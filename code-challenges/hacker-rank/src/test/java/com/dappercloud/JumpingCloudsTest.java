package com.dappercloud.hackerrank;

import org.junit.Assert;
import org.junit.Test;

public class JumpingCloudsTest {

	@Test
	public void testCaseOne() {
		JumpingClouds jc = new JumpingClouds();
		// 6
		// 0 0 0 0 1 0
		
		int res = jc.jump(new int[] {0, 0,0, 0, 1, 0});
		
		Assert.assertEquals("There should be 3 jumps", 3, res);
	}
	

	
	@Test
	public void testCaseTwo() {
		JumpingClouds jc = new JumpingClouds();
		// 	7
		// 0 0 1 0 0 1 0
		
		int res = jc.jump(new int[] {0, 0, 1, 0, 0, 1, 0});
		
		Assert.assertEquals("There should be 4 jumps", 4, res);
	}

}
