package com.dappercloud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class HourGlassAdd {
    public static Integer getMax(int[][] arr) {
        Collection<Integer> sums = new ArrayList<Integer>();
        for(int r = 0; r < arr.length; r++) {
        	for(int c = 0; c < arr[r].length; c++) {
        		//Smallest number is all numbers being -9 ie -9*7.
        		int sum = -9*7;
    			//Cannot go out of bounds
    			if(r+2 < 6 && c+2 <6) {
    				sum = arr[r][c] 
						+ arr[r][c+1]
						+ arr[r][c+2] 
						+arr[r+1][c+1]
						+arr[r+2][c] + arr[r+2][c+1]+arr[r+2][c+2];
    			}
    			sums.add(sum);
        	}
        }
       
        return Collections.max(sums);
    }
}
