
package com.auto.class8;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.auto.common.Environment;
import com.csvreader.CsvReader;
import com.thoughtworks.selenium.*;

@RunWith(Parameterized.class)
public class CSVCalculator extends SeleneseTestCase {
	
	private int num1;
	private int num2;
	private String oper;
	private double exp_result;
	
	@Parameters
    public static Collection<Object[]> data() throws FileNotFoundException {
		List<Object[]> data = new ArrayList<Object[]>();
		CsvReader reader = new CsvReader("data.csv");

		try {
			reader.readHeaders();
			while (reader.readRecord())
			{
				int num1 = Integer.valueOf(reader.get("num1"));
				String oper = reader.get("oper");
				int num2 = Integer.valueOf(reader.get("num2"));
				double exp_result = Double.valueOf(reader.get("exp_result"));
				
				Object[] singleTest = {num1, oper, num2, exp_result};
				data.add(singleTest);
				
				// perform program logic here

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		reader.close();
        return data;
    }


	
    public CSVCalculator(int num1, String oper, int num2, double result) {
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
		selenium.click(oper);
		selenium.type("calcResults", String.valueOf(num2));
		selenium.click("calequal");
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
