package com.dappercloud;

class DivisorSum {

	public static int caluculate(int n) {
		int sum = 0;
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				//do not duplicate
				if(i != n/i) {
					sum = sum + i + (n / i);
				} else {
					sum = sum + i;
				}
			}
		}
		return sum;
	}
}