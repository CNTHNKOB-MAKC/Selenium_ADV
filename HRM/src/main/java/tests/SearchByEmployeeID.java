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

public class SearchByEmployeeID {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://hrm.tehportal.net/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testSearchByEmployeeID() throws Exception {
		selenium.open("/symfony/web/index.php/auth/login");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.type("id=txtUsername", "admin");
		selenium.type("id=txtPassword", "Password");
		selenium.click("id=btnLogin");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.click("id=btnAdd");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.type("id=firstName", "New");
		selenium.type("id=lastName", "Employee");
		String EmployeeId = selenium.getValue("id=employeeId");
		selenium.click("id=btnSave");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.click("id=menu_pim_viewEmployeeList");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.type("id=empsearch_id", EmployeeId);
		selenium.click("id=searchBtn");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		assertEquals("New", selenium.getText("//td[3]/a"));
		assertEquals("Employee", selenium.getText("//td[4]/a"));
		selenium.click("id=welcome");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
