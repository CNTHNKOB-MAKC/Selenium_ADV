package base;

import java.io.File;

import android.graphics.Point;
import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.core.UiWatcher;

public class WrappedUiDevice {
	protected static UiDevice mDevice = getInstance();
	protected static UiSelector by = new UiSelector();
	
	public UiObject findElement(UiSelector by) {
		return new UiObject(by);
	}
	
	public UiScrollable findScrollableCollection(UiSelector by) {
		return new UiScrollable(by.scrollable(true));
	}
	
	public UiObject findButtonByText(String button) {
		return findElement(by.text(button).className(android.widget.Button.class));
	}
	
	public void clearLastTraversedText(){ mDevice.clearLastTraversedText(); }
	public boolean click(int x, int y){ return mDevice.click(x, y); }
	public void dumpWindowHierarchy(String fileName){ mDevice.dumpWindowHierarchy(fileName); }
	public void freezeRotation() throws RemoteException{ mDevice.freezeRotation(); }
	public String getCurrentPackageName(){ return mDevice.getCurrentPackageName(); }
	public int getDisplayHeight(){ return mDevice.getDisplayHeight(); }
	public int getDisplayRotation(){ return mDevice.getDisplayRotation(); }
	public int getDisplayWidth(){ return mDevice.getDisplayWidth(); }
	public static UiDevice getInstance(){ return UiDevice.getInstance(); }
	public String getLastTraversedText(){ return mDevice.getLastTraversedText(); }
	public String getProductName(){ return mDevice.getProductName(); }
	public boolean hasAnyWatcherTriggered(){ return mDevice.hasAnyWatcherTriggered(); }
	public boolean hasWatcherTriggered(String watcherName){ return mDevice.hasWatcherTriggered(watcherName); }
	public boolean isNaturalOrientation(){ return mDevice.isNaturalOrientation(); }
	public boolean isScreenOn() throws RemoteException{ return mDevice.isScreenOn(); }
	public boolean pressBack(){ return mDevice.pressBack(); }
	public boolean pressDPadCenter(){ return mDevice.pressDPadCenter(); }
	public boolean pressDPadDown(){ return mDevice.pressDPadDown(); }
	public boolean pressDPadLeft(){ return mDevice.pressDPadLeft(); }
	public boolean pressDPadRight(){ return mDevice.pressDPadRight(); }
	public boolean pressDPadUp(){ return mDevice.pressDPadUp(); }
	public boolean pressDelete(){ return mDevice.pressDelete(); }
	public boolean pressEnter(){ return mDevice.pressEnter(); }
	public boolean pressHome(){ return mDevice.pressHome(); }
	public boolean pressKeyCode(int keyCode){ return mDevice.pressKeyCode(keyCode); }
	public boolean pressKeyCode(int keyCode, int metaState){ return mDevice.pressKeyCode(keyCode, metaState); }
	public boolean pressMenu(){ return mDevice.pressMenu(); }
	public boolean pressRecentApps() throws RemoteException{ return mDevice.pressRecentApps(); }
	public boolean pressSearch(){ return mDevice.pressSearch(); }
	public void registerWatcher(String name, UiWatcher watcher){ mDevice.registerWatcher(name, watcher); }
	public void removeWatcher(String name){ mDevice.removeWatcher(name); }
	public void resetWatcherTriggers(){ mDevice.resetWatcherTriggers(); }
	public void runWatchers(){ mDevice.runWatchers(); }
	public void setOrientationLeft() throws RemoteException{ mDevice.setOrientationLeft(); }
	public void setOrientationNatural() throws RemoteException{ mDevice.setOrientationNatural(); }
	public void setOrientationRight() throws RemoteException{ mDevice.setOrientationRight(); }
	public void sleep() throws RemoteException{ mDevice.sleep(); }
	public boolean swipe(Point[] segments, int segmentSteps){ return mDevice.swipe(segments, segmentSteps); }
	public boolean swipe(int startX, int startY, int endX, int endY, int steps){ return mDevice.swipe(startX, startY, endX, endY, steps); }
	public boolean takeScreenshot(File storePath, float scale, int quality){ return mDevice.takeScreenshot(storePath, scale, quality); }
	public boolean takeScreenshot(File storePath){ return mDevice.takeScreenshot(storePath); }
	public void unfreezeRotation() throws RemoteException{ mDevice.unfreezeRotation(); }
	public void waitForIdle(long time){ mDevice.waitForIdle(time); }
	public void waitForIdle(){ mDevice.waitForIdle(); }
	public boolean waitForWindowUpdate(String packageName, long timeout){ return mDevice.waitForWindowUpdate(packageName, timeout); }
	public void wakeUp() throws RemoteException { mDevice.wakeUp(); }
	
	@Deprecated
	public String getCurrentActivityName(){ return mDevice.getCurrentActivityName(); }
}
