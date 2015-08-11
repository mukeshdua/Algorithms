package com.mukeshdua.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Arr {

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

}
