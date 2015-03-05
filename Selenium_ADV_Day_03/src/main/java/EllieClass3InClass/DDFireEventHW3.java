package EllieClass3InClass;

import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.internal.seleniumemulation.JavascriptLibrary;
import org.openqa.selenium.support.ui.Select;

import com.auto.common.CommonMethods;

@RunWith(Parameterized.class)
public class DDFireEventHW3 {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private String yahooID;
  private String last_name;
  private String first_name;
  private String expected_message;

  public DDFireEventHW3(String first_name, String last_name, String yahooID,
		String expected_message) {
	super();
	this.yahooID = yahooID;
	this.last_name = last_name;
	this.first_name = first_name;
	this.expected_message = expected_message;
}
  
  @Parameters
  public static Collection<Object[]>	data() {
	  return Arrays.asList(new Object[][] { { "Steve", "Jobs", "abc", "Your ID must be between 4 and 32 characters long" },
	  	{"Ellie", "Skobel", "abcd", "This ID is not available"},
		{"Dotty", "Smith", "1234", "Your ID must begin with a letter"},
		{"Manny", "Worts", "a b c d", "Only letters, numbers, underscores, and one dot (.) are allowed"},
		{"Colorful", "Stripes", "a._b", "._ and _. not allowed"},
		{"Colorful", "Stripes", "a__b", "No consecutive underscores or dots allowed"},
		{"Colorful", "Stripes", "abc.", "Cannot end with an underscore or dot (.)"}		
	  }); 
  }

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://edit.yahoo.com";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testDDFireEventHW3() throws Exception {
    driver.get(baseUrl + "/registration");
    assertEquals("Yahoo! Registration", driver.getTitle());
    driver.findElement(By.id("firstname")).click();
    CommonMethods.type(driver, By.id("firstname"), first_name);
    CommonMethods.type(driver, By.id("secondname"), last_name);
    
    WebElement element = driver.findElement(By.id("yahooid"));
    JavascriptLibrary javascript = new JavascriptLibrary();
    
    javascript.callEmbeddedSelenium(driver, "triggerEvent", element, "focus");
    CommonMethods.type(driver, By.id("yahooid"), yahooID);
    javascript.callEmbeddedSelenium(driver, "triggerEvent", element, "blur");
    CommonMethods.pause(200);
    assertEquals(expected_message, driver.findElement(By.id("yahooIdFldMsg")).getText());
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
