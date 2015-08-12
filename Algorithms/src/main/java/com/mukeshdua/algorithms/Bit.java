package com.mukeshdua.algorithms;

public class Bit {

	// Reverse bits of a given 32 bits unsigned integer.
	public static int reverseBits(int in) {
		int out = 0;
		for (int ii = 0; ii < 32; ii++) {
			int bit = (in & 1);
			out = (out << 1) | bit;
			in = in >> 1;
		}
		return out;

	}

}
