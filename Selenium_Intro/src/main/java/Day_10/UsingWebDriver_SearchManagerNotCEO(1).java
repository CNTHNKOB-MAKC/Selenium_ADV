package com.auto.core.in_class;

import com.thoughtworks.selenium.*;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import junit.framework.TestCase;


import org.openqa.selenium.By;
import org.openqa.selenium.HasInputDevices;
import org.openqa.selenium.Mouse;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;

public class UsingWebDriver_SearchManagerNotCEO extends TestCase {
//	Selenium selenium;
	WebDriver driver;
	String url;
	
	public void setUp() throws Exception {
//		setUp("http://hrm.tehportal.net/", "*chrome");
		url = "http://hrm.tehportal.net/";
//		selenium = new DefaultSelenium("localhost", 4444, "*firefox", url);
		driver = new FirefoxDriver();	
//		selenium = new WebDriverBackedSelenium(driver, url);
		
	}
		
	public void tearDown() {
//		selenium.stop();
		driver.quit();
	}
	
	
	public void testSearchManagerNotCEO() throws Exception {
//		selenium.open("/login.php");
		driver.get(url);
		
//		selenium.type("txtUserName", "admin");
		driver.findElement(By.name("txtUserName")).sendKeys("admin");
		
//		selenium.type("txtPassword", "password");
		driver.findElement(By.name("txtPassword")).sendKeys("password");
		
//		selenium.click("Submit");
//		selenium.waitForPageToLoad("45000");
		driver.findElement(By.name("Submit")).click();

//		selenium.click("//li[@id='pim']/a/span");
//		selenium.waitForPageToLoad("45000");
		
		WebElement menuPIM = driver.findElement(By.id("pim"));
//		new Actions(driver).moveToElement(menuPIM).perform();   // Doesn't work on Windows in Firefox
		
		// To click the sub-menu item we must first execute a mouse-over to make sub-menu items visible
		// Retrieving the mouse from the WebDriver
		Mouse mouse =((HasInputDevices)driver).getMouse();
		
		// Performing a mouse-over action
		mouse.mouseMove(((Locatable)menuPIM).getCoordinates());
		
		// Now that we are using the mouse to hover over PIM menu we can click the sub-menu items
		menuPIM.findElement(By.linkText("Employee List")).click();
		
		driver.switchTo().frame("rightMenu");
		
//		selenium.select("loc_code", "label=Job Title");
		driver.findElement(By.id("loc_code")).sendKeys("Job");
		
//		selenium.type("loc_name", "manager");
		driver.findElement(By.id("loc_name")).sendKeys("manager");
		
		System.out.println("Ready to search!");

//		selenium.click("//input[@value='Search']");
//		selenium.waitForPageToLoad("45000");
		driver.findElement(By.xpath("//input[@value='Search']")).click();
		
//		assertFalse(selenium.isTextPresent("CFO"));
		WebElement body = driver.findElement(By.tagName("body"));
		String body_text = body.getText();
		assertFalse(body_text.contains("CFO"));
		
//		assertFalse(selenium.isTextPresent("director"));
		assertFalse(body_text.contains("director"));
		
//		assertFalse(selenium.isTextPresent("Staff"));
		assertFalse(body_text.contains("Staff"));
		
//		selenium.click("link=Logout");
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Logout")).click();
	}
}
