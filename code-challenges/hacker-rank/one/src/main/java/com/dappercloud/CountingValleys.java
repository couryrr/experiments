package com.dappercloud;

import java.io.IOException;

public class CountingValleys {

	// Complete the countingValleys function below.
	static int countingValleys(int n, String s) {
		
		int valley = 0;

		int level = 0;
		
		for(int i = 0; i < n; i++) {
			char step = s.charAt(i);
			if(step =='D') {
				level--;
			} else { //should be a U
				level++;
			}
			if(step == 'U' && level == 0) valley++; //came out of a valley
		}
		
		return valley;
	}

	public static void main(String[] args) throws IOException {

		int n = 12;


		int result = countingValleys(n, s);
		
		System.out.println(result);

	}

}