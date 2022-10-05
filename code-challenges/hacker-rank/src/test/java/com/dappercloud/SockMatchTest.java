package com.dappercloud.hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class SockMatchTest {
		
	@Test
	public void testNone() {
		SockMatch sm = new SockMatch();
		//Provided
		// 0
		// 
		int res = sm.match(0, new int[]{});
		
		Assert.assertEquals("There should be 0 matches", 0, res);
	}
	
	@Test
	public void testOne() {
		SockMatch sm = new SockMatch();
		//Provided
		// 1
		// 10
		int res = sm.match(1, new int[]{10});
		
		Assert.assertEquals("There should be 0 matches", 0, res);
	}
	
	@Test
	public void testTwoWithMatch() {
		SockMatch sm = new SockMatch();
		//Provided
		// 2
		// 10, 10
		int res = sm.match(1, new int[]{10, 10});
		
		Assert.assertEquals("There should be 1 matches", 1, res);
	}
	
	@Test
	public void testTwoWithNoMatch() {
		SockMatch sm = new SockMatch();
		//Provided
		// 2
		// 10, 11
		int res = sm.match(1, new int[]{10, 11});
		
		Assert.assertEquals("There should be 0 matches", 0, res);
	}
	
	@Test
	public void testFromHankerRank() {
		SockMatch sm = new SockMatch();
		//Provided
		// 9
		// 10 20 20 10 10 30 50 10 20
		int res = sm.match(9, new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20});
		
		Assert.assertEquals("There should be 3 matches", 3, res);
	}
	
}
