package com.auto.core.HW;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

@SuppressWarnings("deprecation")
public class HW5_SearchAllManager extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://chemistry.about.com/", "*chrome");
	}
	public void testHW4_SearchAllManager() throws Exception {
		selenium.open("/login.php");
		selenium.type("txtUserName", "admin");
		selenium.type("txtPassword", "password");
		selenium.click("Submit");
		selenium.waitForPageToLoad("45000");
		selenium.click("//li[@id='pim']/a/span");
		selenium.waitForPageToLoad("45000");
		selenium.select("loc_code", "label=Job Title");
		selenium.type("loc_name", "manager");
		selenium.click("//input[@value='Search']");
		selenium.waitForPageToLoad("45000");
		
		selenium.selectFrame("rightMenu");
		Number row_count = selenium.getXpathCount("/html/body/div/div[2]/form/table/tbody/tr");
		int total_rows = row_count.intValue();
		System.out.println(row_count);
		
		
		int index = 1;
		while(index <= total_rows) 		// selenium.while("${index}<=${row_count}");
		{
//			String xpath = selenium.getEval("\"//form[@id='standardView']/table/tbody/tr[\" + " + index + " + \"]/td[4]\"");
			String xpath = "//form[@id='standardView']/table/tbody/tr[" + index + "]/td[4]";
			verifyEquals("regexpi:Manager", selenium.getText(xpath));
			System.out.println(xpath);
//			index = selenium.getEval(index + "+1");
			index++;
			
		}       // selenium.endWhile();
		
		
		
		



		
				
		selenium.selectWindow("");
		selenium.click("link=Logout");
	}
}
