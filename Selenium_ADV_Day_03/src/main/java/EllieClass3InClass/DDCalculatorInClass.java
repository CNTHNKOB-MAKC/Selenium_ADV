
package EllieClass3InClass;


import java.io.IOException;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

 
import org.omg.CORBA.Environment;

import com.thoughtworks.selenium.*;

@RunWith(Parameterized.class)
public class DDCalculatorInClass extends SeleneseTestCase {
	
	private int num1;
	private int num2;
	private String oper;
	private double exp_result;
	
	@Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { 0, "calplus", 7, 7.00 }, { 1, "caldiv", 5, 0.20 },
                { 0, "caldiv", 9, 0 }, { 2, "calmul", 3, 6.00 }, { 1, "calminus", 5, -4.00 }, });
    }


	
    public DDCalculatorInClass(int num1, String oper, int num2, double result) {
        super();
        this.num1 = num1;
        this.num2 = num2;
        this.oper = oper;
        exp_result = result;
    }

    @BeforeClass
    public static void setUpFixture() throws IOException {
        Environment.startSeleniumServer();
        Environment.setURL("http://chemistry.about.com/");
        Environment.initBrowser();

    }
    
    @Test
	public void testDDCalculator() throws Exception {
		selenium = Environment.getBrowser();
		selenium.open("/library/weekly/blcalculator.htm");
		assertEquals("Online Calculator", selenium.getTitle());
		selenium.type("calcResults", String.valueOf(num1));
		selenium.click(oper); //VARIABLE
		selenium.type("calcResults", String.valueOf(num2));
		selenium.click("calequal"); //not variABLE
		String result = selenium.getValue("calcResults");
		verifyEquals(result, exp_result);
		// ALTERNATIVE
		assertEquals("Acquired result does not equal expected result", Double.valueOf(result), exp_result);
	}
	
    @AfterClass
    public static void tearDownFixture() {
        try {
            Environment.killBrowser();
            Environment.stopSeleniumServer();
        } finally {
            ;
        }
    }
}
