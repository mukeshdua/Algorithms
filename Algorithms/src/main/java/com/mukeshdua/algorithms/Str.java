package com.mukeshdua.algorithms;

public class Str {

	// Return excel sheet column number from string
	public static int titleToNumber(String s) {
		if (s == null) {
			return 0;
		}

		int sum = 0;
		int i = s.length() - 1;
		int t = 0;
		while (i >= 0) {
			int curr = s.charAt(i) - 64;
			sum = sum + (int) Math.pow(26, t) * curr;
			t++;
			i--;
		}
		return sum;
	}

}
