package core.Selenium_WebDriverAPIbyExample;

import java.io.File;



import java.util.List;
import java.util.Set;

import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public   class ChromeDriver implements WebDriver    {
	
	public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:/WorkspaceEllie/Selenium_ADV_Day_01/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();              
        driver.get("http://www.google.com");

    }

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public WebElement findElement(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<WebElement> findElements(By arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public void get(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public String getCurrentUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPageSource() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getWindowHandle() {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> getWindowHandles() {
		// TODO Auto-generated method stub
		return null;
	}

	public Options manage() {
		// TODO Auto-generated method stub
		return null;
	}

	public Navigation navigate() {
		// TODO Auto-generated method stub
		return null;
	}

	public void quit() {
		// TODO Auto-generated method stub
		
	}

	public TargetLocator switchTo() {
		// TODO Auto-generated method stub
		return null;
	}

	}
