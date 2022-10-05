package com.dappercloud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class HourGlassAddTest {
	
	@Test
	public void sumHourGlassShapeUseCase1Test() {
		int[][] arr = {{1,1,1,0,0,0},{0,1,0,0,0,0},{1,1,1,0,0,0},{0,0,2,4,4,0},{0,0,0,2,0,0},{0,0,1,2,4,0}};
		int max = HourGlassAdd.getMax(arr);
		
		assertEquals("User case from hacker rank day 11", 19, max);
		
	}
	
	@Test
	public void sumHourGlassShapeUseCase2Test() {
		int[][] arr = {{-1,-1,0,-9,-2,-2},{-2,-1,-6,-8,-2,-5},{-1,-1,-1,-2,-3,-4},{-1,-9,-2,-4,-4,-5},{-7,-3,-3,-2,-9,-9},{-1,-3,-1,-2,-4,-5}};
		int max = HourGlassAdd.getMax(arr);
		assertEquals("User case from hacker rank day 11", -6, max);
	}
	
	@Test
	public void sumHourGlassShapeUseCaseAllMaxTest() {
		int[][] arr = {{9,9,9,9,9,9},{9,9,9,9,9,9},{9,9,9,9,9,9},{9,9,9,9,9,9},{9,9,9,9,9,9},{9,9,9,9,9,9}};
		int max = HourGlassAdd.getMax(arr);
		
		assertEquals("Hour glass shape all 9s should be 63 as max", 63, max);
		
	}
	
	@Test
	public void sumHourGlassShapeUseCaseAllMinTest() {
		int[][] arr = {{-9,-9,-9,-9,-9,-9},{-9,-9,-9,-9,-9,-9},{-9,-9,-9,-9,-9,-9},{-9,-9,-9,-9,-9,-9},{-9,-9,-9,-9,-9,-9},{-9,-9,-9,-9,-9,-9}};
		int max = HourGlassAdd.getMax(arr);
		assertEquals("Hour glass shape all -9s should be -63 as max", -63, max);
	}

}
