package EllieClass3InClass;


import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class DDCalculator {
	private WebDriver driver;
	private String baseUrl;
	private int num1;
	private String operator;
	private int num2;
	private double expected_result;

	public DDCalculator(int num1, String operator, int num2,
			double expected_result) {
		super();
		this.num1 = num1;
		this.operator = operator;
		this.num2 = num2;
		this.expected_result = expected_result;
	}

	@Parameters
	public static Collection<Object[]> data() {
		// Object[][] array = {{2, "plus", 1, 3},
		// {5, "minus", 3, 2},
		// {3, "mul", 2, 6},
		// {5, "div", 2, 2.5}};
		// return Arrays.asList(array);

		return Arrays
				.asList(new Object[][] { { 2, "plus", 1, 3 },
						{ 5, "minus", 3, 2 }, { 3, "mul", 2, 6 },
						{ 5, "div", 2, 2.5 } });
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
		assertEquals(expected_result, Double.valueOf(driver
				.findElement(By.name("calcResults")).getAttribute("value")),0.01);
		driver.findElement(By.name("calclear")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

}
