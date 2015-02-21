package base;

import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public abstract class AndroidTestCase extends UiAutomatorTestCase {
	protected AndroidDevice device;
	protected UiSelector by;
	
	protected void setUp() throws Exception {
		super.setUp();
		device = new AndroidDevice();
		by = new UiSelector();
	}

	public AndroidDevice getDevice() {
		return device;
	}
}
