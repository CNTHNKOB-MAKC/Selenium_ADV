package com.auto.core.HW;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

@SuppressWarnings("deprecation")
public class HW4_CreateUserAccount extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://hrm.tehportal.net/", "*chrome");
	}
	public void testHW4_CreateUserAccount() throws Exception {
		selenium.open("/login.php");
		selenium.type("txtUserName", "admin");
		selenium.type("txtPassword", "password");
		selenium.click("Submit");
		selenium.waitForPageToLoad("45000");
		selenium.click("//li[@id='pim']/ul/li[2]/a/span");
		selenium.waitForPageToLoad("45000");
//		String code = selenium.getEval("Math.ceil(Math.random()*99999999)");
		int code = (int)Math.ceil(Math.random()*99999999);
		
		// This is advanced technique to ensure that the emp code is always at least 4 digits long. This is necessary because we are using it to set the password, which has to be at least 4 characters.
		selenium.type("txtEmployeeId", String.valueOf(code));

		String lName = randomName(10);  
		 

		selenium.type("txtEmpLastName", lName);
		String fName = randomName(7);
		selenium.type("//input[@id='txtEmpFirstName']", fName);
		selenium.click("btnEdit");
		selenium.waitForPageToLoad("45000");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isElementPresent("//*[@id=\"personal\"]")) break; } catch (Exception e) {}
			Thread.sleep(1000);
		}

		selenium.click("//li[@id='admin']/ul/li[7]/ul/li[2]/a/span");
		selenium.waitForPageToLoad("45000");
		selenium.click("//input[@value='Add']");
		selenium.waitForPageToLoad("45000");
		selenium.type("txtUserName", fName + lName);
		// Alternative way to get the same result
		System.out.println(selenium.getEval("\"The login is: \" + '" + fName + "' + '" + lName + "'"));
		selenium.type("txtUserPassword", String.valueOf(code));
		selenium.type("txtUserConfirmPassword", String.valueOf(code));
		selenium.type("txtUserEmpID", selenium.getEval("'" + fName + "' + \" \" + '" + lName + "'"));
		// Alternative way to get the same result
		System.out.println(fName + " " + lName);
		selenium.click("editBtn");
		selenium.waitForPageToLoad("45000");
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("45000");
		selenium.type("txtUserName", fName + lName);
		selenium.type("txtPassword", String.valueOf(code));
		selenium.click("Submit");
		selenium.waitForPageToLoad("45000");
		verifyEquals("Welcome " + fName, selenium.getText("//ul[@id='option-menu']/li[1]"));
		selenium.click("link=Logout");
		selenium.waitForPageToLoad("45000");
	}
	private String randomName(int numLetters) {
		String name = "";
		for (int i = 0; i < numLetters; i++) {  
			char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz".toCharArray();  
			double length = Math.ceil(Math.random() * 10);  
			int letter = (int)Math.floor( Math.random() * chars.length);
			name = name + chars[letter]; 
		} 
		return name;
	}
}
