package com.auto.tests;

import com.auto.utils.Utils;
import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

public class ValidLogin_WD {
	private Selenium selenium;
	private int a = 5;
	private String baseUrl = "http://hrm.tehportal.net/";
	private WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testValidLogin() throws Exception {
		
//		selenium.open("/symfony/web/index.php/auth/login");
		driver.get(baseUrl);

//		assertEquals("OrangeHRM", selenium.getTitle());
		assertEquals("OrangeHRM", driver.getTitle());
		
		a++;
		
//		selenium.type("id=txtUsername", "admin");
		Utils.type(driver, By.id("txtUsername"), "admin");
////		driver.findElement(By.id("txtUsername")).clear();
////		driver.findElement(By.id("txtUsername")).sendKeys("admin");
		
//		selenium.type("id=txtPassword", "Password");
		Utils.type(driver, By.id("txtPassword"), "Password");
////		driver.findElement(By.id("txtPassword")).clear();
////		driver.findElement(By.id("txtPassword")).sendKeys("Password");
		
//		selenium.click("id=btnLogin");
		driver.findElement(By.id("btnLogin")).click();
		
		++a;
		
//		WebDriver **should** wait for page to load by default		
//		selenium.waitForPageToLoad("45000");

//		assertEquals("OrangeHRM", selenium.getTitle());
		assertEquals("OrangeHRM", driver.getTitle());
		
//		assertEquals("Welcome Admin", selenium.getText("id=welcome"));
		
		WebElement welcome_widget = driver.findElement(By.id("welcome"));
		assertEquals("Welcome Admin", welcome_widget.getText());

//		selenium.click("id=welcome");
		welcome_widget.click();
		
		a+=2;
		
		System.out.println("Just a random number " + (Math.random()));
		
//TODO: EY: Replace this with waitForVisible later
				Thread.sleep(500);

		selenium.click("link=Logout");
		driver.findElement(By.linkText("Logout")).click();
		
//		selenium.waitForPageToLoad("45000");
		assertEquals("OrangeHRM", driver.getTitle());
	}


	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
