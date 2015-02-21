package com.auto.inclass;

import com.auto.common.Environment;
import com.thoughtworks.selenium.*;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FlexMonkiumExample {
	private Selenium selenium;
	private HttpCommandProcessor proc;

	@Before
	public void setUp() throws Exception {
		Environment.startSeleniumServer();
		proc = new HttpCommandProcessor("localhost", 4444, "*chrome", "http://www.gorillalogic.com/");
		selenium = new DefaultSelenium(proc);
		selenium.start();
	}

	@After
	public void tearDown() throws Exception {
		if (selenium != null) {
			selenium.stop();
			selenium = null;
		}
		Environment.stopSeleniumServer();
	}

	@Test
	public void myTestMethod() throws Exception {
		selenium.open("/userfiles/MonkeyContacts/MonkeyContacts.html");
		selenium.setSpeed("1000");
	
		executeFlex(new String[] {"<UIEvent command=\"SelectText\" value=\"inName\">   <arg value=\"0\"/>   <arg value=\"0\"/> </UIEvent>"});
		executeFlex(new String[] {"<UIEvent command=\"Input\" value=\"inName\"><arg value=\"Bob\"/></UIEvent>"});

//		Replace all similar patterns with the function call to executeFlex(String [] args)		
//		for (int t = 0;; t++) {
//			if (t >= 60) fail("timeout");
//			try {
//				if (proc.getBoolean("isFlexMonkey", new String[] {"<VerifyProperty value=\"inName\" propertyString=\"text\" expectedValue=\"Bob\"/>"})) break;
//			} catch (Exception e) { }
//			Thread.sleep(500);
//		}
		
		executeFlex(new String[] {"<VerifyProperty value=\"inName\" propertyString=\"text\" expectedValue=\"Bob\"/>"});		
		executeFlex(new String[] {"<UIEvent command=\"ChangeFocus\" value=\"inName\"/>"});
		executeFlex(new String[] {"<UIEvent command=\"Open\" value=\"inType\"/>"});
		executeFlex(new String[] {"<UIEvent command=\"Select\" value=\"inType\"><arg value=\"Mobile\"/></UIEvent>"});
		executeFlex(new String[] {"<UIEvent command=\"SelectText\" value=\"inPhone\"><arg value=\"0\"/><arg value=\"0\"/></UIEvent>"});
		executeFlex(new String[] {"<VerifyProperty value=\"Add\" propertyString=\"label\" expectedValue=\"Add\"/>"});
		executeFlex(new String[] {"<UIEvent command=\"Input\" value=\"inPhone\"><arg value=\"122345566\"/></UIEvent>"});
		executeFlex(new String[] {"<UIEvent command=\"Open\" value=\"inDate\"/>"});
		executeFlex(new String[] {"<UIEvent command=\"Change\" value=\"inDate\"><arg value=\"Sat Sep 29 2012\"/></UIEvent>"});
		executeFlex(new String[] {"<UIEvent command=\"Click\" value=\"Add\"/>"});

	}
	
	
	private void executeFlex(String [] args) throws InterruptedException {
		for (int t = 0;; t++) {
			if (t >= 60) fail("timeout");
			try {
				if (proc.getBoolean("isFlexMonkey", args)) break;
			} catch (Exception e) { }
			Thread.sleep(500);
		}
		
	}
}
