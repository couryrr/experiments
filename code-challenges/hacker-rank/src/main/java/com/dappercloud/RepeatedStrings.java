package com.dappercloud.hackerrank;

/**
 * 
 * Lilah has a string, s, of lowercase English letters that she repeated
 * infinitely many times.
 * 
 * Given an integer, n, find and print the number of letter a's in the first n
 * letters of Lilah's infinite string.
 * 
 * For example, if the string s='abcac' and n=10, the substring we consider is
 * abcacabcac, the first 10 characters of her infinite string. There are
 * occurrences 4 of a in the substring.
 *
 */
public class RepeatedStrings {

	/**
	 * Complete the repeatedString function in the editor below. It should return an
	 * integer representing the number of occurrences of a in the prefix of length n
	 * in the infinitely repeating string.
	 * 
	 * @param s
	 * @param n
	 * @return
	 */
	public long repeatedString(String s, long n) {

		// Full length of string
		int len = s.length();

		// The left over of the extra chars to get to n
		long modulus = n % len;

		// number of the char "a" that appear in the string
		long count = s.chars().filter(c -> c == 'a').count();

		// number of the char "a" that appear in the sub-string to the modulus.
		long extras = s.chars().limit(modulus).filter(c -> c == 'a').count();

		// full math of aba to 10 chars
		// 10/3 * 2 + 10%3
		// 3*2+1
		// 6 + 1
		// 7
		return n / len * count + extras;

	}

}
