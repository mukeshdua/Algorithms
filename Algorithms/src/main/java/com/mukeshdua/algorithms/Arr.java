package com.mukeshdua.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Arr {

	/**
	 * Generate pascal tree
	 */
	public static List<List<Integer>> generate(int num) {
		List<List<Integer>> pascals = new ArrayList<List<Integer>>(num);
		if (num < 1) {
			return pascals;
		}
		List<Integer> prev = new ArrayList<Integer>();

		prev.add(1);
		pascals.add(prev);
		for (int count = 2; count <= num; count++) {
			List<Integer> curr = new ArrayList<Integer>();
			curr.add(1);
			for (int innerCount = 0; innerCount < prev.size() - 1; innerCount++) {
				curr.add(prev.get(innerCount) + prev.get(innerCount + 1));
			}
			curr.add(1);
			pascals.add(curr);
			prev = curr;
		}
		return pascals;
	}

	/**
	 * Given an index k, return the kth row of the Pascal's triangle.
	 */
	public static List<Integer> getRow(int k) {
		List<List<Integer>> pascals = new ArrayList<List<Integer>>(k);
		List<Integer> prev = new ArrayList<Integer>();
		prev.add(1);
		if (k < 1) {
			return prev;
		}
		pascals.add(prev);
		for (int count = 2; count <= k + 1; count++) {
			List<Integer> curr = new ArrayList<Integer>();
			curr.add(1);
			for (int innerCount = 0; innerCount < prev.size() - 1; innerCount++) {
				curr.add(prev.get(innerCount) + prev.get(innerCount + 1));
			}
			curr.add(1);
			pascals.add(curr);
			prev = curr;

		}
		return pascals.get(k);
	}

	/**
	 * Add plus one to number of digits
	 * @param digits
	 * @return
	 */
	public static int[] plusOne(int[] digits) {
		int carriage = 0;

		for (int count = digits.length - 1; count >= 0; count--) {
			int temp = 0;
			int carraigeDigit = digits[count] + carriage;
			if (count == digits.length - 1) {
				carraigeDigit++;
			}
			carriage = 0;
			if (carraigeDigit > 9) {
				temp = 10;
				carriage = 1;
			}
			digits[count] = carraigeDigit - temp;
			if (carriage == 0) {
				break;
			}

		}
		if (carriage == 0) {
			return digits;
		}
		int[] retDigits = new int[digits.length + 1];
		for (int count = digits.length - 1; count >= 0; count--) {
			retDigits[count + 1] = digits[count];
		}
		retDigits[0] = carriage;
		return retDigits;
	}

	/**
	 * Rotate array by k
	 * @param nums
	 * @param k
	 */
	public static void rotate(int[] nums, int k) {
		if (k > nums.length) {
			k = k % nums.length;
		}
		HashMap<Integer, Integer> expectedPosition = new HashMap<Integer, Integer>();
		for (int count = 0; count < nums.length; count++) {
			int key = -1;
			if (count + k >= nums.length) {
				key = k + count - nums.length;
			} else {
				key = count + k;
			}
			expectedPosition.put(key, nums[count]);
		}
		for (int count = 0; count < nums.length; count++) {
			if (expectedPosition.containsKey(count)) {
				nums[count] = expectedPosition.get(count);
			}
		}
	}

	/**
	 * Contains duplicate
	 * @param nums
	 * @return
	 */
	public static boolean containsDuplicate(int[] nums) {
		HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>();
		for (int num : nums) {
			if (numMap.containsKey(num)) {
				return true;
			} else {
				numMap.put(num, 1);
			}
		}
		return false;
	}

	/**
	 * Contains duplicate at most k
	 * @param nums
	 * @param k
	 * @return
	 */
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>();
		for (int count = 0; count < nums.length; count++) {
			int num = nums[count];
			if (numMap.containsKey(num) && count - numMap.get(num) <= k) {
				return true;
			} else {
				numMap.put(num, count);
			}
		}
		return false;

	}

	/**
	 * Majority Element
	 * @param nums
	 * @return
	 */
	public static int majorityElement(int[] nums) {
		HashMap<Integer, Integer> numMap = new HashMap<Integer, Integer>();
		for (int num : nums) {
			if (numMap.containsKey(num)) {
				int value = numMap.get(num) + 1;
				numMap.put(num, value);
			} else {
				numMap.put(num, 1);
			}
		}

		int majoritynum = -1;
		int majoritynumCount = 0;
		for (Integer key : numMap.keySet()) {
			if (numMap.get(key) > majoritynumCount) {
				majoritynumCount = numMap.get(key);
				majoritynum = key;
			}
		}

		return majoritynum;

	}

}
