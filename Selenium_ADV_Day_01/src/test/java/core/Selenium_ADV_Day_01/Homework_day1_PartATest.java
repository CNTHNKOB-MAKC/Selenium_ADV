package core.Selenium_ADV_Day_01;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.browserlaunchers.locators.GoogleChromeLocator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

 




public class Homework_day1_PartATest {
public static void main(String[] args) throws InterruptedException {
	Homework_day1_PartATest HWD1 = new Homework_day1_PartATest();
	HWD1.launchTest();
	
}
	void launchTest() throws InterruptedException{
		
		WebDriver browser;
		String urlHRM;
String uName;
String pWard;
uName = "admin";
pWard = "Password";
		browser = new FirefoxDriver();
		//browser = new ChromeDriver();
		urlHRM= "http://hrm.seleniumminutes.com";
		
		browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
		
		
		//browser.navigate().to(urlHRM);
		browser.get(urlHRM); // same thing as navigate
		Thread.sleep(3000);
		browser.findElement(By.id("txtUsername")).sendKeys("admin");
		
		browser.findElement(By.id("txtPassword")).sendKeys("Password");
	//	browser.findElement(By.xpath("//*[@id='btnLogin']")).submit();
		browser.findElement(By.id("btnLogin")).click();
		
		//WebElement image0 = browser.findElement(By.xpath("//*[@id='dashboard-quick-launch-panel-menu_holder']/table/tbody/tr/td[1]/div/a/img"));
		browser.manage().window().maximize();
		
		
		
		
	// System.out.println(browser.findElement(By.xpath("//*[@id='dashboard-quick-launch-panel-menu_holder']/table/tbody/tr/td[1]/div/a/span")).toString());

	 System.out.println(browser.findElement(By.xpath("//*[@id='dashboard-quick-launch-panel-menu_holder']/table/tbody/tr/td[1]/div/a/span")).getText());
		
	 System.out.println(browser.findElement(By.xpath("//*[@id='dashboard-quick-launch-panel-menu_holder']/table/tbody/tr/td[1]/div/a/span")).getText().matches("Assign Leave"));
		
		//browser.close();
	 
		browser.quit();	
	}
	
	                                                                                         }
 
