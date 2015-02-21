package com.auto.utils;

import java.util.Date;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Utils {

	public static void type(WebDriver driver, By locator, String text) {
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text);
	}
	
	public static void waitForElementPresent(WebDriver driver, By locator, int seconds) {
		long startTime = new Date().getTime();
		int duration = seconds * 1000;
		
		while(!isElementPresent(driver, locator)) {
			long currentTime = new Date().getTime();
			if (currentTime - startTime > duration) {
				Assert.fail("Could not find element: " + locator);
			}
			pause(500);
		}
	}
	
	
	public static boolean isElementPresent(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
	}
	
	public static void pause(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
