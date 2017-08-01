package com.mukeshdua.algorithms;

import java.util.PriorityQueue;

public class Heap {
	
	// each time connect the two shortest ropes
    public static int minCost(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int res = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int num : nums) {
            queue.add(num);
        }
        while (queue.size() > 1) {
            int cost = queue.poll() + queue.poll();
            res += cost;
            queue.offer(cost);
        }
        return res;
    }
    
    public static void main(String[] args)
    {
    	minCost(new int[]{5,6,2,3});
    }

}
