package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HW5SearchForManagerLoop {
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
    driver.get(baseUrl + "/login.php");
    driver.findElement(By.name("txtUserName")).clear();
    driver.findElement(By.name("txtUserName")).sendKeys("admin");
    driver.findElement(By.name("txtPassword")).clear();
    driver.findElement(By.name("txtPassword")).sendKeys("password");
    driver.findElement(By.name("Submit")).click();
    driver.findElement(By.xpath("//li[@id='pim']/a/span")).click();
    new Select(driver.findElement(By.id("loc_code"))).selectByVisibleText("Job Title");
    driver.findElement(By.id("loc_name")).clear();
    driver.findElement(By.id("loc_name")).sendKeys("manager");
    System.out.println("I'm done!");
    driver.findElement(By.xpath("//input[@value='Search']")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | id=rightMenu | ]]
    Number total_rows = driver.findElements(By.xpath("//form[@id='standardView']/table/tbody/tr")).size();
    System.out.println(total_rows);
    String i = "1";
    // ERROR: Caught exception [unknown command [while]]
    assertEquals("regexpi:Manager", driver.findElement(By.xpath("//form[@id='standardView']/table/tbody/tr[" + i + "]/td[4]")).getText());
    // ERROR: Caught exception [ERROR: Unsupported command [getEval |  | ]]
    System.out.println(i);
    // ERROR: Caught exception [unknown command [endWhile]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow |  | ]]
    driver.findElement(By.linkText("Logout")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alert.getText();
    } finally {
      acceptNextAlert = true;
    }
  }
}
