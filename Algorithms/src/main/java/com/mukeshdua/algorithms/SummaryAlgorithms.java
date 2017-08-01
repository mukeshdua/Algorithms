package com.mukeshdua.algorithms;

import java.util.ArrayList;
import java.util.HashMap;

public class SummaryAlgorithms {
	static /*
	 * Find a pair in an array of size 'n', whose sum is X
	 * Using hashMap or sort
	 */
	/*
	 *Find a majority element in an array of size 'n', HAshMap- O(N).
	 *Below method O(1) space 
	 */
	  /* Function to find the candidate for Majority */
	void printMajority(int a[], int size) 
    {
        /* Find the candidate for Majority*/
        int cand = findCandidate(a, size);
 
        /* Print the candidate if it is Majority*/
        if (isMajority(a, size, cand))
            System.out.println(" " + cand + " ");
        else
            System.out.println("No Majority Element");
    }
	
    static int findCandidate(int a[], int size) 
    {
        int maj_index = 0, count = 1;
        int i;
        for (i = 1; i < size; i++) 
        {
            if (a[maj_index] == a[i])
                count++;
            else
                count--;
            if (count == 0)
            {
                maj_index = i;
                count = 1;
            }
        }
        return a[maj_index];
    }
 
    /* Function to check if the candidate occurs more
       than n/2 times */
    static boolean isMajority(int a[], int size, int cand) 
    {
        int i, count = 0;
        for (i = 0; i < size; i++) 
        {
            if (a[i] == cand)
                count++;
        }
        if (count > size / 2) 
            return true;
        else
            return false;
    }
	
	/*
	 * Find the number occuring odd number of times in a given array of size 'n'
	 */
	public static int getOddOccurrence(int[] arr) {
		int i;
		int res = 0;
		for (i = 0; i < arr.length; i++) {
			res = res ^ arr[i];
		}
		return res;
	}
	
	/*
	 * Algorithm to reverse an array: Easy by changing the temp value
	 */
	public static int[] reverseArray(int[] arr)
	{
		int start=0;int end=arr.length-1;
		while(start<end)
		{
			int temp=arr[start];
			arr[start]=arr[end];
			arr[end]=temp;
			start++;
			end--;
		}
		return arr;
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
	
	/*
	 * Find the maximum difference between two elements such that larger element appears after the smaller element
	 */
	int maxDiff(int arr[], int arr_size) 
    {
        int max_diff = arr[1] - arr[0];
        int min_element = arr[0];
        int i;
        for (i = 1; i < arr_size; i++) 
        {
            if (arr[i] - min_element > max_diff)
                max_diff = arr[i] - min_element;
            if (arr[i] < min_element)
                min_element = arr[i];
        }
        return max_diff;
    }
	
	//permutation
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		permute(num, 0, result);
		return result;
	}
	 
	void permute(int[] num, int start, ArrayList<ArrayList<Integer>> result) {
	 
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
	 
	private ArrayList<Integer> convertArrayToList(int[] num) {
		ArrayList<Integer> item = new ArrayList<Integer>();
		for (int h = 0; h < num.length; h++) {
			item.add(num[h]);
		}
		return item;
	}
	 
	private void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static void main(String[] args)
	{
//		int[] arr = new int[]{1,2,3,3,1};
//		getOddOccurrence(arr);
		//int a[] = new int[]{1, 3, 3, 1, 3,3,2,3,4};
//        int size = a.length;
//        printMajority(a, size);
        
		int a1[] = new int[]{1, 2,4,5,3};
		reverseArray(a1);
	}
	

}
