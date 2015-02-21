package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ValidLogin {
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
  public void testValidLogin() throws Exception {
    driver.get(baseUrl + "/login.php");
    assertEquals("OrangeHRM - New Level of HR Management", driver.getTitle());
    driver.findElement(By.name("txtUserName")).clear();
    driver.findElement(By.name("txtUserName")).sendKeys("admin");
    driver.findElement(By.name("txtPassword")).clear();
    driver.findElement(By.name("txtPassword")).sendKeys("password");
    driver.findElement(By.name("Submit")).click();
    assertEquals("Welcome Admin", driver.findElement(By.xpath("//ul[@id='option-menu']/li")).getText());
    driver.findElement(By.linkText("Logout")).click();
    assertEquals("OrangeHRM - New Level of HR Management", driver.getTitle());
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
