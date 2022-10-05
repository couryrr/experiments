package com.dappercloud;

public class Recursion {
	public static int factorial(int n) {
		// Had a variable set but since returns are needed removed it.
		if(n >= 1) {
			// Method calls are a stack so each call until 1 is 
			// multipled by the last calls return.
			// minus one so we are going down.
			return n*factorial(n-1);
		} else {
			return 1;
		}
	}

}
