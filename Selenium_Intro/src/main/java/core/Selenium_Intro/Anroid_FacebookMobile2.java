package com.auto.core;

import com.thoughtworks.selenium.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.android.AndroidDriver;

import java.util.regex.Pattern;

@SuppressWarnings("deprecation")
public class Anroid_FacebookMobile2 extends SeleneseTestCase {
//	Selenium selenium;
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
		
		driver = new AndroidDriver();
		
		String baseUrl = "http://www.facebook.com/";
		driver.get(baseUrl);
		
//		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://www.facebook.com/");
//		selenium.start();
	}

	@Test
	public void testAnroid_FacebookMobile2() throws Exception {
		assertEquals("Facebook Mobile", driver.getTitle());
		
		driver.findElement(By.name("email")).sendKeys("kaya738404@yahoo.com");
		driver.findElement(By.name("pass")).sendKeys("Ellie123");
		driver.findElement(By.name("login")).click();
		Thread.sleep(3000);
		assertEquals("Facebook", driver.getTitle());

		String message = "having a great day " + Math.round(Math.random()*60);
		driver.findElement(By.linkText("Settings")).toggle();
		driver.findElement(By.id("composerInput")).sendKeys(message);
		driver.findElement(By.name("update")).click();
		driver.findElement(By.linkText("Log Out")).click();
		assertEquals("Facebook Mobile", driver.getTitle());
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}
}
