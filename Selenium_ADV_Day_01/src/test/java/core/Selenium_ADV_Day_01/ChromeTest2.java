package core.Selenium_ADV_Day_01;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromeTest2 {
public static void main(String[] args) {
	System.setProperty("webdriver.chrome.driver", "C:/WorkspaceEllie/Selenium_ADV_Day_01/src/main/resources/chromedriver.exe");
	 
	ChromeOptions options = new ChromeOptions();
	//options.addArguments("window-size=800,600");

	DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
	WebDriver driver = new ChromeDriver(capabilities);

	driver.get("http://google.com/");

	//driver.close();

}
}
