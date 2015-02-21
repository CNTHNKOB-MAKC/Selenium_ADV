package com;

import com.auto.core.MyTestSuite;
import com.auto.core.HW.HomeworksTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class RegressionTestSuite {

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTest(HomeworksTestSuite.suite());
		suite.addTest(MyTestSuite.suite());
		return suite;
	}

}
