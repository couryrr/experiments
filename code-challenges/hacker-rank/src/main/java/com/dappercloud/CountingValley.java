package com.dappercloud.hackerrank;

/**
 * HackRank Challenge Easy completed Gary is an avid hiker. He tracks his hikes
 * meticulously, paying close attention to small details like topography. During
 * his last hike he took exactly n steps. For every step he took, he noted if it
 * was an uphill, U, or a downhill, D step. Gary's hikes start and end at sea
 * level and each step up or down represents a 1 unit change in altitude. We
 * define the following terms:
 * 
 * A mountain is a sequence of consecutive steps above sea level, starting with
 * a step up from sea level and ending with a step down to sea level. A valley
 * is a sequence of consecutive steps below sea level, starting with a step down
 * from sea level and ending with a step up to sea level. Given Gary's sequence
 * of up and down steps during his last hike, find and print the number of
 * valleys he walked through.
 * 
 * For example, if Gary's path is s=[DDUUUUDD], he first enters a valley 2 units
 * deep. Then he climbs out an up onto a mountain units 2 high. Finally, he
 * returns to sea level and ends his hike.
 *
 */

public class CountingValley {

	/**
	 *
	 * The first line contains an integer n, the number of steps in Gary's hike. The
	 * second line contains a single string s, of n characters that describe his
	 * path.
	 * 
	 * @param n
	 * @param s
	 * @return number of valleys
	 *
	 */

	public int count(int n, String s) {
		int valley = 0;

		int level = 0;

		for (int i = 0; i < n; i++) {
			char step = s.charAt(i);
			if (step == 'D') {
				level--;
			} else { // should be a U
				level++;
			}

			if (step == 'U' && level == 0)
				valley++; // came out of a valley
		}

		return valley;
	}

}
