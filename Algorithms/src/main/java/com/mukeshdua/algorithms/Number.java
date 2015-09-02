package com.mukeshdua.algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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

	/**
	 * Write an algorithm to determine if a number is "happy".
	 * 
	 * @param number
	 * @return
	 */
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

	/**
	 * Is particular number prime
	 * 
	 * @param number
	 * @return
	 */
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

	/**
	 * Count number of primes
	 * 
	 * @param number
	 * @return
	 */
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

	/**
	 * Palindrome number
	 * 
	 * @param num
	 * @return
	 */
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

	/**
	 * Reverse integer
	 * 
	 * @param num
	 * @return
	 */
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

	/**
	 * Is particular number of two
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPowerOfTwo(int n) {
		if (n <= 0) {
			return false;
		}
		return (n & (n - 1)) == 0;

	}

	/***
	 * Find trailing zeros in factorial
	 */
	public int trailingZeroes(int n) {
		if (n < 0)
			return -1;

		int count = 0;
		for (long i = 5; n / i >= 1; i *= 5) {
			count += n / i;
		}

		return count;
	}

	/**
	 * Sum Digit until sum is not less than 0
	 * 
	 * @param num
	 * @return
	 */
	public static int sumDigit(int num) {
		int sum = 0;
		while (num != 0) {
			sum = sum + num % 10;
			num = num / 10;
			if (num == 0 && sum >= 10) {
				num = sum;
				sum = 0;
			}
		}
		return sum;
	}

	/**
	 * You are climbing a stair case. It takes n steps to reach to the top. Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb
	 * to the top?
	 */
	public int climbStairs(int n) {
		if (n < 4)
			return n;
		int a = 2, b = 3, c = 5;
		for (int i = 5; i <= n; i++) {
			a = c;
			c = b + c;
			b = a;
		}
		return c;

	}

	private static final String[]	specialNames	= { "", " thousand", " million", " billion", " trillion", " quadrillion", " quintillion" };

	private static final String[]	tensNames		= { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty", " seventy", " eighty", " ninety" };

	private static final String[]	numNames		= { "", " one", " two", " three", " four", " five", " six", " seven", " eight", " nine", " ten", " eleven",
			" twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen", " eighteen", " nineteen" };

	private static String convertLessThanOneThousand(int number) {
		String current;

		if (number % 100 < 20) {
			current = numNames[number % 100];
			number /= 100;
		} else {
			current = numNames[number % 10];
			number /= 10;

			current = tensNames[number % 10] + current;
			number /= 10;
		}
		if (number == 0)
			return current;
		return numNames[number] + " hundred" + current;
	}

	public static String convert(int number) {

		if (number == 0) {
			return "zero";
		}

		String prefix = "";

		if (number < 0) {
			number = -number;
			prefix = "negative";
		}

		String current = "";
		int place = 0;

		do {
			int n = number % 1000;
			if (n != 0) {
				String s = convertLessThanOneThousand(n);
				current = s + specialNames[place] + current;
			}
			place++;
			number /= 1000;
		} while (number > 0);

		return (prefix + current).trim();
	}

	public static int optdivide(int dividend, int divisor) {
		if (divisor == 0)
			return Integer.MAX_VALUE;
		if (divisor == -1 && dividend == Integer.MIN_VALUE)
			return Integer.MAX_VALUE;

		// get positive values
		long pDividend = Math.abs((long) dividend);
		long pDivisor = Math.abs((long) divisor);
		int q = 0;
		int currentQBase = 1;
		int currentDivisor = divisor;
		while (dividend >= divisor) {
			if (dividend >= currentDivisor) {
				dividend -= currentDivisor;
				q += currentQBase;
				currentDivisor *= 2;
				currentQBase *= 2;
			} else {
				currentDivisor /= 2;
				currentQBase /= 2;
			}
		}
		if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
			return q;
		} else {
			return -q;
		}
	}

	public static int divide(int dividend, int divisor) {
		// handle special cases
		if (divisor == 0)
			return Integer.MAX_VALUE;
		if (divisor == -1 && dividend == Integer.MIN_VALUE)
			return Integer.MAX_VALUE;

		// get positive values
		long pDividend = Math.abs((long) dividend);
		long pDivisor = Math.abs((long) divisor);

		int result = 0;
		while (pDividend >= pDivisor) {
			// calculate number of left shifts
			int numShift = 0;
			while (pDividend >= (pDivisor << numShift)) {
				numShift++;
			}

			// dividend minus the largest shifted divisor
			result += 1 << (numShift - 1);
			pDividend -= (pDivisor << (numShift - 1));
		}

		if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
			return result;
		} else {
			return -result;
		}

	}

}
