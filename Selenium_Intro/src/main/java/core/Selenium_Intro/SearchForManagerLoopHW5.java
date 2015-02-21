package com.auto.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.auto.common.CommonMethods;

public class SearchForManagerLoopHW5 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://hrm.tehportal.net/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testHW5SearchForManagerLoop() throws Exception {
    CommonMethods.login(driver, "admin", "password");
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(By.id("pim")));
    action.moveToElement(driver.findElement(By.linkText("Employee List")));
    action.click();
    action.build().perform();
    driver.switchTo().frame("rightMenu");
    new Select(driver.findElement(By.id("loc_code"))).selectByVisibleText("Job Title");
    CommonMethods.type(driver, By.id("loc_name"), "manager");
    System.out.println("I'm done!");
    driver.findElement(By.xpath("//input[@value='Search']")).click();
    
//    Number total_rows = driver.findElements(By.xpath("//form[@id='standardView']/table/tbody/tr")).size();
  Number total_rows = driver.findElement(By.id("standardView"))
		  .findElements(By.xpath(".//table/tbody/tr")).size();
    System.out.println(total_rows);
    int i = 1;
    while(i <= total_rows.intValue()) {
	    String text = driver.findElement(By.id("standardView"))
	  		  .findElement(By.xpath(".//table/tbody/tr[" + i + "]/td[4]")).getText();
	    assertTrue(text.compareToIgnoreCase("Manager")==0);
	    i++;
	    System.out.println(i);
    }
    CommonMethods.logout(driver);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

}
