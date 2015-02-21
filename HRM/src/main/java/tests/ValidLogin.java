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

public class ValidLogin {
	private Selenium selenium;
	private int a = 5;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://hrm.tehportal.net/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testValidLogin() throws Exception {
		selenium.open("/symfony/web/index.php/auth/login");
		assertEquals("OrangeHRM", selenium.getTitle());
		a++;
		selenium.type("id=txtUsername", "admin");
		selenium.type("id=txtPassword", "Password");
		selenium.click("id=btnLogin");
		++a;
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		assertEquals("Welcome Admin", selenium.getText("id=welcome"));
		selenium.click("id=welcome");
		a+=2;
		
		System.out.println("Just a random number " + (Math.random()));
		
//TODO: EY: Replace this with waitForVisible later
				Thread.sleep(500);
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
	}
	
	@Test
	public void testHW1_AssignJobTitleAndJoinedDate() throws Exception {
		selenium.open("/symfony/web/index.php/auth/login");
		assertEquals("OrangeHRM", selenium.getTitle());
		
		String word1 = "apple";
		word1.compareToIgnoreCase("bear");
		
		
		selenium.type("id=txtUsername", "admin");
		selenium.type("id=txtPassword", "Password");
		selenium.click("id=btnLogin");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.click("id=menu_pim_addEmployee");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.type("id=firstName", "Big");
		selenium.type("id=lastName", "Tree");
		String EmployeeId = selenium.getValue("id=employeeId");
		selenium.click("id=btnSave");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.click("xpath=(//a[contains(text(),'Job')])[4]");
		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", selenium.getTitle());
		selenium.click("id=btnSave");
		selenium.select("id=job_job_title", "label=QA Engineer");
		selenium.select("id=job_emp_status", "label=Full Time");
		selenium.click("id=job_joined_date");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isVisible("id=ui-datepicker-div")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("link=16");
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
		assertEquals("Big", selenium.getText("//td[3]/a"));
		assertEquals("Tree", selenium.getText("//td[4]/a"));
		assertEquals("QA Engineer", selenium.getText("//table[@id='resultTable']/tbody/tr/td[5]"));
		assertEquals("Full Time", selenium.getText("//table[@id='resultTable']/tbody/tr/td[6]"));
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
