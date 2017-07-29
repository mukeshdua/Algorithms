package com.mukeshdua.algorithms;

public class BFSTuple {

	int[] new_list;

	BFSTuple(int i, int j){
			new_list = new int[] {i, j};
		}

	public int get(int i) {
		return new_list[i];
	}

}
