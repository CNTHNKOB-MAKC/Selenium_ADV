package com.auto.tests;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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

public class JDBCDataIntegrityEmployee {
  private static Connection connection;
private WebDriver driver;
private String employee_id = "1122334455";
private String query = "SELECT emp_lastname, emp_firstname FROM hs_hr_employee WHERE employee_id = " + employee_id;

  @BeforeClass
  public static void suiteSetUp() {
	  connection = CommonMethods.getDBConnection();
  }
  
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testJDBCDataIntegrityEmployee() throws Exception {
	CommonMethods.login(driver, "admin", "password");
	driver.switchTo().frame("rightMenu");
    driver.findElement(By.xpath("//input[@value='Add']")).click();
    CommonMethods.waitToLoadElement(driver, By.id("txtEmployeeId"), 5);
    CommonMethods.type(driver, By.id("txtEmployeeId"), employee_id);
    CommonMethods.type(driver, By.id("txtEmpLastName"), "Skobel");
    CommonMethods.type(driver, By.name("txtEmpFirstName"), "Ellie");
    driver.findElement(By.id("btnEdit")).click();
    
    CommonMethods.waitToLoadElement(driver, By.id("personal"), 5);
    
    // verify information in the DB
    Statement statement = connection.createStatement();
    ResultSet result = statement.executeQuery(query);
    
    assertTrue(result.next());
    System.out.println(result.getString("emp_firstname") + " " + result.getString("emp_lastname"));
    
    
    driver.switchTo().defaultContent();
    
    Actions action = new Actions(driver);
    action.moveToElement(driver.findElement(By.id("pim")));
    action.moveToElement(driver.findElement(By.linkText("Employee List")));
    action.click();
    action.build().perform();
    driver.switchTo().frame("rightMenu");

    new Select(driver.findElement(By.id("loc_code"))).selectByVisibleText("Emp. ID");
    CommonMethods.type(driver, By.id("loc_name"), employee_id);
    driver.findElement(By.xpath("//input[@value='Search']")).click();
    driver.findElement(By.name("chkLocID[]")).click();
    driver.findElement(By.xpath("//input[@value='Delete']")).click();
    assertEquals("Successfully Deleted", driver.findElement(By.xpath("//form[@id='standardView']/div[2]/span")).getText());
   
    result = statement.executeQuery(query);
    assertFalse(result.next());
    
    CommonMethods.logout(driver);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }

  @AfterClass
  public static void suiteTearDown() {
	  CommonMethods.closeDBConnection();
  }
  
}
