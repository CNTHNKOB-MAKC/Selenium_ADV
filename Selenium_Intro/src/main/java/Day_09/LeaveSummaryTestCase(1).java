package com.auto.core.HW;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

public class LeaveSummaryTestCase extends SeleneseTestCase {
	public LeaveSummaryTestCase(String name) {
		super(name);
	}
	public void setUp() throws Exception {
		setUp("http://hrm.tehportal.net/", "*firefox");
	}
	public void testLeaveSummaryTestCase() throws Exception {
		selenium.open("/login.php");
		selenium.type("txtUserName", "admin");
		selenium.type("txtPassword", "password");
		selenium.click("Submit");
		selenium.waitForPageToLoad("30000");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.click("//li[@id='leave']/ul/li[1]/ul/li/a/span");
		selenium.waitForPageToLoad("30000");
		selenium.selectFrame("rightMenu");
		selenium.select("cmbId", "label=Select");
		selenium.click("//input[@value='...']");
		selenium.waitForPopUp("Employees", "30000");
		selenium.selectWindow("name=Employees");
		selenium.selectWindow("name=Employees");
		String name = selenium.getText("//tbody/tr[1]/td[2]/a");
		selenium.click("//tbody/tr[1]/td[2]/a");
		selenium.selectWindow("null");
		selenium.select("leaveTypeId", "label=PTO");
		selenium.click("btnView");
		selenium.waitForPageToLoad("30000");
		verifyEquals("PTO", selenium.getText("//form[@id='frmSummary']/table/tbody/tr/td[1]"));
		verifyEquals("Leave Summary for " + name + " for 2010", selenium.getText("//h2"));
		System.out.println("Leave Summary for " + name + " for 2010");
		selenium.selectFrame("relative=up");
		selenium.click("link=Logout");
	}
}
