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

	// Is particular number prime
	public static Boolean isNumberPrime(int number) {
		if (number == 2) {
			return true;
		}
		if (number > 2 && number % 2 == 0) {
			return false;
		}
		int top = (int) (Math.sqrt(number)) + 1;
		for (int count = 3; count < top; count += 2) {
			if (number % count == 0) {
				return false;
			}
		}
		return true;
	}

	// Count number of primes
	public static int countPrimes(int number) {
		if (number <= 2) {
			return 0;
		}
		int primeCount = 1;
		for (int count = 3; count < number; count += 2) {
			if (isNumberPrime(count)) {
				primeCount++;
			}
		}
		return primeCount;

	}

	// Palindrome number
	public static boolean isPalindrome(int num) {
		if (num < 0) {
			return false;
		}
		int original = num;
		int reverse = 0;
		while (num != 0) {
			int remainder = num % 10;
			reverse = reverse * 10 + remainder;
			num = num / 10;
		}
		if (reverse == original) {
			return true;
		}
		return false;
	}

	// Reverse integer
	public static int reverse(int num) {
		long reverse = 0;
		while (num != 0) {
			reverse = reverse * 10 + num % 10;
			num = num / 10;
		}
		if (reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE) {
			reverse = 0;
		}
		return (int) reverse;
	}
	
	//Is particular number of two
	public static boolean isPowerOfTwo(int n) {
		if (n <= 0) {
			return false;
		}
		return (n & (n - 1)) == 0;

	}

}
