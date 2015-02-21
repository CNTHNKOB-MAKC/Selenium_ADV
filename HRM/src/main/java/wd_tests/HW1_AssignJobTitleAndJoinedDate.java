package com.auto.wd_tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.auto.utils.Library;
import com.auto.utils.Utils;

public class HW1_AssignJobTitleAndJoinedDate {
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
  public void testHW1AssignJobTitleAndJoinedDate() throws Exception {
    driver.get(baseUrl + "/symfony/web/index.php/auth/login");

    Library.login(driver, "admin", "Password");
    
    driver.findElement(By.id("menu_pim_addEmployee")).click();
    Utils.type(driver, By.id("firstName"), "Big");
    Utils.type(driver, By.id("lastName"), "Tree");
    String EmployeeId = driver.findElement(By.id("employeeId")).getAttribute("value");
    driver.findElement(By.id("btnSave")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Job')])[4]")).click();
    driver.findElement(By.id("btnSave")).click();
    new Select(driver.findElement(By.id("job_job_title"))).selectByVisibleText("QA Engineer");
    new Select(driver.findElement(By.id("job_emp_status"))).selectByVisibleText("Full Time");
    driver.findElement(By.id("job_joined_date")).click();
    
    Utils.waitForElementVisible(driver, By.id("ui-datepicker-div"), 30);
//    for (int second = 0;; second++) {
//    	if (second >= 60) fail("timeout");
//    	try { if (driver.findElement(By.id("ui-datepicker-div")).isDisplayed()) break; } catch (Exception e) {}
//    	Thread.sleep(1000);
//    }

    driver.findElement(By.linkText("16")).click();
    driver.findElement(By.id("btnSave")).click();
    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
    Utils.type(driver, By.id("empsearch_id"), EmployeeId);
    driver.findElement(By.id("searchBtn")).click();
    assertEquals("Big", driver.findElement(By.xpath("//td[3]/a")).getText());
    assertEquals("Tree", driver.findElement(By.xpath("//td[4]/a")).getText());
    assertEquals("QA Engineer", driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[5]")).getText());
    assertEquals("Full Time", driver.findElement(By.xpath("//table[@id='resultTable']/tbody/tr/td[6]")).getText());

    Library.logout(driver);
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
