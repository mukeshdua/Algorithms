package com.mukeshdua.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Tree {
	TreeNode	root;

	Tree(int[] arr) throws Exception {
		if (arr.length == 0)
			throw new Exception("Arraylength is 0");
		root = sortedArrayToBST(arr, 0, arr.length - 1);

	}

	// Convert sorted array to Tree
	public TreeNode sortedArrayToBST(int[] num, int start, int end) {
		if (start > end)
			return null;

		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = sortedArrayToBST(num, start, mid - 1);
		root.right = sortedArrayToBST(num, mid + 1, end);

		return root;
	}

	// Print pre-order
	void preOrder(TreeNode root) {

		if (root == null)
			return;

		System.out.println(root.val);

		preOrder(root.left);
		preOrder(root.right);

	}

	// print inorder
	void inOrder(TreeNode root) {

		if (root == null)
			return;

		inOrder(root.left);
		System.out.println(root.val);
		inOrder(root.right);

	}

	// Print postOrder
	void postOrder(TreeNode root) {

		if (root == null)
			return;

		postOrder(root.left);
		postOrder(root.right);
		System.out.println(root.val);

	}

	// Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> retValues = new ArrayList<List<Integer>>();
		if (root == null) {
			return retValues;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		int currentlevel = 1;
		int nextlevel = 0;

		List<Integer> values = new ArrayList<Integer>();
		while (!q.isEmpty()) {
			TreeNode node = q.remove();
			currentlevel--;
			values.add(node.val);
			if (node.left != null) {
				q.add(node.left);
				nextlevel++;
			}
			if (node.right != null) {
				q.add(node.right);
				nextlevel++;
			}
			if (currentlevel == 0) {
				retValues.add(values);
				values = new ArrayList<Integer>();
				currentlevel = nextlevel;
				nextlevel = 0;
			}

		}
		return retValues;
	}

	// Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).BOTTOM UP
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> retValues = new ArrayList<List<Integer>>();
		if (root == null) {
			return retValues;
		}
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		int currentlevel = 1;
		int nextlevel = 0;

		List<Integer> values = new ArrayList<Integer>();
		while (!q.isEmpty()) {
			TreeNode node = q.remove();
			currentlevel--;
			values.add(node.val);
			if (node.left != null) {
				q.add(node.left);
				nextlevel++;
			}
			if (node.right != null) {
				q.add(node.right);
				nextlevel++;
			}
			if (currentlevel == 0) {
				retValues.add(values);
				values = new ArrayList<Integer>();
				currentlevel = nextlevel;
				nextlevel = 0;
			}

		}

		List<List<Integer>> finalRetValues = new ArrayList<List<Integer>>();
		for (int count = retValues.size() - 1; count >= 0; count--) {
			finalRetValues.add(retValues.get(count));
		}
		return finalRetValues;
	}

	// InvertTree
	public TreeNode invertTree(TreeNode root) {
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();

		if (root != null) {
			queue.add(root);
		}

		while (!queue.isEmpty()) {
			TreeNode p = queue.poll();
			if (p.left != null)
				queue.add(p.left);
			if (p.right != null)
				queue.add(p.right);

			TreeNode temp = p.left;
			p.left = p.right;
			p.right = temp;
		}

		return root;
	}

	// Check if 2 BSTs are same
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if ((p != null && q == null) || (p == null && q != null)) {
			return false;
		}

		return isSameTree(p.left, q.left) && p.val == q.val && isSameTree(p.right, q.right);

	}

	// Lowest common Ancestor
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || p == null || q == null) {
			return null;
		}
		if (Math.max(p.val, q.val) < root.val) {
			return lowestCommonAncestor(root.left, p, q);
		} else if (Math.min(p.val, q.val) > root.val) {
			return lowestCommonAncestor(root.right, p, q);
		} else {
			return root;
		}

	}

}
