package EllieClass3InClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.csvreader.CsvReader;


@RunWith(Parameterized.class)
public class CSVCalculator {
	private WebDriver driver;
	private String baseUrl;
	private int num1;
	private String operator;
	private int num2;
	private String expected_result;

	public CSVCalculator(int num1, String operator, int num2,
			String expected_result) {
		super();
		this.num1 = num1;
		this.operator = operator;
		this.num2 = num2;
		this.expected_result = expected_result;
	}

	@Parameters // she said that I could copy and paste it into the DDT Calculator INSTEAD Of the parameters in the CSVCalculator class
	public static Collection<Object[]> data() throws IOException {
		List<Object[]> data = new ArrayList<Object[]>();
		
			CsvReader reader = new CsvReader("C:/WorkspaceEllie/Selenium_ADV_Day_03/lib/data(1).csv");
			reader.readHeaders();
			
			while(reader.readRecord()) {
			
				int num1 = Integer.valueOf(reader.get("num1"));
				int num2 = Integer.valueOf(reader.get("num2"));
				String operator = reader.get("oper");
				String expected_result = reader.get("exp_result");
				
				Object[] test = {num1, operator, num2, expected_result};
				data.add(test);
			}
			
			reader.close();
			
	
		
		return data;
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://chemistry.about.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testDDCalculator() throws Exception {
		driver.get(baseUrl + "/library/weekly/blcalculator.htm");
		assertEquals("Online Calculator", driver.getTitle());
		driver.findElement(By.name("cal" + num1)).click();
		driver.findElement(By.name("cal" + operator)).click();
		driver.findElement(By.name("cal" + num2)).click();
		driver.findElement(By.name("calequal")).click();
		assertEquals(expected_result, driver
				.findElement(By.name("calcResults")).getAttribute("value"));
		driver.findElement(By.name("calclear")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
