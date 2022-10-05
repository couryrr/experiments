package com.dappercloud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DivisorSumTest {
	
	@Test
	public void caluculateUseCaseOneTest() {
		int result = DivisorSum.caluculate(1);
		assertEquals("Use Case sum of 1 divisor is 1", 1, result);
	}
	
	@Test
	public void caluculateUseCaseSixTest() {
		int result = DivisorSum.caluculate(6);
		assertEquals("Use Case sum of 6 divisor is 12", 12, result);
	}
	
	@Test
	public void caluculateUseCaseSixTeenTest() {
		int result = DivisorSum.caluculate(16);
		assertEquals("Use Case sum of 16 divisor is 31", 31, result);
	}
	
	@Test
	public void caluculateUseCaseTwentyTest() {
		int result = DivisorSum.caluculate(20);
		assertEquals("Use Case sum of 20 divisor is 42", 42, result);
	}

}
