package com.auto.core.HW;

import com.auto.common.Environment;
import com.thoughtworks.selenium.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class JDBCDrivenTestCase extends SeleneseTestCase {

	private Selenium selenium;
	private String login;
	private String password;
	private double pto_taken;
	private double pto_scheduled;
	private	double	pto_remaining;
	
	public JDBCDrivenTestCase(String login, String password, double ptoTaken,
			double ptoScheduled, double ptoRemaining) {
		super();
		this.login = login;
		this.password = password;
		pto_taken = ptoTaken;
		pto_scheduled = ptoScheduled;
		pto_remaining = ptoRemaining;
		selenium = Environment.getBrowser();
	}	
	
	@Parameters
    public static Collection<Object[]> data() throws FileNotFoundException {
		List<Object[]> data = new ArrayList<Object[]>();
		Connection conn = Environment.getDBConnection();
		
		String query = "SELECT * FROM emp_leave";

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next())
			{
				String login = rs.getString("login");
				String password = rs.getString("password");
				double pto_taken = rs.getDouble("pto_taken");
				double pto_scheduled = rs.getDouble("pto_scheduled");
				double pto_remaining = rs.getDouble("pto_remaining");
				
				Object[] singleTest = {login, password, pto_taken, pto_scheduled, pto_remaining};
				data.add(singleTest);
				
				// perform program logic here
				
			}
			stmt.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return data;
    }

    @BeforeClass
    public static void setUpFixture() throws IOException {
    	Environment.startDBConnection();
        Environment.startSeleniumServer();
        Environment.initBrowser();

    }
    
    @Test
	public void testJDBCDrivenTestCase() throws Exception {
		selenium.open("/login.php");
		selenium.type("txtUserName", login);
		selenium.type("txtPassword", password);
		selenium.click("Submit");
		selenium.waitForPageToLoad("30000");
		selenium.click("//li[@id='leave']/ul/li[1]/a/span");
		selenium.waitForPageToLoad("30000");
		org.junit.Assert.assertEquals(pto_taken, Double.parseDouble(selenium.getText("//form[@id='frmSummary']/table/tbody/tr[3]/td[2]")),0.009);
		org.junit.Assert.assertEquals(pto_scheduled, Double.parseDouble(selenium.getText("//form[@id='frmSummary']/table/tbody/tr[3]/td[3]")),0.009);
		org.junit.Assert.assertEquals(pto_remaining, Double.parseDouble(selenium.getText("//form[@id='frmSummary']/table/tbody/tr[3]/td[4]")),0.009);
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
	}
	
    @AfterClass
    public static void tearDownFixture() {
        try {
            Environment.killBrowser();
            Environment.stopSeleniumServer();
            Environment.stopDBConnection();
        } finally {
            ;
        }
    }
}
