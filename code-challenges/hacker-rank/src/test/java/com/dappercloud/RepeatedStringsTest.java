package com.dappercloud.hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class RepeatedStringsTest {

	@Test
	public void testCaseOne() {
		RepeatedStrings rs = new RepeatedStrings();
		// Provided
		// aba
		// 10
		long res = rs.repeatedString("aba", 10);

		Assert.assertEquals("There should be 7 valley", 7, res);
	}
	
	@Test
	public void testCaseTwo() {
		RepeatedStrings rs = new RepeatedStrings();
		// Provided
		// a
		// 1000000000000
		long res = rs.repeatedString("a", 1000000000000L);

		Assert.assertEquals("There should be 1000000000000 valley", 1000000000000L, res);
	}

}
