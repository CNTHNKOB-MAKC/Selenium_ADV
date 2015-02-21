package android_tests;

import com.android.uiautomator.core.UiObject;

import android.os.RemoteException;
import base.AndroidTestCase;

public class MyAndroidTestCase extends AndroidTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testUnlockDevice() throws Exception {
		device.unlockDeviceUsingPin(1111);
		device.openApp("Clock");
		UiObject timer_btn = device.findElement(by.description("Timer"));
		timer_btn.waitForExists(1000);
		timer_btn.clickAndWaitForNewWindow(1000);
		assertTrue(device.findButtonByText("Start").exists());
		
		device.findElement(by.description("Stopwatch")).clickAndWaitForNewWindow(1000);
		device.findElement(by.text("Start")).click();
		UiObject stop_btn = device.findElement(by.text("Stop"));
		stop_btn.waitForExists(1000);
		stop_btn.click();
		stop_btn.waitUntilGone(1000);
		assertTrue(device.findElement(by.text("Start")).exists());
		device.sleep();
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
