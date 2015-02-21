package core.Selenium_ADV_Day_01;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class ActionChains {
	
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
	  driver = new FirefoxDriver();
	  baseUrl = "http://hrm.seleniumminutes.com/";
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	@Test
	public void testCheckRowStyles() throws Exception {
	  driver.get(baseUrl + "/symfony/web/index.php/auth/login");
	  driver.findElement(By.cssSelector("span.form-hint")).click();
	  driver.findElement(By.id("txtUsername")).clear();
	  driver.findElement(By.id("txtUsername")).sendKeys("admin");
	  driver.findElement(By.id("txtPassword")).clear();
	  driver.findElement(By.id("txtPassword")).sendKeys("Password");
	  driver.findElement(By.id("btnLogin")).click();}
	  
	@Test
	public void testActions1() {
	//Actions Un-chained Example
	  Actions action = new Actions(driver);
	  action.moveToElement(driver.findElement(By.cssSelector("#menu_pim_viewPimModule > b")));// tell my mouse to go to PIM menue  //can tell it where to click on // go t ot menue and i want tupiup to find this guy and click on it 
	  action.click(driver.findElement(By.id("menu_pim_viewEmployeeList")));
	  action.perform();// very important  nothing will happan if you will not tell perform// must 

	//Action Chain Example1  
	  Actions action1 = new Actions(driver);
			action1.moveToElement(driver.findElement(By.cssSelector("#menu_pim_viewPimModule > b")))
			.moveToElement(driver.findElement(By.id("menu_pim_viewEmployeeList")))// elly changed in class for the show
			.click();
					//.perform();
			
			
			
			Actions action2 = new Actions(driver);
			WebElement performance = driver.findElement(By.id("menue_Performance"));
			WebElement manageReviewMenue = driver.findElement(By.id("menue_performance_Manage_Reviews"));
			WebElement manageReviewSubMenue = driver.findElement(By.id("menue_performance_searchPerformanceReview"));
 action.moveToElement(performance).moveToElement(manageReviewSubMenue).click(manageReviewSubMenue);
 action.perform();
 
 // Java script external command to SCRALL doen and cjoose the e,lement
JavascriptExecutor jse = (JavascriptExecutor)driver;//casting can be done in one line 
 jse.executeScript("document.getElemetnVyId('resultTable').scrollIntoView()"); // execute script can accept one or two parameters
 
 
/* @After
 public void tearDown11(){
	 
	driver.quit(); 
 }
 */
 
					
	}
	private void tearDown11() {
		// TODO Auto-generated method stub
		
	}
			
			
			
			
}
