package com.mukeshdua.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Arr {
	
	/*
	 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.
	 */
	public int islandPerimeter(int[][] grid) {
        int m=grid.length;
        int n=grid[0].length;
        int island=0;
        int neighbor=0;
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(grid[i][j] == 1)
                {
                    island++;
                    if(i<m-1 && grid[i+1][j]==1) neighbor++;
                    if(j<n-1 && grid[i][j+1]==1) neighbor++;
                }
            }
        }
        return island*4 - neighbor*2;
    }
	
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
		if (nums[left] == nums[right]) {
			return findMinRotatedSortedDuplicateArray(nums, left + 1, right);
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
		if (left > right)
			return -1;

		int mid = left + (right - left) / 2;
		if (target == nums[mid]) {
			return mid;
		}

		if (nums[left] <= nums[mid]) {
			if (nums[left] <= target && target < nums[mid]) {
				return searchRotatedArray(nums, left, mid - 1, target);
			} else {
				return searchRotatedArray(nums, mid + 1, right, target);
			}
		} else {
			if (nums[mid] < target && target <= nums[right]) {
				return searchRotatedArray(nums, mid + 1, right, target);
			} else {
				return searchRotatedArray(nums, left, mid - 1, target);
			}
		}

	}

	/**
	 * Permutation
	 * 
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

	/*
	 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
	 * 
	 * Find all the elements of [1, n] inclusive that do not appear in this array.
	 * 
	 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
	 */
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			int val = Math.abs(nums[i]) - 1;
			if (nums[val] > 0) {
				nums[val] = -nums[val];
			}

		}
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				result.add(i + 1);
			}
		}
		return result;
	}

	/*
	 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
	 * 
	 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
	 * 
	 * Note: You must do this in-place without making a copy of the array. Minimize the total number of operations.
	 */

	public void moveZeroes(int[] nums) {
		int i = 0;
		int j = 0;

		while (j < nums.length) {
			if (nums[j] == 0) {
				j++;
			} else {
				nums[i] = nums[j];
				i++;
				j++;
			}
		}

		while (i < nums.length) {
			nums[i] = 0;
			i++;
		}
	}
	/*
	 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order, too.

You need to find the shortest such subarray and output its length.

Example 1:
Input: [2, 6, 4, 8, 10, 9, 15]
Output: 5
Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
Note:
Then length of the input array is in range [1, 10,000].
The input array may contain duplicates, so ascending order here means <=.
	 */
	 public int findUnsortedSubarray(int[] nums) {
	        int[] snums = nums.clone();
	        Arrays.sort(snums);
	        int start = snums.length, end = 0;
	        for (int i = 0; i < snums.length; i++) {
	            if (snums[i] != nums[i]) {
	                start = Math.min(start, i);
	                end = Math.max(end, i);
	            }
	        }
	        return (end - start >= 0 ? end - start + 1 : 0);
	    }
	 
	 /*
	  * Given a set of distinct integers, nums, return all possible subsets.

Note: The solution set must not contain duplicate subsets.

For example,
If nums = [1,2,3], a solution is:
	  */
	 public List<List<Integer>> subsets(int[] S) {
		    if (S == null)
				return null;
		 
			Arrays.sort(S);
		 
			List<List<Integer>> result = new ArrayList<List<Integer>>();
		 
			for (int i = 0; i < S.length; i++) {
				List<List<Integer>> temp = new ArrayList<List<Integer>>();
		 
				//get sets that are already in result
				for (List<Integer> a : result) {
					temp.add(new ArrayList<Integer>(a));
				}
		 
				//add S[i] to existing sets
				for (List<Integer> a : temp) {
					a.add(S[i]);
				}
		 
				//add S[i] only as a set
				List<Integer> single = new ArrayList<Integer>();
				single.add(S[i]);
				temp.add(single);
		 
				result.addAll(temp);
			}
		 
			//add empty set
			result.add(new ArrayList<Integer>());
		 
			return result;
		    }

	public static void main(String[] args) {
		findDisappearedNumbers(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 });
	}
	
	int subArraySum(int arr[], int n, int sum) 
    {
        int curr_sum = arr[0], start = 0, i;
 
        // Pick a starting point
        for (i = 1; i <= n; i++) 
        {
            // If curr_sum exceeds the sum, then remove the starting elements
            while (curr_sum > sum && start < i-1)
            {
                curr_sum = curr_sum - arr[start];
                start++;
            }
             
            // If curr_sum becomes equal to sum, then return true
            if (curr_sum == sum) 
            {
                int p = i-1;
                System.out.println("Sum found between indexes " + start
                        + " and " + p);
                return 1;
            }
             
            // Add this element to curr_sum
            if (i < n)
            curr_sum = curr_sum + arr[i];
             
        }
 
        System.out.println("No subarray found");
        return 0;
    }
	
	 // function to calculate median
    static int getMedian(int ar1[], int ar2[], int n)
    {   
        int i = 0;  
        int j = 0; 
        int count;
        int m1 = -1, m2 = -1;
      
        /* Since there are 2n elements, median will 
           be average of elements at index n-1 and 
           n in the array obtained after merging ar1 
           and ar2 */
        for (count = 0; count <= n; count++)
        {
            /* Below is to handle case where all 
              elements of ar1[] are smaller than 
              smallest(or first) element of ar2[] */
            if (i == n)
            {
                m1 = m2;
                m2 = ar2[0];
                break;
            }
      
            /* Below is to handle case where all 
               elements of ar2[] are smaller than 
               smallest(or first) element of ar1[] */
            else if (j == n)
            {
                m1 = m2;
                m2 = ar1[0];
                break;
            }
      
            if (ar1[i] < ar2[j])
            {   
                /* Store the prev median */
                m1 = m2;  
                m2 = ar1[i];
                i++;
            }
            else
            {
                /* Store the prev median */
                m1 = m2;  
                m2 = ar2[j];
                j++;
            }
        }
      
        return (m1 + m2)/2;
    }
}
