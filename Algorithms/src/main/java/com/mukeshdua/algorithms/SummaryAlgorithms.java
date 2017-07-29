package com.mukeshdua.algorithms;

public class SummaryAlgorithms {
	static /*
	 * Find a pair in an array of size 'n', whose sum is X
	 * Using hashMap or sort
	 */
	/*
	 * 
	 */
	
	/*
	 * Find the number occuring odd number of times in a given array of size 'n'
	 */
	int getOddOccurrence(int[] arr) {
		int i;
		int res = 0;
		for (i = 0; i < arr.length; i++) {
			res = res ^ arr[i];
		}
		return res;
	}
	
	public static void main(String[] args)
	{
		int[] arr = new int[]{1,2,3,3,1};
		getOddOccurrence(arr);
	}

}
