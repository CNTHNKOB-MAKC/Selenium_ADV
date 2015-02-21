package com.auto.common;

import java.io.File;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.auto.utils.GloballyUsedFunction;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

public class Environment {

    private static final Logger m_logger = Logger.getLogger(Environment.class.getName());

    public static String TS_HOME;
    public static String TS_WAIT_FOR_PAGE_TO_LOAD;
    public static int TS_WAIT_FOR_IS_VISIBLE;

    private static String SELENIUM_SERVER_HOST;
    private static String SELENIUM_BROWSER_TYPE;
    private static String SELENIUM_BROWSER_URL;
    private static String SELENIUM_FIREFOX_PROFILE;
    private static int SELENIUM_SERVER_PORT;

    static {
            SELENIUM_SERVER_HOST = "localhost";
            SELENIUM_SERVER_PORT = 4444;
            SELENIUM_BROWSER_TYPE = "*firefox3";
            SELENIUM_BROWSER_URL = "http://hrm.tehportal.net";
            SELENIUM_FIREFOX_PROFILE = "Firefox_Profile";

            TS_WAIT_FOR_PAGE_TO_LOAD = "35000";
            TS_WAIT_FOR_IS_VISIBLE = Integer.parseInt("2500");
    }

    private static final long SELENIUM_START_STOP_WAIT = 3000;

    /**
     * Provides a server that can launch/terminate browsers and can receive remote 
     * Selenium commands over HTTP and send them on to the browser.
     */
    private static SeleniumServer m_server = null;

    /**
     * Selenium object represents the browser
     */
    private static Selenium m_browser = null;

	private static Connection m_connection = null;;

    public static final void startSeleniumServer() {
        if (m_server == null) {
            try {
                final RemoteControlConfiguration rcc = new RemoteControlConfiguration();
//                rcc.setFirefoxProfileTemplate(new File(SELENIUM_FIREFOX_PROFILE));
                rcc.setPort(SELENIUM_SERVER_PORT);
                           
                rcc.setUserExtensions(new File("lib//user-extensions.js"));
                rcc.setTimeoutInSeconds(60);
//                rcc.setSingleWindow(true);

                m_server = new SeleniumServer(false, rcc);
            } catch (Exception ex) {
                m_logger.log(Level.SEVERE, "Could not create Selenium Server because of: ", ex);
            }
            if (m_server != null) {
                try {
                	m_server.boot();
                    m_server.start();
                    GloballyUsedFunction.sleep(SELENIUM_START_STOP_WAIT);
                } catch (Exception ex) {
                    m_logger.log(Level.SEVERE, "Could not start Selenium Server because of: ", ex);
                    m_server = null;
                }
            }
        }
    }

    public static final void stopSeleniumServer() {
        if (m_server != null) {
            m_server.stop();
            m_server = null;
            GloballyUsedFunction.sleep(SELENIUM_START_STOP_WAIT);
        }
    }

    public static WebDriver getDriver(DesiredCapabilities browser) throws Exception {
    	DesiredCapabilities capability = browser;
		WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
		return driver;
    }
    
    public static final void initBrowser() {
        if (m_browser == null) {
            m_browser = new DefaultSelenium( SELENIUM_SERVER_HOST
                                           , SELENIUM_SERVER_PORT
                                           , SELENIUM_BROWSER_TYPE
                                           , SELENIUM_BROWSER_URL);
            m_browser.start();
            GloballyUsedFunction.sleep(SELENIUM_START_STOP_WAIT);
        }
    }

    public static final Selenium getBrowser() {
        initBrowser();
        return m_browser;
    }

    public static final void killBrowser() {
        if (m_browser != null) {
            m_browser.stop();
            m_browser = null;
            GloballyUsedFunction.sleep(SELENIUM_START_STOP_WAIT);
        }
    }
    
    public static void startDBConnection() {
    	if(m_connection == null) {
    		String url = "jdbc:mysql://hrm.tehportal.net:3306/";
    		String database_name = "web3db4";
    		String db_Driver = "com.mysql.jdbc.Driver";
    		String username = "web3u4";
    		String password = "password";

    		try {
    			Class.forName(db_Driver).newInstance();
    			m_connection  = DriverManager.getConnection(url + database_name, username, password);

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    public static Connection getDBConnection() {
    	startDBConnection();
    	return m_connection;
    }
    
    public static void stopDBConnection() {
    	if(m_connection != null) {
    		try {
    			m_connection.close();
    			m_connection = null;
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	}
    }

	public static void setURL(String url) {
		SELENIUM_BROWSER_URL = url;
	}
}
