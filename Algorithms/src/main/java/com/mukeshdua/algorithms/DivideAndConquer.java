package com.mukeshdua.algorithms;

public class DivideAndConquer {

	public static int getMajorityElementSorted(int[] arr) {
		int result = -1;
		int count = 1;
		for (int index = 0; index < arr.length - 1; index++) {
			if (arr[index] == arr[index + 1]) {
				count++;
				result = arr[index];
			} else {
				count = 1;
				result = -1;
			}
			if (count > arr.length / 2) {
				return result;
			}
		}
		return result;
	}

	/*
	 * Inversion Count for an array indicates – how far (or close) the array is from being sorted. If array is already sorted then inversion count is 0. If
	 * array is sorted in reverse order that inversion count is the maximum. Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and
	 * i < j
	 * 
	 * Example: The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).
	 */
	/*
	 * This method sorts the input array and returns the number of inversions in the array
	 */
	static int inversionUsingMergeSort(int arr[], int array_size) {
		int temp[] = new int[array_size];
		return _mergeSort(arr, temp, 0, array_size - 1);
	}

	/*
	 * An auxiliary recursive method that sorts the input array and returns the number of inversions in the array.
	 */
	static int _mergeSort(int arr[], int temp[], int left, int right) {
		int mid, inv_count = 0;
		if (right > left) {
			/*
			 * Divide the array into two parts and call _mergeSortAndCountInv() for each of the parts
			 */
			mid = (right + left) / 2;

			/*
			 * Inversion count will be sum of inversions in left-part, right-part and number of inversions in merging
			 */
			inv_count = _mergeSort(arr, temp, left, mid);
			inv_count += _mergeSort(arr, temp, mid + 1, right);

			/* Merge the two parts */
			inv_count += merge(arr, temp, left, mid + 1, right);
		}
		return inv_count;
	}

	/*
	 * This method merges two sorted arrays and returns inversion count in the arrays.
	 */
	static int merge(int arr[], int temp[], int left, int mid, int right) {
		int i, j, k;
		int inv_count = 0;

		i = left; /* i is index for left subarray */
		j = mid; /* j is index for right subarray */
		k = left; /* k is index for resultant merged subarray */
		while ((i <= mid - 1) && (j <= right)) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				temp[k++] = arr[j++];

				/* this is tricky -- see above explanation/diagram for merge() */
				inv_count = inv_count + (mid - i);
			}
		}

		/*
		 * Copy the remaining elements of left subarray (if there are any) to temp
		 */
		while (i <= mid - 1)
			temp[k++] = arr[i++];

		/*
		 * Copy the remaining elements of right subarray (if there are any) to temp
		 */
		while (j <= right)
			temp[k++] = arr[j++];

		/* Copy back the merged elements to original array */
		for (i = left; i <= right; i++)
			arr[i] = temp[i];

		return inv_count;
	}

	/*
	 * Find the maximum and minimum of an array using minimum number of comparisons
	 */
	public static int[] maxmin(int[] arr) {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		int[] minmax = new int[2]; // min at 0 index, max at 1st index
		if (arr.length == 1) {
			min = arr[0];
			max = arr[0];
		}

		if (arr[0] < arr[1]) {
			min = arr[0];
			max = arr[1];
		} else {
			min = arr[1];
			max = arr[0];
		}

		for (int index = 2; index <= arr.length - 2;) {
			if (arr[index] > arr[index + 1]) {
				min = Math.min(min, arr[index + 1]);
				max = Math.max(max, arr[index]);

			} else {
				min = Math.min(min, arr[index]);
				max = Math.max(max, arr[index + 1]);

			}
			index = index + 2;
		}
		if (arr.length % 2 != 0) {
			min = Math.min(min, arr[arr.length - 1]);
			max = Math.max(max, arr[arr.length - 1]);
		}
		minmax[0] = min;
		minmax[1] = max;

		return minmax;

	}

	/*
	 * A peak element is an element that is greater than its neighbors.
	 * 
	 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
	 * 
	 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
	 * 
	 * You may imagine that num[-1] = num[n] = -∞.
	 * 
	 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
	 */
	public int findPeakElement(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return 0;
		}
		int count = 0;
		if (nums[0] > nums[1]) {
			return 0;
		}
		for (int index = 1; index < nums.length - 1; index++) {
			if (nums[index] > nums[index - 1] && nums[index] > nums[index + 1]) {
				return index;
			}
		}
		if (nums[nums.length - 1] > nums[nums.length - 2]) {
			return nums.length - 1;
		}

		return count;

	}

	public int findPeakElementBinarySearch(int[] nums) {
		int l = 0, r = nums.length - 1;
		while (l < r) {
			int mid = (l + r) / 2;
			if (nums[mid] > nums[mid + 1])
				r = mid;
			else
				l = mid + 1;
		}
		return l;
	}

	public static int findMissingNumber(int[] arr) {

		int difference[] = new int[arr.length - 1];
		int missingTerm = 0;

		for (int i = 1; i < arr.length; i++) {
			difference[i - 1] = arr[i] - arr[i - 1];
		}
		for (int j = 0; j < arr.length - 1; j++) {

			if (difference[j] != difference[j + 1]) {
				missingTerm = arr[j] + difference[j + 1];
				System.out.println("The missing term is: " + missingTerm);

				break;
			}
		}
		return missingTerm;
	}

	public static void main(String[] args) {
		findMissingNumber(new int[] { 10, 8, 4, 2, 0, -2, -4, -6, -8, -10, -12 });
		System.out.println(maxmin(new int[] { 1, 20, 6, 4, 21, 5 }));
		System.out.println(getMajorityElementSorted(new int[] { 1, 1, 2, 2, 2, 3, 3, 3, 3, 3, 3 }));
		System.out.println(inversionUsingMergeSort(new int[] { 1, 20, 6, 4, 5 }, 5));
	}

}
