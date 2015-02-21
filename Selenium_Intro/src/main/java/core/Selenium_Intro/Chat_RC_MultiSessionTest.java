
import static junit.framework.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import com.auto.common.Environment;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;


public class Chat_RC_MultiSessionTest {

	Selenium ellie;
	Selenium jane;
	
	@Before
	public void setUp() throws Exception {
		Environment.startSeleniumServer();
		ellie = new DefaultSelenium("localhost", 4444, "*chrome", "http://www.chatzy.com/");
		jane = new DefaultSelenium("localhost", 4444, "*googlechrome", "http://www.chatzy.com/");
		
		ellie.start();
		jane.start();
		
		ellie.setSpeed("1000");
		jane.setSpeed("1000");
		
	}

	
	@Test
	public void test() {
		ellie.open("/");
		ellie.type("id=X370", "Ellie");
		ellie.type("id=X9", "Chat Test");
		ellie.type("id=X371", "some_email@mail.com");
		ellie.click("css=input[type=\"submit\"]");
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (ellie.isElementPresent("css=input[type=\"button\"][value=\"OK\"]")) break; } catch (Exception e) {}
			pause(1);
		}

		ellie.click("css=input[type=\"button\"]");
		
		String URL = ellie.getLocation();
		int index = URL.lastIndexOf("/");
		String URI = URL.substring(index);
		
		// ----
		jane.open(URI);
		jane.type("id=X370", "Jane");
		jane.select("id=X900", "label=Orange");
		jane.click("id=X490");
		jane.waitForPageToLoad("45000");
		jane.type("id=X130", "Hi");
		jane.click("//a[@id='X204']");
		assertEquals("Jane: Hi", jane.getText("//td[@id='X165']/p[last()]"));
		
		// ----
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Jane".equals(ellie.getText("//p[last()]/b"))) break; } catch (Exception e) {}
			pause(1);
		}

		assertEquals("Jane: Hi", ellie.getText("//td[@id='X165']/p[last()]"));
		ellie.type("id=X130", "How are you?");
		ellie.click("//a[@id='X204']");
		assertEquals("Ellie: How are you?", ellie.getText("//td[@id='X165']/p[last()]"));
		
		// ----
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Ellie".equals(jane.getText("//p[last()]/b"))) break; } catch (Exception e) {}
			pause(1);
		}

		assertEquals("Ellie: How are you?", jane.getText("//td[@id='X165']/p[last()]"));
		jane.type("id=X130", "Great! Thanks, and you?");
		jane.click("//a[@id='X204']");
		assertEquals("Jane: Great! Thanks, and you?", jane.getText("//td[@id='X165']/p[last()]"));
		
		// ----
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Jane".equals(ellie.getText("//p[last()]/b"))) break; } catch (Exception e) {}
			pause(1);
		}

		assertEquals("Jane: Great! Thanks, and you?", ellie.getText("//td[@id='X165']/p[last()]"));
		ellie.type("id=X130", "I am doing very well too!");
		ellie.click("//a[@id='X204']");
		assertEquals("Ellie: I am doing very well too!", ellie.getText("//td[@id='X165']/p[last()]"));
		
		// ----
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Ellie".equals(jane.getText("//p[last()]/b"))) break; } catch (Exception e) {}
			pause(1);
		}

		assertEquals("Ellie: I am doing very well too!", jane.getText("//td[@id='X165']/p[last()]"));
		jane.type("id=X130", "Sorry... I have to go. Bye");
		jane.click("//a[@id='X204']");
		assertEquals("Jane: Sorry... I have to go. Bye", jane.getText("//td[@id='X165']/p[last()]"));

		// ----
		for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if ("Jane".equals(ellie.getText("//p[last()]/b"))) break; } catch (Exception e) {}
			pause(1);
		}

		assertEquals("Jane: Sorry... I have to go. Bye", ellie.getText("//td[@id='X165']/p[last()]"));

		// ---------------------	
	}
	@After
	public void tearDown() throws Exception {
		ellie.stop();
		jane.stop();
		Environment.stopSeleniumServer();
	}

	private static void pause(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
