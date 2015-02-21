package com.auto.core.HW;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

public class AutoCompleteTestCase extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://hrm.tehportal.net/", "*firefox");
	}
	public void testAutoCompleteTestCase() throws Exception {
		selenium.open("/login.php");
		selenium.type("txtUserName", "admin");
		selenium.type("txtPassword", "password");
		selenium.click("Submit");
		selenium.waitForPageToLoad("30000");
		selenium.click("//li[@id='leave']/ul/li[4]/a/span");
		selenium.waitForPageToLoad("30000");
		selenium.type("txtEmployeeId", "a");
		selenium.typeKeys("txtEmployeeId", "a");
		verifyTrue(selenium.isElementPresent("//div[@id='employeeSearchACContainer']"));
		// Waiting for ANY text to be loaded
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (Pattern.compile(".+").matcher(selenium.getText("//div[@id='employeeSearchACContainer']/div[3]/div[2]/ul/li[1]")).find()) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		// ... now we will verify that the auto fill suggestions match the entered input
		verifyTrue(selenium.getText("//div[@id='employeeSearchACContainer']/div[3]/div[2]/ul/li[1]").matches("^A[\\s\\S]*$"));
		String user = selenium.getText("//div[@id='employeeSearchACContainer']/div[3]/div[2]/ul/li[1]");
		selenium.click("//div[@id='employeeSearchACContainer']/div[3]/div[2]/ul/li[1]");
		verifyEquals(user, selenium.getValue("txtEmployeeId"));
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("30000");
	}
}
