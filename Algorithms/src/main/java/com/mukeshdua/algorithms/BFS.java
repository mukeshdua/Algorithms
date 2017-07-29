package com.mukeshdua.algorithms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BFS {
	// https://www.youtube.com/watch?v=0OQjzWthrOY
	// How to show graphs: https://www.khanacademy.org/computing/computer-science/algorithms/graph-representation/a/representing-graphs

	// Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
	public boolean isSymmetric(TreeNode root) {
		return isMirror(root, root);
	}

	public boolean isMirror(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}
		if (root2 == null || root1 == null) {
			return false;
		}
		return root1.val == root2.val && isMirror(root1.right, root2.left) && isMirror(root1.left, root2.right);
	}

	static int count;

	public static void BFSProcess(char[][] arr, int i, int j, boolean[][] visited, int m, int n) {

		LinkedList<BFSTuple> queue = new LinkedList<BFSTuple>();
		BFSTuple obj = new BFSTuple(i, j);
		queue.add(obj);
		visited[i][j] = true;

		while (queue.size() != 0) {
			BFSTuple temp = queue.poll();
			if (temp.get(0) != m - 1) {
				if (arr[temp.get(0) + 1][temp.get(1)] == 'X' && !visited[temp.get(0) + 1][temp.get(1)]) {
					visited[temp.get(0) + 1][temp.get(1)] = true;
					BFSTuple obj1 = new BFSTuple(temp.get(0) + 1, temp.get(1));
					queue.add(obj1);
				}
			}
			if (temp.get(0) != 0) {
				if (arr[temp.get(0) - 1][temp.get(1)] == 'X' && !visited[temp.get(0) - 1][temp.get(1)]) {
					visited[temp.get(0) - 1][temp.get(1)] = true;
					BFSTuple obj2 = new BFSTuple(temp.get(0) - 1, temp.get(1));
					queue.add(obj2);
				}
			}
			if (temp.get(1) != n - 1) {
				if (arr[temp.get(0)][temp.get(1) + 1] == 'X' && !visited[temp.get(0)][temp.get(1) + 1]) {
					visited[temp.get(0)][temp.get(1) + 1] = true;
					BFSTuple obj3 = new BFSTuple(temp.get(0), temp.get(1) + 1);
					queue.add(obj3);
				}
			}
			if (temp.get(1) != 0) {
				if (arr[temp.get(0)][temp.get(1) - 1] == 'X' && !visited[temp.get(0)][temp.get(1) - 1]) {
					visited[temp.get(0)][temp.get(1) - 1] = true;
					BFSTuple obj4 = new BFSTuple(temp.get(0), temp.get(1) - 1);
					queue.add(obj4);
				}
			}
		}

	}

	public static int BFSUtil(char[][] arr, int m, int n) {
		count = 0;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (arr[i][j] == 'X' && !visited[i][j]) {
					count++;
					BFSProcess(arr, i, j, visited, m, n);
				}
			}
		}
		return count;

	}

	public static void main(String[] args) {
		int[][] pre = { { 1, 0} };
		canFinish(2,pre);
		char[][] grid = { { '1', '1', '1', '1', '0' }, { '1', '1', '0', '1', '0' }, { '1', '1', '0', '0', '0' }, { '0', '0', '0', '0', '0' } };
		numIslands(grid);
		Scanner scan = new Scanner(System.in);
		int test = scan.nextInt();
		for (int t = 0; t < test; t++) {
			int m = scan.nextInt();
			int n = scan.nextInt();
			char[] arr[] = new char[m][n];
			int k = 0;
			for (int i = 0; i < m; i++) {
				String data = scan.next();
				for (int j = 0; j < n; j++) {
					arr[i][j] = data.charAt(j);
				}
			}
			// System.out.println(arr[1].length);
			System.out.printf("%d \n", BFSUtil(arr, m, n));
		}
	}

	public static int numIslands(char[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;
		int count = 0;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == '1' && !(visited[i][j])) {
					count++;
					BFSNumberIslands(grid, i, j, visited, m, n);
				}
			}
		}

		return count;

	}

	public static void BFSNumberIslands(char[][] arr, int i, int j, boolean[][] visited, int m, int n) {
		int[] tuple = new int[] { i, j };
		LinkedList<int[]> queue = new LinkedList<int[]>();
		queue.add(tuple);
		visited[i][j] = true;

		while (queue.size() != 0) {
			int[] temp = queue.poll();
			if (temp[0] != m - 1) {
				if (arr[temp[0] + 1][temp[1]] == '1' && !visited[temp[0] + 1][temp[1]]) {
					visited[temp[0] + 1][temp[1]] = true;
					int[] obj1 = new int[] { temp[0] + 1, temp[1] };
					queue.add(obj1);
				}
			}
			if (temp[0] != 0) {
				if (arr[temp[0] - 1][temp[1]] == '1' && !visited[temp[0] - 1][temp[1]]) {
					visited[temp[0] - 1][temp[1]] = true;
					int[] obj2 = new int[] { temp[0] - 1, temp[1] };
					queue.add(obj2);
				}
			}
			if (temp[1] != n - 1) {
				if (arr[temp[0]][temp[1] + 1] == '1' && !visited[temp[0]][temp[1] + 1]) {
					visited[temp[0]][temp[1] + 1] = true;
					int[] obj3 = new int[] { temp[0], temp[1] + 1 };
					queue.add(obj3);
				}
			}
			if (temp[1] != 0) {
				if (arr[temp[0]][temp[1] - 1] == '1' && !visited[temp[0]][temp[1] - 1]) {
					visited[temp[0]][temp[1] - 1] = true;
					int[] obj4 = new int[] { temp[0], temp[1] - 1 };
					queue.add(obj4);
				}
			}
		}

	}

	/*
	 * There are a total of n courses you have to take, labeled from 0 to n - 1.
	 * 
	 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
	 * 
	 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
	 * 
	 * For example:
	 * 
	 * 2, [[1,0]] There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
	 * 
	 * 2, [[1,0],[0,1]] There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have
	 * finished course 1. So it is impossible.
	 */
	public static boolean canFinish(int numCourses, int[][] prerequisites) {
		if (numCourses <= 0)
			return false;
		Queue<Integer> queue = new LinkedList<Integer>();
		int[] inDegree = new int[numCourses];
		for (int i = 0; i < prerequisites.length; i++) {
			inDegree[prerequisites[i][1]]++;
		}
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] == 0)
				queue.offer(i);
		}
		while (!queue.isEmpty()) {
			int x = queue.poll();
			for (int i = 0; i < prerequisites.length; i++) {
				if (x == prerequisites[i][0]) {
					inDegree[prerequisites[i][1]]--;
					if (inDegree[prerequisites[i][1]] == 0)
						queue.offer(prerequisites[i][1]);
				}
			}
		}
		for (int i = 0; i < inDegree.length; i++) {
			if (inDegree[i] != 0)
				return false;
		}
		return true;
	}
}
