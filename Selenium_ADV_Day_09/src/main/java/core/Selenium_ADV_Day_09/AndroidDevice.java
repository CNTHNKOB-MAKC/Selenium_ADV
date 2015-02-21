package base;

import static junit.framework.Assert.*;
import android.os.RemoteException;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;

public class AndroidDevice extends WrappedUiDevice{

	public void openApp(String appName) {
		mDevice.pressHome();
		
		try {
			UiObject allAppsButton = findElement(by.description("Apps"));
			allAppsButton.clickAndWaitForNewWindow();
			
			UiObject appsTab = findElement(by.text("Apps"));
			if(!appsTab.isSelected()) appsTab.click();

			UiScrollable appViews = new UiScrollable(by.scrollable(true));
			appViews.setAsHorizontalList();
			appViews.scrollIntoView(by.text(appName));
			appViews.getChild(by.text(appName)).clickAndWaitForNewWindow();
			
//			UiObject mobileNow = appViews.getChild(by.text(appName));
//			mobileNow.clickAndWaitForNewWindow();
			
			
//			UiObject settingsApp = appViews.getChildByText(by.className(android.widget.TextView.class.getName()),"Settings");

		} catch (UiObjectNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public void unlockDeviceUsingPin(int pin) throws UiObjectNotFoundException  {
		String str_pin = String.valueOf(pin);
		assertTrue("Invalid pin: " + str_pin, str_pin.length() >= 4);	
		try {
			mDevice.wakeUp();
		} catch (RemoteException e) {
			System.out.println("Could not detect an active device or emulator on this system.");
			e.printStackTrace();
		}
		
		for (String num : str_pin.split("")) {
			if(!num.isEmpty()) findElement(by.className(android.widget.Button.class).textStartsWith(num)).click();
		}
		
		UiObject enter_button = findElement(by.description("Enter"));
		enter_button.clickAndWaitForNewWindow();
// or this:		enter_button.waitUntilGone(1000);
	}
	
	public void uninstallApp(String appName) throws UiObjectNotFoundException {
		openApp("Settings");
		UiScrollable settingsScrollableView = new UiScrollable(by.className(android.widget.ListView.class).scrollable(true));
		settingsScrollableView.getChild(by.text("Apps")).click();
		settingsScrollableView.waitUntilGone(500);
		UiScrollable apps_settingsScrollableView = new UiScrollable(by.className(android.widget.ListView.class).scrollable(true));
		apps_settingsScrollableView.getChild(by.text(appName)).click();
		apps_settingsScrollableView.waitUntilGone(500);
		findButtonByText("Uninstall").click();
		findButtonByText("OK").clickAndWaitForNewWindow();
		assertFalse("Removing downloaded app failed: " + appName,apps_settingsScrollableView.getChild(by.text(appName)).exists());
	}

}