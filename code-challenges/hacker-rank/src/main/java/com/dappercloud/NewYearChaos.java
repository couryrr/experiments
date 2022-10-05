package com.dappercloud;

public class NewYearChaos {

	static void minimumBribes(int[] q) {
		boolean possible = true;
		int bribes = 0;
		int index = 0;

		// Check the value of current index to index
		while (index < q.length) {
			int value = q[index];
			if (possible) {
				int place = index + 1;
				if (value > place) {
					if (value != index + 1) {

						if (value != place + 2) {
							if (value != place + 1) {
								possible = false;
							} else {
								bribes += 1;
							}
						} else {
							bribes += 2;
						}
					}
				}
				index++;

			} else {
				break;
			}
		}

		if (possible) {
			System.out.println(bribes);
		} else {
			System.out.println("Too chaotic");
		}
	}

	public static void main(String[] args) {
		int[] q = {1,2, 5, 3, 7, 8, 6, 4};
		minimumBribes(q);
	}

}
