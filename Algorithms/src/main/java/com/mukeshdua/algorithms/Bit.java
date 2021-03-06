package com.mukeshdua.algorithms;

public class Bit {

	/**
	 * Reverse bits of a given 32 bits unsigned integer.
	 */
	public static int reverseBits(int in) {
		int out = 0;
		for (int ii = 0; ii < 32; ii++) {
			int bit = (in & 1);
			out = (out << 1) | bit;
			in = in >> 1;
		}
		return out;

	}

	/**
	 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
	 */
	public static int hammingWeight(int x) {
		int total_ones = 0;
		while (x != 0) {
			x = x & (x - 1);
			total_ones++;
		}
		return total_ones;
	}
	
	/*
	 * Given an array of integers, every element appears twice except for one. Find that single one.
	 */
	public static int singleNumber(int[] A) {
		int x = 0;
		for (int a : A) {
			x = x ^ a;
		}
		return x;
	}
	
	public static void main(String[] args)
	{
		singleNumber(new int[]{4,1,3,1,4});
	}

}
