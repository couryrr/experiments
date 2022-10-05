package com.dappercloud;

public class InfinitelyRepeatingLetter {

	// Complete the repeatedString function below.
	static long repeatedString(String s, long n) {
		
		//Full length of string
		int len = s.length();
		
		//The left over of the extra chars to get to n 
		long modulus = n % len;

		//number of the char "a" that appear in the string
		long count = s.chars().filter(c -> c == 'a').count();

		//number of the char "a" that appear in the sub-string to the modulus.
		long extras = s.chars().limit(modulus).filter(c -> c == 'a').count();

		//full math of aba to 10 chars
		//10/3 * 2 + 10%3
		//3*2+1
		//6 + 1
		//7
		return n / len * count + extras;
	}

	public static void main(String[] args) {
		String s = "aba";

		long n = 10;

		long result = repeatedString(s, n);
		System.out.println(result);

	}
}
