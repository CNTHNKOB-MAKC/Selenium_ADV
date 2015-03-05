package EllieClass3InClass;

import org.junit.Test;
import org.omg.CORBA.Environment;

public class InClassReturnAsList {
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
	
}
