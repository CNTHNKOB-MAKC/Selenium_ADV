package com.auto.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyTestCase {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testApple() {
		isItFruit("apple");
	}
	
	@Test
	public void testKiwi() {
		isItFruit("KIWI");
	}
	
	@Test
	public void testPotato() {
		isItFruit("potato");
	}
	
	// Helper function called by the test which performs the actual testing
	public void isItFruit(String fruit) {
		String[] fruits = {"apple", "orange", "banana", "kiwi"};
		boolean result = false;
		for (String item: fruits) {
			if (item.equalsIgnoreCase(fruit)) {
				result = true;
			}
		}
		assertTrue(result);
	}

}
