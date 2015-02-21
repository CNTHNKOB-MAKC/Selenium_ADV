package com.auto.core.in_class;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

@SuppressWarnings("deprecation")
public class SimpleLogin extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://hrm.tehportal.net/", "*chrome");
	}
	public void testSimpleLogin() throws Exception {
		int number = 5;
		selenium.open("/login.php");
		selenium.type("txtUserName", "admin");
		number ++;
		selenium.type("txtPassword", "password");
		selenium.click("Submit");
		selenium.waitForPageToLoad("45000");
		verifyTrue(selenium.isTextPresent("Welcome Admin"));
		number += 12;
		selenium.click("link=Logout");
	}
}
