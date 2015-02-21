package com.auto.pages;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PIMPage implements BasePage{

	private WebDriver driver;

	public PIMPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean doesWelcomeMessageContainsName(String name) {
//		assertEquals("Welcome Admin", driver.findElement(By.id("welcome")).getText());
		String welcome_message = driver.findElement(By.id("welcome")).getText();
		
		if(welcome_message.equals("Welcome " + name)) {
			return true;
		}
		return false;
	}
}

