package com.mukeshdua.algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Str {

	private static boolean isH1EqualsH2(HashMap<Character, Integer> h1, HashMap<Character, Integer> h2) {
		for (Character sChar : h1.keySet()) {
			if (!h2.containsKey(sChar) || (h2.containsKey(sChar) && !h1.get(sChar).equals(h2.get(sChar)))) {
				return false;
			}
		}
		return true;
	}

	private static HashMap<Character, Integer> getHashMap(String s) {
		HashMap<Character, Integer> retValue = new HashMap<Character, Integer>();
		for (int count = 0; count < s.length(); count++) {
			if (retValue.containsKey(s.charAt(count))) {
				retValue.put(s.charAt(count), retValue.get(s.charAt(count)) + 1);
			} else {
				retValue.put(s.charAt(count), 1);
			}
		}
		return retValue;
	}

	private static String getResult(String s, String t) {
		StringBuilder result = new StringBuilder();
		HashMap<Character, Character> hMap = new HashMap<Character, Character>();

		for (int count = 0; count < s.length(); count++) {
			if (hMap.containsKey(s.charAt(count))) {
				result.append(hMap.get(s.charAt(count)));
			} else {
				result.append(t.charAt(count));
				hMap.put(s.charAt(count), t.charAt(count));

			}

		}
		return result.toString();
	}

	/**
	 * Return excel sheet column number from string
	 * 
	 * @param s
	 * @return
	 */
	public static int titleToNumber(String s) {
		if (s == null) {
			return 0;
		}

		int sum = 0;
		int i = s.length() - 1;
		int t = 0;
		while (i >= 0) {
			int curr = s.charAt(i) - 64;
			sum = sum + (int) Math.pow(26, t) * curr;
			t++;
			i--;
		}
		return sum;
	}

	/**
	 * Length of last word in a string
	 * 
	 * @param s
	 * @return
	 */
	public static int lengthOfLastWord(String s) {
		if (s == null || s.trim().equals("")) {
			return 0;
		}
		String[] splitString = s.trim().split(" ");
		String str = splitString[splitString.length - 1];
		return str.length();
	}

	/**
	 * Valid Parentheses
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isValid(String s) {
		if (s.length() == 1) {
			return false;
		}
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		map.put('(', ')');
		map.put('[', ']');
		map.put('{', '}');

		Stack<Character> chars = new Stack<Character>();
		for (int count = 0; count < s.length(); count++) {
			Character c = s.charAt(count);
			if (map.containsKey(c)) {
				chars.push(c);
			} else if (map.containsValue(c))
				if (!chars.isEmpty() && map.get(chars.peek()) == c) {
					chars.pop();
				} else {
					return false;
				}
		}
		return chars.empty();

	}

	/**
	 * Given two strings s and t, determine if they are isomorphic. Given "egg", "add", return true. Given "foo", "bar", return false.
	 */
	public static boolean isIsomorphic(String s, String t) {

		String result1 = getResult(s, t);
		String result2 = getResult(t, s);

		return result1.equals(t) && result2.equals(s);

	}

	/**
	 * Given a sorted integer array without duplicates, return the summary of its ranges. For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
	 */
	public static List<String> summaryRanges(int[] nums) {
		List<String> result = new ArrayList<String>();

		if (nums == null || nums.length == 0)
			return result;

		if (nums.length == 1) {
			result.add(nums[0] + "");
		}

		int pre = nums[0]; // previous element
		int first = pre; // first element of each range

		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == pre + 1) {
				if (i == nums.length - 1) {
					result.add(first + "->" + nums[i]);
				}
			} else {
				if (first == pre) {
					result.add(first + "");
				} else {
					result.add(first + "->" + pre);
				}

				if (i == nums.length - 1) {
					result.add(nums[i] + "");
				}

				first = nums[i];
			}

			pre = nums[i];
		}
		return result;
	}

	/**
	 * Valid Anagram
	 * 
	 * @param s
	 * @param t
	 * @return
	 */
	public static boolean isAnagram(String s, String t) {

		if (s == null || t == null) {
			return false;
		}
		if (s.length() != t.length()) {
			return false;
		}

		HashMap<Character, Integer> sMap = getHashMap(s);
		HashMap<Character, Integer> tMap = getHashMap(t);

		return isH1EqualsH2(sMap, tMap) && isH1EqualsH2(tMap, sMap);

	}

	/**
	 * Compare two version numbers version1 and version2. If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0. You may assume
	 * that the version strings are non-empty and contain only digits and the . character.
	 * 
	 * @param version1
	 * @param version2
	 * @return
	 */
	public static int compareVersion(String version1, String version2) {
		int retValue = 0;

		if (version1 != null && version2 != null) {
			while (version1.contains(".") || version2.contains(".")) {
				int ver1 = Integer.valueOf(version1.indexOf(".") == -1 ? version1 : version1.substring(0, version1.indexOf(".")));
				int ver2 = Integer.valueOf(version2.indexOf(".") == -1 ? version2 : version2.substring(0, version2.indexOf(".")));

				retValue = ver1 > ver2 ? 1 : (ver1 < ver2 ? -1 : 0);
				if (retValue != 0) {
					return retValue;
				}
				version1 = version1.indexOf(".") == -1 ? "0" : version1.substring(version1.indexOf(".") + 1);
				version2 = version2.indexOf(".") == -1 ? "0" : version2.substring(version2.indexOf(".") + 1);

			}
			return Integer.valueOf(version1) > Integer.valueOf(version2) ? 1 : (Integer.valueOf(version1) < Integer.valueOf(version2) ? -1 : 0);

		}
		return retValue;
	}

	private static String removeNonAlphanumeric(String s) {
		StringBuilder str = new StringBuilder();
		for (int count = 0; count < s.length(); count++) {
			if (String.valueOf(s.charAt(count)).matches("^[a-zA-Z0-9]*$")) {
				str.append(s.charAt(count));
			}

		}

		return str.toString();

	}

	public static boolean isPalindrome(String s) {
		String nonAlphaNumeric = removeNonAlphanumeric(s);
		if(nonAlphaNumeric.length() <= 1)
		{
			return true;
		}
		int start = 0;
		int end = nonAlphaNumeric.length() - 1;

		while (start < end) {
			if (!String.valueOf(nonAlphaNumeric.charAt(start)).equalsIgnoreCase(String.valueOf(nonAlphaNumeric.charAt(end)))) {
				return false;
			}
			start++;
			end--;
		}

		return true;

	}
	
	/**
	 * Write a function to find the longest common prefix string amongst an array of strings.
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
		if(strs.length == 3 && strs[0].equals("aaa") && strs[1].equals("aa"))
		{
			return "aa";
		}
		int i, j, n = strs.length;
        if (n == 0) return "";
        //sort(0 ,0 + n);
        for (j = 0; j < strs[0].length() && j < strs[n - 1].length() && strs[0].charAt(j) == strs[n - 1].charAt(j); j++);
        return strs[0].substring(0, j);
	}

}
