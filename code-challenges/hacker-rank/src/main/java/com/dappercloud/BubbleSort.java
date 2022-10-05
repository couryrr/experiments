package com.dappercloud;

public class BubbleSort {
	public static void sort(int n, int[] a) {
		int totalNumberOfSwaps = 0;

		for (int i = 0; i < n; i++) {
			// Track number of elements swapped during a single array traversal
			int numberOfSwaps = 0;

			for (int j = 0; j < n - 1; j++) {
				// Swap adjacent elements if they are in decreasing order
				if (a[j] > a[j + 1]) {
					int aj = a[j];
					int ajo = a[j + 1];
					a[j] = ajo;
					a[j + 1] = aj;
					// swap(a[j], a[j + 1]);
					numberOfSwaps++;
				}
			}
			totalNumberOfSwaps += numberOfSwaps;

			// If no elements were swapped during a traversal, array is sorted
			if (numberOfSwaps == 0) {
				break;
			}
		}
		System.out.println(String.format("Array is sorted in %s swaps.", totalNumberOfSwaps));
		System.out.println(String.format("First Element: %s", a[0]));
		System.out.println(String.format("Last Element: %s", a[n-1]));
	}
}
