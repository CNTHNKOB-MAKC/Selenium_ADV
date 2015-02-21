package com.auto.tests;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class HW8_AlphaOrder {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://hrm.tehportal.net/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testHW8_AlphaOrder() throws Exception {
		selenium.open("/symfony/web/index.php/auth/login");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.type("id=txtUsername", "admin");
		selenium.type("id=txtPassword", "Password");
		selenium.click("id=btnLogin");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		assertEquals("Welcome Admin", selenium.getText("id=welcome"));
		selenium.click("link=First (& Middle) Name");
		selenium.waitForPageToLoad("45000");
		int row_count = (selenium.getXpathCount("//table[@id='resultTable']/tbody/tr")).intValue();
		for(int i=1; i < row_count; i++) {
			
			String top = selenium.getText("//table[@id='resultTable']/tbody/tr["+ i +"]/td[3]");
			String bottom = selenium.getText("//table[@id='resultTable']/tbody/tr["+ (i+1) +"]/td[3]");
			assertTrue("Helpfull message goes here", top.compareToIgnoreCase(bottom)<=0);
			assertFalse(top.compareToIgnoreCase(bottom)>0);
			if(top.compareToIgnoreCase(bottom)>0) {
				fail("string " +top+ " and string " +bottom+ " are in reverse order.");
			}
		}
		selenium.click("id=welcome");
		Thread.sleep(500);
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
