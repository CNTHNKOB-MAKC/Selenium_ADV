package core.Selenium_ADV_Day_01;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.webdriven.commands.GetText;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
public class Homework_day1_PartATestTest {

	
	
		
		
	  static WebDriver browser;
		@BeforeClass // for this peace of shit to work @BEFOREC must be static a
		static public void geturl111() {
			
			 //private static ChromeDriverService service;
			    String urlHRM;
			    String uName;
		        String pWard;
		 StringBuffer verificationErrors = new StringBuffer();
		   uName = "admin";
	    pWard = "Password";
		browser = new FirefoxDriver();
		urlHRM= "http://hrm.seleniumminutes.com";
        
		browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
		browser.get(urlHRM); // same thing as navigate
		
		
	/*	 service = new ChromeDriverService.Builder()
	        .usingDriverExecutable(new File("C:/WorkspaceEllie/Selenium_ADV_Day_01/src/main/resources/chromedriver.exe"))
	        .usingAnyFreePort()
	        .build();
	    service.start();
		*/
		
		 }
		
    @Before
		public void logIn() throws InterruptedException{
    	
	 
		browser.findElement(By.id("txtUsername")).sendKeys("admin");
		browser.findElement(By.id("txtPassword")).sendKeys("Password");
		browser.findElement(By.id("btnLogin")).click();
		browser.manage().window().maximize();
		//WebElement image0 = browser.findElement(By.xpath("//*[@id='dashboard-quick-launch-panel-menu_holder']/table/tbody/tr/td[1]/div/a/img"));
		//browser.navigate().to(urlHRM);
        //browser.findElement(By.xpath("//*[@id='btnLogin']")).submit();
		
		//assertEquals("Assign Leave",browser.findElement(By.xpath("//*[@id='dashboard-quick-launch-panel-menu_holder']/table/tbody/tr/td[1]/div/a/span")).getText() );
    }
	
    @Test
	public void assertAssignLeave() throws InterruptedException{
	  
	
	assertEquals("Assign Leave",browser.findElement(By.xpath("//*[@id='dashboard-quick-launch-panel-menu_holder']/table/tbody/tr/td[1]/div/a/span")).getText() );
}

    
    
    
    
    
		@AfterClass
		 static  public void quitsTheBrowser(){
			
				browser.quit();	
				//browser.close();	
			}
		 
	
		

}	
		
		
		
 

 
