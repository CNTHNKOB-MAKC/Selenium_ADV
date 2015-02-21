package core.Selenium_Intro1;

import java.util.Date;

public class Wait_Code {


public static boolean waitToLoadElement(WebDriver driver, By by, int seconds) {
    boolean found = true;

    long bailOutPeriod = 1000 * seconds;
    long lStartTime = new Date().getTime();

    while (!isElementPresent(driver, by)) {
        long lEndTime = new Date().getTime();
        long difference = lEndTime - lStartTime;

        if (difference < bailOutPeriod) {
            pause(1);
        }
        else {
            found = false;
            break;
        }
    }
    return found;
}

public static boolean isElementPresent(final WebDriver driver, By by) {
	try {
		WebElement element = driver.findElement(by);
		return element.isDisplayed();
	} catch (NoSuchElementException e) {
		return false;
	}
}

public static void pause(int seconds) {
    try {
        TimeUnit.SECONDS.sleep(seconds);
    }
    catch (InterruptedException e) {
        e.printStackTrace();
    }
}

public static void populateField(WebDriver driver, By locator, String value) {
	WebElement element = driver.findElement(locator);
	element.clear();
	element.sendKeys(value);
}}