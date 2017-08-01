package com.mukeshdua.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Tree can be travered 4 ways:
// 1. Preorder- Recursion
// 2. Inorder - Recursion
// 3. PostOrder - Recursion
// 4. Level order - Queue
public class Tree {
	TreeNode root;

	Tree(int[] arr) throws Exception {
		// if (arr.length == 0)
		// throw new Exception("Arraylength is 0");
		root = sortedArrayToBST(arr, 0, arr.length - 1);

	}

	public static TreeNode sortedArrayToBST(int[] arr) {
		if (arr.length == 0)
			return null;
		return sortedArrayToBST(arr, 0, arr.length - 1);

	}

	/*
	 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all
	 * keys greater than the original key in BST.
	 * 
	 * Example:
	 * 
	 * Input: The root of a Binary Search Tree like this: 5 / \ 2 13
	 * 
	 * Output: The root of a Greater Tree like this: 18 / \ 20 13
	 * 
	 */
	int sum = 0;

	public TreeNode convertBST(TreeNode root) {
		if (root == null) {
			return null;
		}

		convertBST(root.right);
		int temp = root.val;
		sum = sum + temp;
		root.val = sum;
		convertBST(root.left);
		return root;
	}

	/**
	 * Convert sorted array to Tree
	 * 
	 * @param num
	 * @param start
	 * @param end
	 * @return
	 */
	public static TreeNode sortedArrayToBST(int[] num, int start, int end) {
		if (start > end)
			return null;

		int mid = (start + end) / 2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = sortedArrayToBST(num, start, mid - 1);
		root.right = sortedArrayToBST(num, mid + 1, end);

		return root;
	}

	/**
	 * Print pre-order
	 * 
	 * @param root
	 */
	public static void preOrder(TreeNode root) {

		if (root == null)
			return;

		System.out.println(root.val);

		preOrder(root.left);
		preOrder(root.right);

	}

	/**
	 * print inorder
	 * 
	 * @param root
	 */
	public static void inOrder(TreeNode root) {

		if (root == null)
			return;

		inOrder(root.left);
		System.out.println(root.val);
		inOrder(root.right);

	}

	/**
	 * Print postOrder
	 * 
	 * @param root
	 */
	public static void postOrder(TreeNode root) {

		if (root == null)
			return;

		postOrder(root.left);
		postOrder(root.right);
		System.out.println(root.val);

	}

	/**
	 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
	 * 
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> levelOrder(TreeNode root) {
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

	/**
	 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).BOTTOM UP
	 * 
	 * @param root
	 * @return
	 */
	public static List<List<Integer>> levelOrderBottom(TreeNode root) {
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

	/**
	 * InvertTree
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode invertTree(TreeNode root) {
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

	/**
	 * vb
	 * 
	 * @param p
	 * @param q
	 * @return
	 */
	public static boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		} else if ((p != null && q == null) || (p == null && q != null)) {
			return false;
		}

		return isSameTree(p.left, q.left) && p.val == q.val && isSameTree(p.right, q.right);

	}

	/**
	 * Lowest common Ancestor
	 * 
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
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

	private int getHeightLeft(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return getHeightLeft(root.left) + 1;

	}

	private int getHeightRight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return getHeightRight(root.right) + 1;

	}

	/**
	 * Given a complete binary tree, count the number of nodes.
	 * 
	 * Definition of a complete binary tree from Wikipedia: In a complete binary tree every level, except possibly the last, is completely filled, and all nodes
	 * in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
	 * 
	 * @param root
	 * @return
	 */
	public int countNodes(TreeNode root) {

		if (root == null) {
			return 0;
		}
		int leftHeight = getHeightLeft(root.left);
		int rightHeight = getHeightRight(root.right);
		if (leftHeight == rightHeight) {
			return (2 << (leftHeight)) - 1;
		} else {
			return countNodes(root.left) + countNodes(root.right) + 1;
		}
	}

	/**
	 * Return all tree nodes from top to bottom
	 * 
	 * @param root
	 * @param str
	 * @return
	 */
	public static List<String> getTreeNodes(TreeNode root, String str) {
		if (root == null) {
			str = "";
			return null;

		}
		str += !str.equals("") ? "->" : "";
		str += root.val;
		if (root.left == null && root.right == null) {
			List<String> result = new ArrayList();
			result.add(str.trim());
			return result;
		}
		List<String> list = new ArrayList<String>();
		List<String> tempList = getTreeNodes(root.left, str);
		if (tempList != null) {
			list.addAll(tempList);
		}
		tempList = getTreeNodes(root.right, str);
		if (tempList != null) {
			list.addAll(tempList);
		}
		return list;

	}

	/***
	 * Check if 2 trees are Symmetric
	 * 
	 * @param root
	 * @return
	 */
	public static boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isSymmetric(root.left, root.right);
	}

	private static boolean isSymmetric(TreeNode left, TreeNode right) {
		if (left == null || right == null) {
			return left == null && right == null;
		}
		return left.val == right.val && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
	}

	/**
	 * Max Depth
	 * 
	 * @param root
	 * @return
	 */
	public static int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.val == -1) {
			return -1;
		}
		int maxLeft = maxDepth(root.left);
		int maxRight = maxDepth(root.right);
		return maxLeft > maxRight ? maxLeft + 1 : maxRight + 1;
	}

	/**
	 * isTreeBalanced
	 * 
	 * @param root
	 * @return
	 */
	public static boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		int maxLeft = maxDepth(root.left);
		int maxRight = maxDepth(root.right);

		if ((maxLeft > maxRight && maxLeft - maxRight > 1) || maxLeft < maxRight && maxRight - maxLeft > 1) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.
	 * 
	 * @param root
	 * @param str
	 * @return
	 */
	public static boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		sum = sum - root.val;
		if (root.left == null && root.right == null) {
			if (sum == 0) {
				return true;
			} else {
				return false;
			}
		}
		boolean left = hasPathSum(root.left, sum);
		boolean right = hasPathSum(root.right, sum);
		return left || right;

	}

	TreeNode mirror(TreeNode node) {
		if (node == null)
			return node;

		/* do the subtrees */
		TreeNode left = mirror(node.left);
		TreeNode right = mirror(node.right);

		/* swap the left and right pointers */
		node.left = right;
		node.right = left;

		return node;
	}
	
	int isSumTree(TreeNode node) 
    {
        int ls, rs;
  
        /* If node is NULL or it's a leaf node then
           return true */
        if ((node == null) || (node.left == null && node.right == null))
            return 1;
  
        /* Get sum of nodes in left and right subtrees */
        ls = sum(node.left);
        rs = sum(node.right);
  
        /* if the node and both of its children satisfy the
           property return 1 else 0*/
        if ((node.val == ls + rs) && (isSumTree(node.left) != 0)
                && (isSumTree(node.right)) != 0)
            return 1;
  
        return 0;
    }
	
	int sum(TreeNode node) 
    {
        if (node == null)
            return 0;
        return sum(node.left) + node.val + sum(node.right);
    }

}
