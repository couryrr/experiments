package com.dappercloud.hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class CountingValleyTest {
		
	@Test
	public void testCaseOne() {
		CountingValley cv = new CountingValley();
		//Provided
		// 8
		// UDDDUDUU
		int res = cv.count(8, "UDDDUDUU");
		
		Assert.assertEquals("There should be 1 valley", 1, res);
	}
	
	@Test
	public void testCaseTwo() {
		CountingValley cv = new CountingValley();
		//Provided
		// 12
		// DDUUDDUDUUUD
		int res = cv.count(12, "DDUUDDUDUUUD");
		
		Assert.assertEquals("There should be 2 valley", 2, res);
	}
	
	
	
	
}
