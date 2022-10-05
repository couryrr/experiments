package com.dappercloud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RecursionTest {

	@Test
	public void testFactorial3UseCase() {
		int result = Recursion.factorial(3);
		assertEquals("Factorial of 3 should be 6", 6, result);
	}
	
	@Test
	public void testFactorial6UseCase() {
		int result = Recursion.factorial(6);
		assertEquals("Factorial of 6 should be 720", 720, result);
	}
}
