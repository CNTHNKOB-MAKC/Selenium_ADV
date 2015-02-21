package com.auto.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.auto.utils.Utils;

public class LoginPage implements BasePage{
	
	private WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	public PIMPage login(String username, String password) {
	    assertEquals("OrangeHRM", driver.getTitle());
	    Utils.type(driver, By.id("txtUsername"), username);
	    Utils.type(driver, By.id("txtPassword"), password);
	    driver.findElement(By.id("btnLogin")).click();
	    assertEquals("OrangeHRM", driver.getTitle());
	    return new PIMPage(driver);
	}
}
