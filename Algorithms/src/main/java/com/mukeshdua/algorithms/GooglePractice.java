package com.mukeshdua.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GooglePractice {
	/*
	 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
	 * 
	 * Note: Do not use any built-in library function such as sqrt.
	 * 
	 * Example 1:
	 * 
	 * Input: 16 Returns: True Example 2:
	 * 
	 * Input: 14 Returns: False
	 */
	// public boolean isPerfectSquare(int num) {
	// if(num < 0)
	// {
	// return false;
	// }
	// for(int index=0;index<=num/2;index++)
	// {
	// if(index*index == num)
	// {
	// return true;
	// }
	// }
	// return false;
	// }

	public static boolean isPerfectSquare(int num) {
		int low = 1, high = num;
		while (low <= high) {
			long mid = (low + high) / 2;
			if (mid * mid == num) {
				return true;
			} else if (mid * mid < num) {
				low = (int) mid + 1;
			} else {
				high = (int) mid - 1;
			}
		}
		return false;
	}

	/*
	 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
	 * 
	 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that
	 * your returned answers (both index1 and index2) are not zero-based.
	 * 
	 * You may assume that each input would have exactly one solution and you may not use the same element twice.
	 * 
	 * Input: numbers={2, 7, 11, 15}, target=9 Output: index1=1, index2=2
	 */
	public int[] twoSum(int[] numbers, int target) {
		int[] retArray = new int[2];
		int startIndex = 0;
		int endIndex = numbers.length - 1;
		while (startIndex < endIndex) {
			int sum = numbers[startIndex] + numbers[endIndex];
			if (sum == target) {
				retArray[0] = startIndex + 1;
				retArray[1] = endIndex + 1;
				return retArray;
			} else if (sum > target) {
				endIndex--;
			} else {
				startIndex++;
			}
		}
		return retArray;

	}

	/*
	 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
	 * 
	 * Given n, find the total number of full staircase rows that can be formed.
	 * 
	 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
	 * 
	 * Example 1:
	 * 
	 * n = 5
	 * 
	 * The coins can form the following rows: ¤ ¤ ¤ ¤ ¤
	 * 
	 * Because the 3rd row is incomplete, we return 2.
	 */
	public int arrangeCoins(int n) {
		int row = 1;
		int rowCount = 0;
		while (n >= row) {
			n = n - row;
			row++;
			rowCount++;
		}
		return rowCount;
	}

	/*
	 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in
	 * order.
	 * 
	 * You may assume no duplicates in the array.
	 * 
	 * Here are few examples. [1,3,5,6], 5 → 2 [1,3,5,6], 2 → 1 [1,3,5,6], 7 → 4 [1,3,5,6], 0 → 0
	 */
	public static int searchInsert(int[] A, int target) {
		int low = 0, high = A.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (A[mid] == target)
				return mid;
			else if (A[mid] > target)
				high = mid - 1;
			else
				low = mid + 1;
		}
		return low;
	}

	/*
	 * Given two arrays, write a function to compute their intersection.
	 * 
	 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
	 * 
	 * Note: Each element in the result must be unique. The result can be in any order.
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		if (nums1.length == 0 || nums2.length == 0) {
			int[] ret = new int[0];
			return ret;
		}
		HashSet<Integer> set = new HashSet<Integer>();
		HashSet<Integer> intersection = new HashSet<Integer>();
		for (int num : nums1) {
			set.add(num);
		}
		for (int num : nums2) {
			if (set.contains(num)) {
				intersection.add(num);
			}
		}
		int[] result = new int[intersection.size()];
		int i = 0;
		for (Integer num : intersection) {
			result[i++] = num;
		}
		return result;
	}

	/**
	 * Important to read If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record
	 * the intersections. If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements
	 * from each array at a time in memory, record intersections.
	 * 
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] intersectmultipleVals(int[] nums1, int[] nums2) {
		if (nums1.length == 0 || nums2.length == 0) {
			int[] ret = new int[0];
			return ret;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		List<Integer> intersection = new ArrayList<Integer>();
		for (int num : nums1) {
			int value = 1;
			if (map.containsKey(num)) {
				value = map.get(num) + 1;
			}
			map.put(num, value);

		}
		for (int num : nums2) {
			if (map.containsKey(num) && map.get(num) > 0) {
				intersection.add(num);
				map.put(num, map.get(num) - 1);
			}
		}
		int[] result = new int[intersection.size()];
		int i = 0;
		for (Integer num : intersection) {
			result[i++] = num;
		}
		return result;
	}

	/**
	 * Find the sum of all left leaves in a given binary tree.
	 * 
	 * Example:
	 * 
	 * 3 / \ 9 20 / \ 15 7
	 * 
	 * There are two left leaves in the binary tree, with values 9 and 15 respectively. Return 24.
	 */
	/**
	 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
	 */

	public int sumOfLeftLeaves(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int sum = 0;
		if (root.left != null) {
			if (root.left.right == null && root.left.left == null) {
				sum += root.left.val;
			}
		}
		sum += sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
		return sum;
	}

	/*
	 * Given two binary trees and imagine that when you put one of them to cover the other, some nodes of the two trees are overlapped while the others are not.
	 * 
	 * You need to merge them into a new binary tree. The merge rule is that if two nodes overlap, then sum node values up as the new value of the merged node.
	 * Otherwise, the NOT null node will be used as the node of new tree.
	 * 
	 * Example 1: Input: Tree 1 Tree 2 1 2 / \ / \ 3 2 1 3 / \ \ 5 4 7 Output: Merged tree: 3 / \ 4 5 / \ \ 5 4 7 Note: The merging process must start from the
	 * root nodes of both trees.
	 */
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {

		if (t1 == null && t2 == null) {
			return null;
		}

		int val = 0;
		if (t1 != null) {
			val += t1.val;
		}
		if (t2 != null) {
			val += t2.val;
		}
		TreeNode node = new TreeNode(val);

		node.left = mergeTrees(t1 == null ? null : t1.left, t2 == null ? null : t2.left);
		node.right = mergeTrees(t1 == null ? null : t1.right, t2 == null ? null : t2.right);

		return node;
	}

	/*
	 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a
	 * tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
	 * 
	 * Example 1: Given tree s:
	 * 
	 * 3 / \ 4 5 / \ 1 2 Given tree t: 4 / \ 1 2 Return true, because t has the same structure and node values with a subtree of s.
	 */
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (s == null)
			return false;
		if (isSame(s, t))
			return true;
		return isSubtree(s.left, t) || isSubtree(s.right, t);
	}

	private boolean isSame(TreeNode s, TreeNode t) {
		if (s == null && t == null)
			return true;
		if (s == null || t == null)
			return false;

		if (s.val != t.val)
			return false;

		return isSame(s.left, t.left) && isSame(s.right, t.right);
	}

	// Day:2
	/*
	 * 
	 */
	public String tree2str(TreeNode t) {
		if (t == null) {
			return null;
		}
		String ret = String.valueOf(t.val);
		if (t.left != null) {
			ret += "(";
			ret += tree2str(t.left);
		}
		if (t.left == null && t.right != null) {
			ret += "())";
		}
		if (t.right != null) {
			ret += "(";
			ret += tree2str(t.right);
		}
		if (t.left == null && t.right == null) {
			ret += ")";
		}

		if (t.left != null && t.right == null) {
			ret += ")";
		}

		return ret;
	}

	int tilt = 0;

	/*
	 * Given a binary tree, return the tilt of the whole tree.
	 * 
	 * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right subtree node
	 * values. Null node has tilt 0.
	 * 
	 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
	 * 
	 * Example: Input: 1 / \ 2 3 Output: 1 Explanation: Tilt of node 2 : 0 Tilt of node 3 : 0 Tilt of node 1 : |2-3| = 1 Tilt of binary tree : 0 + 0 + 1 = 1
	 * Note:
	 * 
	 * The sum of node values in any subtree won't exceed the range of 32-bit integer. All the tilt values won't exceed the range of 32-bit integer.
	 */
	public int findTilt(TreeNode root) {
		postorder(root);
		return tilt;
	}

	public int postorder(TreeNode root) {
		if (root == null)
			return 0;
		int leftSum = postorder(root.left);
		int rightSum = postorder(root.right);
		tilt += Math.abs(leftSum - rightSum);
		return leftSum + rightSum + root.val;
	}

	// Backup solution
	// public String tree2str(TreeNode t) {
	// if (t == null) return "";
	//
	// String result = t.val + "";
	//
	// String left = tree2str(t.left);
	// String right = tree2str(t.right);
	//
	// if (left == "" && right == "") return result;
	// if (left == "") return result + "()" + "(" + right + ")";
	// if (right == "") return result + "(" + left + ")";
	// return result + "(" + left + ")" + "(" + right + ")";
	// }

	/*
	 * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the
	 * same.
	 * 
	 * For example, these are arithmetic sequence:
	 * 
	 * 1, 3, 5, 7, 9 7, 7, 7, 7 3, -1, -5, -9 The following sequence is not arithmetic.
	 * 
	 * 1, 1, 2, 5, 7
	 * 
	 * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.
	 * 
	 * A slice (P, Q) of array A is called arithmetic if the sequence: A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 <
	 * Q.
	 * 
	 * The function should return the number of arithmetic slices in the array A.
	 */
	public static int numberOfArithmeticSlices(int[] A) {
		int curr = 0, sum = 0;
		for (int i = 2; i < A.length; i++)
			if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
				curr += 1;
				sum += curr;
			} else {
				curr = 0;
			}
		return sum;
	}

	/*
	 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a
	 * single digit. Add the two numbers and return it as a linked list.
	 * 
	 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
	 * 
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
	 */
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = null;
		int nextReminder = 0;
		int count = 0;
		ListNode head = null;
		while (l1 != null || l2 != null) {
			int val = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + nextReminder;
			nextReminder = 0;
			if (val >= 10) {
				nextReminder = 1;
				val = val - 10;
			}
			if (count == 0) {
				result = new ListNode(val);
				head = result;
				count++;
			} else {
				result.next = new ListNode(val);
				result = result.next;
			}
			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
		}
		if (nextReminder == 1) {
			result.next = new ListNode(nextReminder);
			result = result.next;
			nextReminder = 0;
		}
		return head;
	}

	/*
	 * Given a string, find the length of the longest substring without repeating characters.
	 * 
	 * Examples:
	 * 
	 * Given "abcabcbb", the answer is "abc", which the length is 3.
	 * 
	 * Given "bbbbb", the answer is "b", with the length of 1.
	 * 
	 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
	 */
	public static int lengthOfLongestSubstring(String s) {
		if (s.length() == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max = 0;
		for (int i = 0, j = 0; i < s.length(); ++i) {
			if (map.containsKey(s.charAt(i))) {
				j = Math.max(j, map.get(s.charAt(i)) + 1);
			}
			map.put(s.charAt(i), i);
			max = Math.max(max, i - j + 1);
		}
		return max;
	}

	// Day: 4
	public static int lengthOfLongestSubsequence(String s) {
		if (s == null) {
			return 0;
		}
		Map<Character, Integer> val = new HashMap<Character, Integer>();
		int length = 0;
		for (int i = 0; i < s.length(); i++) {
			if (val.containsKey(s.charAt(i))) {
				length = Math.max(length, val.size());
				val.remove(s.charAt(i));
			}
			val.put(s.charAt(i), i);
		}
		return Math.max(val.size(), length);
	}

	/*
	 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
	 * 
	 * Given two integers x and y, calculate the Hamming distance.
	 * 
	 * Note: 0 ≤ x, y < 231.
	 */
	public int hammingDistance(int x, int y) {
		return Integer.bitCount(x ^ y);
	}

	public static String[] findWords(String[] words) {
		Set<String> firstRow = new HashSet<String>(Arrays.asList("q", "w", "e", "r", "t", "y", "u", "i", "o", "p"));
		Set<String> secondRow = new HashSet<String>(Arrays.asList("a", "s", "d", "f", "g", "h", "j", "k", "l"));
		Set<String> thirdRow = new HashSet<String>(Arrays.asList("z", "x", "c", "v", "b", "n", "m"));
		List<String> result = new ArrayList<String>();
		for (String word : words) {
			boolean firstRowVal = false;
			boolean secondRowVal = false;
			boolean thirdRowVal = false;
			for (int i = 0; i < word.length(); i++) {
				Character c = word.charAt(i);
				if (firstRow.contains(String.valueOf(c).toLowerCase())) {
					firstRowVal = true;
				} else if (secondRow.contains(String.valueOf(c).toLowerCase())) {
					secondRowVal = true;
				} else if (thirdRow.contains(String.valueOf(c).toLowerCase())) {
					thirdRowVal = true;
				}

			}
			if ((firstRowVal && !secondRowVal && !thirdRowVal) || (!firstRowVal && secondRowVal && !thirdRowVal)
					|| (!firstRowVal && !secondRowVal && thirdRowVal)) {
				result.add(word);
			}
		}
		return result.toArray(new String[0]);
	}

	/*
	 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
	 * 
	 * Note: The given integer is guaranteed to fit within the range of a 32-bit signed integer. You could assume no leading zero bit in the integer’s binary
	 * representation. Example 1: Input: 5 Output: 2 Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So
	 * you need to output 2. Example 2: Input: 1 Output: 0 Explanation: The binary representation of 1 is 1 (no leading zero bits), and its complement is 0. So
	 * you need to output 0.
	 * 
	 */
	public int findComplement(int num) {
		return ~num & ((Integer.highestOneBit(num) << 1) - 1);
	}

	// Iterative
	public int getIterativeSum(int a, int b) {
		if (a == 0)
			return b;
		if (b == 0)
			return a;

		while (b != 0) {
			int carry = a & b;
			a = a ^ b;
			b = carry << 1;
		}

		return a;
	}

	// Iterative
	public int getIterativeSubtract(int a, int b) {
		while (b != 0) {
			int borrow = (~a) & b;
			a = a ^ b;
			b = borrow << 1;
		}

		return a;
	}

	// Recursive
	public int getRecursiveSum(int a, int b) {
		return (b == 0) ? a : getRecursiveSum(a ^ b, (a & b) << 1);
	}

	// Recursive
	public int getRecursiveSubtract(int a, int b) {
		return (b == 0) ? a : getRecursiveSubtract(a ^ b, (~a & b) << 1);
	}

	// Get negative number
	public int negate(int x) {
		return ~x + 1;
	}

	/*
	 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
	 * 
	 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
	 */
	public static int numSquares(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for (int i = 1; i <= n; ++i) {
			int min = Integer.MAX_VALUE;
			int j = 1;
			while (i - j * j >= 0) {
				min = Math.min(min, dp[i - j * j] + 1);
				++j;
			}
			dp[i] = min;
		}
		return dp[n];
	}

	/*
	 * Given a binary tree, determine if it is height-balanced.
	 * 
	 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more
	 * than 1.
	 */
	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		int depthLeft = maxDepth(root.left);
		int depthRight = maxDepth(root.right);
		int diff = Math.abs(depthLeft - depthRight);
		boolean result = false;
		if (diff <= 1) {
			result = true;
		}
		return result && isBalanced(root.left) && isBalanced(root.right);
	}

	private int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = maxDepth(root.left) + 1;
		int right = maxDepth(root.right) + 1;
		if (left > right) {
			return left;
		}
		return right;
	}

	/**
	 * Definition for a binary tree node. public class TreeNode { int val; TreeNode left; TreeNode right; TreeNode(int x) { val = x; } }
	 */
	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int depth = depth(root.left) + depth(root.right);
		int left = diameterOfBinaryTree(root.left);
		int right = diameterOfBinaryTree(root.right);
		return Math.max(depth, left + right);
	}

	private int depth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = depth(root.left) + 1;
		int right = depth(root.right) + 1;
		if (left > right) {
			return left;
		}
		return right;
	}

	/*
	 * Given a binary tree, find its minimum depth.
	 * 
	 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
	 */
	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int left = minDepth(root.left);
		int right = minDepth(root.right);
		if (left == 0 || right == 0) {
			return left + right + 1;
		}
		if (left < right) {
			return left + 1;
		}
		return right + 1;

	}
	
	public static List<Integer> largestValues(TreeNode root) {
        List<Integer> result= new ArrayList<Integer>();
        if(root==null)
        {
            return result;
        }
        Queue<TreeNode> node= new LinkedList<TreeNode>();
        node.add(root);
        int currentlevel=1;
        int nextlevel=0;
        int max=Integer.MIN_VALUE;
        while(!node.isEmpty())
        {
            TreeNode temp= node.remove();
            max= Math.max(max,temp.val);
            currentlevel--;
            if(temp.left != null)
            {
                node.add(temp.left);
                nextlevel++;
            }
            if(temp.right !=null)
            {
                node.add(temp.right);
                nextlevel++;
            }
            if (currentlevel == 0) {
                result.add(max);
				currentlevel = nextlevel;
				nextlevel = 0;
                max=Integer.MIN_VALUE;
			}
        }
        return result;
    }

	public static void main(String[] args) throws Exception {
		numSquares(8);
		String[] arr = new String[] { "Hello", "Alaska", "Dad", "Peace" };
		findWords(arr);
		lengthOfLongestSubstring("dvdf");
		Map<Character, Integer> val = new HashMap<Character, Integer>();

		int[] nums = new int[4];
		nums[0] = 1;
		nums[1] = 2;
		nums[2] = 3;
		nums[3] = 4;
		numberOfArithmeticSlices(nums);
		searchInsert(nums, 0);
		isPerfectSquare(14);
		
		Tree t = new Tree(new int[] { 1, 3, 2, 5, 3, 9 });
		List<Integer> largestVal = largestValues(t.root);
		for (int count = 0; count < largestVal.size(); count++) {

			System.out.println(largestVal.get(count) + ",");

		}
	}
	
	/*
	 * returns true if there is triplet with sum equal
    	to 'sum' present in A[]. Also, prints the triplet
	 */
	boolean find3Numbers(int A[], int arr_size, int sum) 
    {
        int l, r;
 
        /* Sort the elements */
        quickSort(A, 0, arr_size - 1);
 
        /* Now fix the first element one by one and find the
           other two elements */
        for (int i = 0; i < arr_size - 2; i++) 
        {
            // To find the other two elements, start two index variables
            // from two corners of the array and move them toward each
            // other
            l = i + 1; // index of the first element in the remaining elements
            r = arr_size - 1; // index of the last element
            while (l < r) 
            {
                if (A[i] + A[l] + A[r] == sum) 
                {
                    System.out.print("Triplet is " + A[i] + " ," + A[l]
                            + " ," + A[r]);
                    return true;
                }
                else if (A[i] + A[l] + A[r] < sum)
                    l++;
                 
                else // A[i] + A[l] + A[r] > sum
                    r--;
            }
        }
 
        // If we reach here, then no triplet was found
        return false;
    }
 
    int partition(int A[], int si, int ei) 
    {
        int x = A[ei];
        int i = (si - 1);
        int j;
 
        for (j = si; j <= ei - 1; j++) 
        {
            if (A[j] <= x) 
            {
                i++;
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        }
        int temp = A[i + 1];
        A[i + 1] = A[ei];
        A[ei] = temp;
        return (i + 1);
    }
 
    /* Implementation of Quick Sort
    A[] --> Array to be sorted
    si  --> Starting index
    ei  --> Ending index
     */
    void quickSort(int A[], int si, int ei) 
    {
        int pi;
         
        /* Partitioning index */
        if (si < ei) 
        {
            pi = partition(A, si, ei);
            quickSort(A, si, pi - 1);
            quickSort(A, pi + 1, ei);
        }
    }
    
   
 }
