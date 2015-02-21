package com.auto.wd_tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.auto.utils.Library;
import com.auto.utils.Utils;

public class SearchByEmployeeId {
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
  public void testSearchByEmployeeId() throws Exception {
    driver.get(baseUrl + "/symfony/web/index.php/auth/login");
    assertEquals("OrangeHRM", driver.getTitle());
/*    
 *  driver.findElement(By.id("txtUsername")).clear();
    driver.findElement(By.id("txtUsername")).sendKeys("admin");
    driver.findElement(By.id("txtPassword")).clear();
    driver.findElement(By.id("txtPassword")).sendKeys("Password");
    driver.findElement(By.id("btnLogin")).click();
    assertEquals("OrangeHRM", driver.getTitle());
*/    
    Library.login(driver, "admin", "Password");
    driver.findElement(By.id("btnAdd")).click();
    assertEquals("OrangeHRM", driver.getTitle());

    Utils.type(driver, By.id("firstName"), "New");
//    driver.findElement(By.id("firstName")).clear();
//    driver.findElement(By.id("firstName")).sendKeys("New");

    Utils.type(driver, By.id("lastName"), "Employee");
//    driver.findElement(By.id("lastName")).clear();
//    driver.findElement(By.id("lastName")).sendKeys("Employee");

    String EmployeeId = driver.findElement(By.id("employeeId")).getAttribute("value");
    driver.findElement(By.id("btnSave")).click();
    assertEquals("OrangeHRM", driver.getTitle());
    driver.findElement(By.id("menu_pim_viewEmployeeList")).click();
    assertEquals("OrangeHRM", driver.getTitle());
    
    Utils.type(driver, By.id("empsearch_id"), EmployeeId);
//    driver.findElement(By.id("empsearch_id")).clear();
//    driver.findElement(By.id("empsearch_id")).sendKeys(EmployeeId);
    
    driver.findElement(By.id("searchBtn")).click();
    assertEquals("OrangeHRM", driver.getTitle());
    assertEquals("New", driver.findElement(By.xpath("//td[3]/a")).getText());
    assertEquals("Employee", driver.findElement(By.xpath("//td[4]/a")).getText());
    
// Extra piece of code added to demonstrate how to use Actions object in selenium2
// This code selects Admin > Configuration > Localization menu item using mouse over behavior
    driver.findElement(By.id("menu_admin_viewAdminModule")).click();
    Actions selectLocalization = new Actions(driver);
    selectLocalization.moveToElement(driver.findElement(By.id("menu_admin_Configuration"))).perform();
    selectLocalization.moveToElement(driver.findElement(By.id("menu_admin_localization"))).perform();
    selectLocalization.click().perform();
    
    Utils.waitForElementPresent(driver, By.id("localization"), 2);
    assertEquals("Localization", driver.findElement(By.cssSelector("div.head h1")).getText());

//    driver.findElement(By.id("welcome")).click();
//    driver.findElement(By.linkText("Logout")).click();
//    assertEquals("OrangeHRM", driver.getTitle());
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
