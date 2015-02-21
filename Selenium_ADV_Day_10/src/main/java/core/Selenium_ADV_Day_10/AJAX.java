package com.example.tests;

import com.thoughtworks.selenium.*;
import java.util.regex.Pattern;

public class AJAX extends SeleneseTestCase {
	public void setUp() throws Exception {
		setUp("http://www.ajaxdaddy.com/", "*chrome");
	}
	public void testAJAX() throws Exception {
		selenium.open("/demo-bsn-autocomplete.html");
		selenium.type("testinput", "a");
		selenium.typeKeys("testinput", "l");
		selenium.waitForCondition("selenium.browserbot.getUserWindow().$.active == 0", "30000");
		selenium.click("//ul[@id='as_ul']/li[1]/a/span[3]");
	}
}
