
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleneseTestCase;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class SearchForManager extends TestCase {
	
	Selenium selenium;
	WebDriver driver;
	String baseUrl;
	
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://hrm.tehportal.net/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	public void testSearchForManager() throws Exception {
		driver.get(baseUrl);
//		selenium.open("/login.php");
		driver.findElement(By.name("txtUserName")).sendKeys("admin");
//		selenium.type("txtUserName", "admin");
		driver.findElement(By.name("txtPassword")).sendKeys("password");		
//		selenium.type("txtPassword", "password");
		driver.findElement(By.name("Submit")).click();
//		selenium.click("Submit");		
//		selenium.waitForPageToLoad("45000");
		
		driver.switchTo().frame("rightMenu");
		Select searchBy = new Select(driver.findElement(By.id("loc_code")));
		searchBy.selectByVisibleText("Job Title");
//		selenium.select("loc_code", "label=Job Title");
		driver.findElement(By.name("loc_name")).sendKeys("manager");
//		selenium.type("loc_name", "manager");
		System.out.println("I'm done!");
		driver.findElement(By.xpath("//input[@value='Search']")).click();
//		selenium.click("//input[@value='Search']");
//		selenium.waitForPageToLoad("45000");
		
		WebElement body = driver.findElement(By.tagName("body"));
		String body_text = body.getText();
		assertFalse(body_text.contains("CFO"));
//		assertFalse(selenium.isTextPresent("CFO"));hthhmhjhjm
		
		assertFalse(body_text.contains("director"));
//		assertFalse(selenium.isTextPresent("director"));
		
		assertFalse(body_text.contains("Staff"));
//		assertFalse(selenium.isTextPresent("Staff"));
		
		assertTrue(body_text.contains("Manager"));		
//		assertTrue(selenium.isTextPresent("Manager"));
		
		driver.switchTo().window("");
		driver.findElement(By.linkText("Logout")).click();
		//selenium.click("link=Logout");
	}

	public void tearDown() throws Exception {
		selenium.stop();
		driver.quit();
	}
}
