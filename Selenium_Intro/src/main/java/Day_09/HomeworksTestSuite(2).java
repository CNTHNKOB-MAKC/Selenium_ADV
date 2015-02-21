package com.auto.core.HW;

import com.auto.QAutoTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class HomeworksTestSuite {

	public static Test suite() {
		TestSuite suite = new QAutoTestSuite();
		suite.addTestSuite(AutoCompleteTestCase.class);
		suite.addTest(new LeaveSummaryTestCase("testLeaveSummaryTestCase"));
		return suite;
	}

}
