package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class HW2LeaveSummary {
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
  public void testHW2LeaveSummary() throws Exception {
    driver.get(baseUrl + "/login.php");
    assertEquals("OrangeHRM - New Level of HR Management", driver.getTitle());
    driver.findElement(By.name("txtUserName")).clear();
    driver.findElement(By.name("txtUserName")).sendKeys("admin");
    driver.findElement(By.name("txtPassword")).clear();
    driver.findElement(By.name("txtPassword")).sendKeys("password");
    driver.findElement(By.name("Submit")).click();
    assertEquals("OrangeHRM", driver.getTitle());
    driver.findElement(By.xpath("//li[@id='leave']/ul/li/ul/li/a/span")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | rightMenu | ]]
    new Select(driver.findElement(By.name("cmbId"))).selectByVisibleText("Select");
    driver.findElement(By.xpath("//input[@value='...']")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | Employees | 30000]]
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=Employees | ]]
    String emp_name = driver.findElement(By.xpath("//tbody/tr/td[2]/a")).getText();
    driver.findElement(By.xpath("//tbody/tr/td[2]/a")).click();
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow |  | ]]
    new Select(driver.findElement(By.name("leaveTypeId"))).selectByVisibleText("PTO (Paid Time Off)");
    driver.findElement(By.id("btnView")).click();
    assertTrue(driver.findElement(By.xpath("//h2")).getText().matches("^Leave Summary for \\$\\{emp_name\\} for [\\s\\S]*$"));
    String  = driver.findElement(By.xpath("//form[@id='frmSummary']/table/tbody/tr/td")).getText();
    assertEquals("PTO (Paid Time Off)", driver.findElement(By.xpath("//form[@id='frmSummary']/table/tbody/tr/td")).getText());
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | relative=up | ]]
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
