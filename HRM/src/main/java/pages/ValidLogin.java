package com.auto.pages;

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
    driver.get(baseUrl + "/symfony/web/index.php/auth/login");
    BasePage page = new LoginPage(driver);
    page = ((LoginPage) page).login("admin", "Password");
    assertTrue(((PIMPage) page).doesWelcomeMessageContainsName("Admin"));
    
    
    
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}
