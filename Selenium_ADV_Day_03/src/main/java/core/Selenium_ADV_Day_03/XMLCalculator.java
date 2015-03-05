
package core.Selenium_ADV_Day_03;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.w3c.dom.*;

import com.auto.common.Environment;
import com.thoughtworks.selenium.*;

@RunWith(Parameterized.class)
public class XMLCalculator extends SeleneseTestCase {
	
	private int num1;
	private int num2;
	private String oper;
	private double exp_result;
	
	@Parameters
    public static Collection<Object[]> data() throws FileNotFoundException {
		List<Object[]> data = new ArrayList<Object[]>();
		try {

	        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
	        Document doc = docBuilder.parse (new File("data.xml"));

	        // normalize text representation
	        doc.getDocumentElement().normalize();

	        NodeList listOfTests = doc.getElementsByTagName("test");
	        int totalTests = listOfTests.getLength();
	        System.out.println("Total number of tests: " + totalTests);

	        for(int s=0; s<listOfTests.getLength() ; s++){


	            Node firstTestNode = listOfTests.item(s);
	            if(firstTestNode.getNodeType() == Node.ELEMENT_NODE){


	                Element firstTestElement = (Element)firstTestNode;

	                //-------
	                NodeList firstNumList = firstTestElement.getElementsByTagName("num1");
	                Element firstNumElement = (Element)firstNumList.item(0);

	                NodeList textFNList = firstNumElement.getChildNodes();
	                int num1 = Integer.valueOf(((Node)textFNList.item(0)).getNodeValue().trim());

	                //-------
	                NodeList secondNumList = firstTestElement.getElementsByTagName("num2");
	                Element secondNumElement = (Element)secondNumList.item(0);

	                NodeList textSNList = secondNumElement.getChildNodes();
	                int num2 = Integer.valueOf(((Node)textSNList.item(0)).getNodeValue().trim());
	                
	                //----
	                NodeList operList = firstTestElement.getElementsByTagName("oper");
	                Element operElement = (Element)operList.item(0);

	                NodeList textOperList = operElement.getChildNodes();
	                String oper = ((Node)textOperList.item(0)).getNodeValue().trim();

	                //------

	                NodeList resultList = firstTestElement.getElementsByTagName("result");
	                Element resultElement = (Element)resultList.item(0);

	                NodeList textRList = resultElement.getChildNodes();
	                double result = Double.valueOf(((Node)textRList.item(0)).getNodeValue().trim());
	                
					Object[] singleTest = {num1, oper, num2, result};
					data.add(singleTest);
					
	            }//end of if clause	            
	        }//end of for loop with s var


		}catch (Throwable t) {
			t.printStackTrace ();
	    }
        return data;
    }


	
    public XMLCalculator(int num1, String oper, int num2, double result) {
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
