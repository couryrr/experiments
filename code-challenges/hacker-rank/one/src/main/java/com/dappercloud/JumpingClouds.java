package com.dappercloud;

import java.io.IOException;

public class JumpingClouds {

	// Complete the jumpingOnClouds function below.
	static int jumpingOnClouds(int[] c) {

		int index = 0;
		int moves = 0;
		int len = c.length - 1;
		while (index < len) {
			int space = index+2 > len ? 1 : 2;
			
			int cloud = c[index + space];
			index += cloud == 0 ? 2 : 1;

			moves++;
		}

		return moves;
	}

	public static void main(String[] args) throws IOException {

		int[] a = { 0, 0, 0, 0, 1, 0 };
		int[] b = { 0, 0, 0, 1, 0, 0 };
		int[] c = { 0, 0, 1, 0, 0, 1, 0 };

		int result = jumpingOnClouds(a);
		System.out.println(result);
		
		result = jumpingOnClouds(b);
		System.out.println(result);
		
		result = jumpingOnClouds(c);
		System.out.println(result);
	}
}
