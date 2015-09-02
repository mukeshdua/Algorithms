package com.mukeshdua.algorithms;

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

	public static int maxProfit(int[] prices) {
		int minPrices = Integer.MAX_VALUE;
		int ret = 0;

		int n = prices.length;
		for (int i = 0; i < n; ++i) {
			minPrices = Math.min(minPrices, prices[i]);
			ret = Math.max(ret, prices[i] - minPrices);
		}
		return ret;
	}

}
