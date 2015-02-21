package com.auto.utils;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Library {

	
	
	public static void login(WebDriver driver, String username, String password) {
	    assertEquals("OrangeHRM", driver.getTitle());
	    Utils.type(driver, By.id("txtUsername"), username);
	    Utils.type(driver, By.id("txtPassword"), password);
	    
//	    driver.findElement(By.id("txtUsername")).clear();
//	    driver.findElement(By.Byid("txtUsername")).sendKeys(username);    
//	    driver.findElement(By.id("txtPassword")).clear();
//	    driver.findElement(By.id("txtPassword")).sendKeys(password);
	    
	    driver.findElement(By.id("btnLogin")).click();
	    assertEquals("OrangeHRM", driver.getTitle());
	}
	
	public static void logout(WebDriver driver) {
		driver.findElement(By.id("welcome")).click();
		Utils.waitForElementPresent(driver, By.linkText("Logout"), 2);
	    driver.findElement(By.linkText("Logout")).click();
	    assertEquals("OrangeHRM", driver.getTitle());
	}
}
