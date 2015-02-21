package com.auto.core.WDFormat;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.auto.common.CommonMethods;

public class AlphaOrderHW8 {
	WebDriver driver;
	
	@Before
	public void setUp() throws Exception {
//		System.setProperty("webdriver.chrome.driver", "browsers//chromedriver.exe");
		
//		driver = new HtmlUnitDriver(true);
//		driver = new ChromeDriver();
		
		System.setProperty("webdriver.firefox.profile", "CookieMonster");
		driver = new FirefoxDriver();
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	@Test
	public void test() throws InterruptedException {
		CommonMethods.login(driver, "admin", "password");
		driver.switchTo().frame("rightMenu");
		
		driver.findElement(By.linkText("Employee Name")).click();
		
//		List<WebElement> all_rows_list = driver.findElements(By.xpath("//tbody/tr"));
		List<WebElement> all_rows_list = driver.findElements(By.xpath("//tbody/tr/td[3]/a"));
		
		int total_rows = all_rows_list.size();
		
		for (int i = 0; i < total_rows-1; i++) {			
			String top_name = all_rows_list.get(i).getText();
			String bottom_name = all_rows_list.get(i+1).getText();
			assertTrue("The names are not in the expected alpha order.", top_name.compareToIgnoreCase(bottom_name) <= 0);			
		}
		
		CommonMethods.logout(driver);
		
	}

}
