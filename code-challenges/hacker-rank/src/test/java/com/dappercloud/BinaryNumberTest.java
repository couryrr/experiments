package com.dappercloud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinaryNumberTest {
	
	@Test
	public void convertToBinaryUserCase5Test() {
		String result = BinaryNumber.convertToBinary(5);
		assertEquals("Binary of 5 is 101", "101", result);
	}
	
	@Test
	public void convertToBinaryUserCase13Test() {
		String result = BinaryNumber.convertToBinary(13);
		assertEquals("Binary of 13 is 1101", "1101", result);
	}
	
	@Test
	public void maxConsecutiveOnesUserCase5Test() {
		int result = BinaryNumber.maxConsecutiveOnes(5);
		assertEquals("Max Consecutive Ones in 5 as binary is 1", 1, result);
	}
	
	@Test
	public void maxConsecutiveOnesUserCase13Test() {
		int result = BinaryNumber.maxConsecutiveOnes(13);
		assertEquals("Max Consecutive Ones in 13 as binary is 2", 2, result);
	}
	
	@Test
	public void maxConsecutiveOnesUserCase439Test() {
		//Edge case where the end count never hits 0 so max is not set.
		int result = BinaryNumber.maxConsecutiveOnes(439);
		assertEquals("Max Consecutive Ones in 439 as binary is 3", 3, result);
	}

}
