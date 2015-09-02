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
	 * 
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
	 * 
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
	 * 
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
	 * 
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
	 * 
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

	/**
	 * Search an element in 2D array
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */

	public static boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;

		int row = matrix.length;
		int col = matrix[0].length;
		int rowPtr = row - 1;
		int colPtr = 0;

		while (true) {

			// ending loop condition
			if (rowPtr < 0 || colPtr >= col)
				return false;

			if (matrix[rowPtr][colPtr] == target)
				return true;

			if (matrix[rowPtr][colPtr] > target) {
				rowPtr--;

			} else {
				colPtr++;
			}

		}

	}

	public static int[] twoSum(int[] nums, int target) {
		int[] retIndexes = new int[2];
		HashMap<Integer, Integer> values = new HashMap<Integer, Integer>(nums.length);
		for (int count = 0; count < nums.length; count++) {
			values.put(nums[count], count);
		}
		for (int count = 0; count < nums.length; count++) {
			int diff = target - nums[count];
			if (values.containsKey(diff) && values.get(diff) != count) {
				retIndexes[0] = count + 1;
				retIndexes[1] = values.get(diff) + 1;
				return retIndexes;
			}
		}
		return retIndexes;
	}

	/**
	 * MinSubArray length whose sum is equal to targetSum
	 * 
	 * @param nums
	 * @param targetSum
	 * @return
	 */

	public boolean isSubArraySum(int[] nums, int targetSum) {
		if (nums.length == 0) {
			return false;
		}
		if (nums.length == 1 && nums[0] == targetSum) {
			return true;
		}

		int currSum = nums[0];
		int start = 0;
		for (int count = 1; count <= nums.length; count++) {

			while (currSum > targetSum) {
				currSum = currSum - nums[start];
				start++;
			}

			if (currSum == targetSum) {
				return true;
			}
			if (count < nums.length) {
				currSum += nums[count];
			}

		}
		return false;
	}

	/**
	 * MinSubArray length whose sum is greater than and equal to targetSum
	 * 
	 * @param targetSum
	 * @param nums
	 * @return
	 */
	public static int minSubArrayLen(int targetSum, int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1 && nums[0] >= targetSum) {
			return 1;
		}

		int currSum = nums[0];
		int start = 0;
		int minLength = Integer.MAX_VALUE;
		for (int count = 1; count <= nums.length; count++) {

			while (currSum >= targetSum) {
				currSum = currSum - nums[start];
				if (currSum < targetSum && count - start < minLength) {
					minLength = count - start;
				}
				start++;
			}
			if (count < nums.length) {
				currSum += nums[count];
			}
		}
		if (minLength == Integer.MAX_VALUE) {
			return 0;
		}
		return minLength;
	}

	/**
	 * Find minimum in rotated sorted array
	 */
	public static int findMin(int[] nums) {
		return findMinRotatedSortedArray(nums, 0, nums.length - 1);

	}

	private static int findMinRotatedSortedArray(int[] nums, int left, int right) {
		if (left == right) {
			return nums[left];
		}
		if (right - left == 1) {
			return Math.min(nums[left], nums[right]);
		}
		if (nums[left] < nums[right]) {
			return nums[left];
		}
		int mid = left + (right - left) / 2;
		if (nums[left] < nums[mid]) {
			return findMinRotatedSortedArray(nums, mid, right);
		} else {
			return findMinRotatedSortedArray(nums, left, mid);
		}

	}
	
	/**
	 * Find minimum in rotated sorted array in duplicate
	 */
	public static int findMinSortedDuplicateArray(int[] nums) {
		return findMinRotatedSortedDuplicateArray(nums, 0, nums.length - 1);

	}

	private static int findMinRotatedSortedDuplicateArray(int[] nums, int left, int right) {
		if (left == right) {
			return nums[left];
		}
		if (right - left == 1) {
			return Math.min(nums[left], nums[right]);
		}
		if (nums[left] < nums[right]) {
			return nums[left];
		}
		int mid = left + (right - left) / 2;
		if(nums[left] == nums[right])
		{
			return findMinRotatedSortedDuplicateArray(nums, left+1, right);
		}
		if (nums[left] <= nums[mid]) {
			return findMinRotatedSortedDuplicateArray(nums, mid, right);
		} else {
			return findMinRotatedSortedDuplicateArray(nums, left, mid);
		}

	}

	/**
	 * Search an element in rotated sorted array
	 */
	public static int search(int[] nums, int target) {
		return searchRotatedArray(nums, 0, nums.length - 1, target);

	}

	private static int searchRotatedArray(int[] nums, int left, int right, int target) {
		 if(left>right) 
		        return -1;
		 
		int mid = left + (right - left) / 2;
		if (target == nums[mid]) {
			return mid;
		}

		if(nums[left] <= nums[mid]){
	        if(nums[left]<=target && target<nums[mid]){
	          return searchRotatedArray(nums,left, mid-1, target);
	        }else{
	          return  searchRotatedArray(nums, mid+1, right, target);
	        }
	    }else {
	        if(nums[mid]<target&& target<=nums[right]){
	          return  searchRotatedArray(nums,mid+1, right, target);
	        }else{
	          return  searchRotatedArray(nums, left, mid-1, target);
	        }
	    }

	}
	
	/**
	 * Permutation
	 * @param num
	 * @return
	 */
	public static ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permute(num, 0, result);
		return result;
	}
	 
	private static void permute(int[] num, int start, ArrayList<ArrayList<Integer>> result) {
	 
		if (start >= num.length) {
			ArrayList<Integer> item = convertArrayToList(num);
			result.add(item);
		}
	 
		for (int j = start; j <= num.length - 1; j++) {
			swap(num, start, j);
			permute(num, start + 1, result);
			swap(num, start, j);
		}
	}
	 
	private static ArrayList<Integer> convertArrayToList(int[] num) {
		ArrayList<Integer> item = new ArrayList<Integer>();
		for (int h = 0; h < num.length; h++) {
			item.add(num[h]);
		}
		return item;
	}
	 
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
