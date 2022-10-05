package com.dappercloud;

public class GenericPrint {

	public static <T> void printArray(T[] a) {
		for (T i : a) {
			System.out.println(i);
		}
	}
}
