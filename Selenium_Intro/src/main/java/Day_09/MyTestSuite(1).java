package com.auto.core;
import com.auto.QAutoTestSuite;

import junit.framework.Test;
import junit.framework.TestSuite;

public class MyTestSuite extends QAutoTestSuite{

	public static Test suite() {
		TestSuite suite = new QAutoTestSuite();
		suite.addTestSuite(LoginTestCase.class);
		suite.addTestSuite(SearchTestCase.class);
		suite.addTest(new ManyTestsTestCase("testLoginTestCase"));
		return suite;
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
