
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.auto.common.Environment;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;


public class MultiWindow {
	
	Selenium selenium;
	
	@Before
	public void setUp() throws Exception {
		Environment.startSeleniumServer();
		selenium = new DefaultSelenium("localhost", 4444, "*firefox", "http://www.google.com/");
		selenium.start();
	}

	@Test
	public void testFindWindowByTitle() {
		selenium.open("/");
		selenium.windowMaximize();
		selenium.openWindow("http://maps.google.com", "");
		selenium.windowMaximize();
		selenium.openWindow("http://news.google.com", "");
		selenium.windowMaximize();
		
		String[] windows = selenium.getAllWindowTitles();
		
		selenium.selectWindow(windows[0]);
		selenium.close();
		
		selenium.selectWindow(windows[1]);
		selenium.close();
		
		// The only window remaining at this point should be GoogleNews
	}
	
	@After
	public void tearDown() throws Exception {
		selenium.stop();
		Environment.stopSeleniumServer();
	}

}
