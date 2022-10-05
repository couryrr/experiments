package com.dappercloud;

public class BinaryNumber {
	public static String convertToBinary(int n) {
		return Integer.toBinaryString(n);
	}
	
	public static int maxConsecutiveOnes(int n) {
		String binary = BinaryNumber.convertToBinary(n);
		int max = 0;
		int count = 0;
		for(int i = 0; i <= binary.length()-1; i++) {
			if(binary.charAt(i) == '1') {
				count += 1;
			} else {
				if(count > max){
					max = count;
				}
				count = 0;
			}
		}
		
		return count > max ? count : max;
	}

}
