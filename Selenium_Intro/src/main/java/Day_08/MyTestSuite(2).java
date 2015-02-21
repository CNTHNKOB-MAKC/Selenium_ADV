package com.auto.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.auto.tests.wdb.WDB_ValidLogin;

@RunWith(Suite.class)
@SuiteClasses({ MyTestCase.class, WDB_ValidLogin.class })
public class MyTestSuite {

}
