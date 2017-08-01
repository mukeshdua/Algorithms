package com.mukeshdua.algorithms;

import java.util.Arrays;
import java.util.List;

public class DynamicProgramming {
	/**
	 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you
	 * from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were
	 * broken into on the same night. Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
	 * money you can rob tonight without alerting the police.
	 */
	public int rob(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int odd = 0;
		int even = 0;
		for (int index = 0; index < nums.length; index++) {
			if (index % 2 == 0) {
				even += nums[index];
				even = even > odd ? even : odd;
			} else {
				odd += nums[index];
				odd = odd > even ? odd : even;
			}
		}
		return even > odd ? even : odd;

	}

	/**
	 * For "ABCD" and "EACB", the LCS is "AC", return 2
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static int longestCommonSubsequence(String A, String B) {
		if (A == null || A.length() == 0)
			return 0;
		if (B == null || B.length() == 0)
			return 0;

		int lenA = A.length();
		int lenB = B.length();
		int[][] lcs = new int[1 + lenA][1 + lenB];

		for (int i = 1; i < 1 + lenA; i++) {
			for (int j = 1; j < 1 + lenB; j++) {
				if (A.charAt(i - 1) == B.charAt(j - 1)) {
					lcs[i][j] = 1 + lcs[i - 1][j - 1];
				} else {
					lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
				}
			}
		}

		return lcs[lenA][lenB];
	}

	/*
	 * Say you have an array for which the ith element is the price of a given stock on day i.
	 * 
	 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum
	 * profit.
	 * 
	 * Example 1: Input: [7, 1, 5, 3, 6, 4] Output: 5
	 * 
	 * max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price) Example 2: Input: [7, 6, 4, 3, 1] Output: 0
	 * 
	 * In this case, no transaction is done, i.e. max profit = 0.
	 * 
	 */
	public int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		int min = prices[0];
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] < min) {
				min = prices[i];
			}
			if (prices[i] - min > profit) {
				profit = prices[i] - min;
			}
		}
		return profit;
	}

	public static int longestPalindromeSubseq(String s) {
		int[][] dp = new int[s.length()][s.length()];

		for (int i = s.length() - 1; i >= 0; i--) {
			dp[i][i] = 1;
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) {
					dp[i][j] = dp[i + 1][j - 1] + 2;
				} else {
					dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
				}
			}
		}
		return dp[0][s.length() - 1];
	}

	/*
	 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
	 * 
	 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4], the contiguous subarray [4,-1,2,1] has the largest sum = 6.
	 */
	public int maxSubArray(int[] nums) {
		int[] dp = new int[nums.length];
		int max = dp[0];
		dp[0] = nums[0];
		for (int i = 1; i < nums.length; i++) {
			dp[i] = nums[i] + dp[i - 1] > 0 ? dp[i - 1] : 0;
			max = Math.max(max, dp[i]);
		}

		return max;
	}

	/*
	 * In LeetCode Store, there are some kinds of items to sell. Each item has a price.
	 * 
	 * However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.
	 * 
	 * You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have
	 * to pay for exactly certain items as given, where you could make optimal use of the special offers.
	 * 
	 * Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers
	 * represents how many specific items you could get if you buy this offer.
	 * 
	 * You could use any of special offers as many times as you want.
	 */
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
		int result = Integer.MAX_VALUE;
		int k = needs.size();
		for (int i = 0; i < special.size(); i++) {
			List<Integer> spe = special.get(i);
			int isValidSpe = -1;

			for (int j = 0; j < k; j++) {
				if (needs.get(j) < spe.get(j)) {
					isValidSpe = j;
					break;
				}
				needs.set(j, needs.get(j) - spe.get(j));
			}
			if (isValidSpe == -1) {
				result = Math.min(result, spe.get(spe.size() - 1) + shoppingOffers(price, special, needs));
				isValidSpe = k;
			}

			for (int j = 0; j < isValidSpe; j++) {
				needs.set(j, needs.get(j) + spe.get(j));
			}
		}
		int noSpePri = 0;
		for (int i = 0; i < k; i++)
			noSpePri += needs.get(i) * price.get(i);

		return Math.min(noSpePri, result);
	}

	/*
	 * Given an unsorted array of integers, find the length of longest increasing subsequence.
	 * 
	 * For example, Given [10, 9, 2, 5, 3, 7, 101, 18], The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be
	 * more than one LIS combination, it is only necessary for you to return the length.
	 * 
	 * Your algorithm should run in O(n2) complexity.
	 * 
	 * Follow up: Could you improve it to O(n log n) time complexity?
	 */
	public int lengthOfLIS(int[] nums) {
		int[] dp = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			dp[i] = 1;
		}
		for (int i = 1; i < nums.length; i++) {
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[j] + 1;
				}
			}
		}
		int max = 0;
		for (int i = 0; i < nums.length; i++) {
			if (max < dp[i]) {
				max = dp[i];
			}
		}
		return max;
	}

	/*
	 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
	 * 
	 * You have the following 3 operations permitted on a word:
	 * 
	 * a) Insert a character b) Delete a character c) Replace a character
	 */
	public int minDistance(String str1, String str2) {
		int m = str1.length();
		int n = str2.length();
		// Create a table to store results of subproblems
		int dp[][] = new int[m + 1][n + 1];

		// Fill d[][] in bottom up manner
		for (int i = 0; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				// If first string is empty, only option is to
				// isnert all characters of second string
				if (i == 0)
					dp[i][j] = j; // Min. operations = j

				// If second string is empty, only option is to
				// remove all characters of second string
				else if (j == 0)
					dp[i][j] = i; // Min. operations = i

				// If last characters are same, ignore last char
				// and recur for remaining string
				else if (str1.charAt(i - 1) == str2.charAt(j - 1))
					dp[i][j] = dp[i - 1][j - 1];

				// If last character are different, consider all
				// possibilities and find minimum
				else
					dp[i][j] = 1 + min(dp[i][j - 1], // Insert
							dp[i - 1][j], // Remove
							dp[i - 1][j - 1]); // Replace
			}
		}

		return dp[m][n];

	}

	int min(int x, int y, int z) {
		if (x < y && x < z)
			return x;
		if (y < x && y < z)
			return y;
		else
			return z;
	}
	
	public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;             
        int[] dp = new int[amount + 1];  
        Arrays.fill(dp, max);  
        dp[0] = 0;   
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

	public static void main(String[] args) {
		coinChange(new int[]{1,2,5,6},11);
		longestPalindromeSubseq("bbbab");
	}

}
