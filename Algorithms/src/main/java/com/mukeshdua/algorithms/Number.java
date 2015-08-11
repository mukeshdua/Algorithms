package com.mukeshdua.algorithms;

import java.util.HashSet;

public class Number {

	private static int getSquare(int num) {
		return num * num;
	}

	private static int getSquareSum(int num) {
		int squareSum = 0;
		while (num != 0) {
			squareSum += getSquare(num % 10);
			num = num / 10;
		}
		return squareSum;
	}

	// Write an algorithm to determine if a number is "happy".
	public static boolean isHappy(int number) {
		HashSet<Integer> set = new HashSet<Integer>();
		while (!set.contains(number)) {
			set.add(number);
			number = getSquareSum(number);
			if (number == 1) {
				return true;
			}
		}
		return false;
	}

}
