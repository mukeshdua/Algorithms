package com.mukeshdua.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Arr {

	// Generate pascal tree
	public List<List<Integer>> generate(int num) {
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

	// Given an index k, return the kth row of the Pascal's triangle.
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
	
	//Add plus one to number of digits
	public int[] plusOne(int[] digits) {
        int carriage = 0;
		
		for (int count = digits.length - 1; count >= 0; count--) {
			int temp = 0;
			int carraigeDigit =digits[count]+carriage;
			if(count == digits.length-1 )
			{
				carraigeDigit++;
			}
			carriage=0;
			if (carraigeDigit > 9) {
				temp = 10;
				carriage = 1;
			}
			digits[count] = carraigeDigit - temp;
			if (carriage == 0) {
				break;
			}

		}
		if(carriage==0)
		{
			return digits;
		}
		int[] retDigits= new int[digits.length +1];
		for (int count = digits.length - 1; count >= 0; count--) {
			retDigits[count + 1]=digits[count];
		}
		retDigits[0]=carriage;
		return retDigits;
    }

}
