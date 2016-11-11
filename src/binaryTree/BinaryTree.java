package binaryTree;

import java.util.*;

public class BinaryTree {
	// TreeNode class
	private static class TreeNode {
		int val;
		TreeNode leftNode;
		TreeNode rightNode;

		public TreeNode() {

		};

		public TreeNode(int i) {
			this.val = i;
			this.leftNode = null;
			this.rightNode = null;
		}
	}

	// build a binary tree
	public static TreeNode buildTree(int[] array) {
		List<TreeNode> list = new LinkedList<TreeNode>();
		for (int i = 0; i < array.length; i++)
			list.add(new TreeNode(array[i]));
		for (int i = 0; i < array.length / 2 - 1; i++) {
			list.get(i).leftNode = list.get(2 * i + 1);
			list.get(i).rightNode = list.get(2 * i + 2);
		}
		if (array.length % 2 == 0)
			list.get(array.length / 2 - 1).leftNode = list.get(list.size() - 1);
		else {
			list.get(array.length / 2 - 1).leftNode = list.get(list.size() - 2);
			list.get(array.length / 2 - 1).rightNode = list.get(list.size() - 1);
		}
		return list.get(0);
	}

	// get the number of nodes in the tree (REC)
	public static int getNumber(TreeNode root) {
		if (root == null)
			return 0;
		return getNumber(root.leftNode) + getNumber(root.rightNode) + 1;
	}

	// get the number of nodes in the tree (non-REC), use Queue
	public static int getNumberNonREC(TreeNode root) {
		if (root == null)
			return 0;
		int count = 1;
		Queue<TreeNode> list = new LinkedList<TreeNode>();
		list.add(root);
		while (!list.isEmpty()) {
			TreeNode currentNode = list.remove();
			if (currentNode.leftNode != null) {
				list.add(currentNode.leftNode);
				count++;
			}
			if (currentNode.rightNode != null) {
				list.add(currentNode.rightNode);
				count++;
			}
		}
		return count;
	}

	// get the depth of the tree(REC)
	public static int getDepth(TreeNode root) {
		if (root == null)
			return 0;
		else {
			return Math.max(getDepth(root.leftNode), getDepth(root.rightNode)) + 1;
		}
	}

	// get the depth of the tree(non-REC), use Queue
	public static int getDepthNonREC(TreeNode root) {
		if (root == null)
			return 0;
		int depth = 0, currentLevelNums = 1, nextLevelNums = 0;
		Queue<TreeNode> list = new LinkedList<TreeNode>();
		list.add(root);
		while (!list.isEmpty()) {
			TreeNode curr = list.remove();
			currentLevelNums--;
			if (curr.leftNode != null) {
				list.add(curr.leftNode);
				nextLevelNums++;
			}
			if (curr.rightNode != null) {
				list.add(curr.rightNode);
				nextLevelNums++;
			}
			if (currentLevelNums == 0) {
				currentLevelNums = nextLevelNums;
				nextLevelNums = 0;
				depth++;
			}
		}
		return depth;

	}

	// pre-order traverse(REC)
	public static void preOrder(TreeNode root) {
		if (root == null)
			return;
		System.out.println(root.val);
		preOrder(root.leftNode);
		preOrder(root.rightNode);
	}

	// pre-order traverse(non-REC)

	// in-order traverse(REC)
	public static void inOrder(TreeNode root) {
		if (root == null)
			return;
		inOrder(root.leftNode);
		System.out.println(root.val);
		inOrder(root.rightNode);
	}

	// in-order traverse(non-REC)

	// post-order traverse(REC)
	public static void postOrder(TreeNode root) {
		if (root == null)
			return;
		postOrder(root.leftNode);
		postOrder(root.rightNode);
		System.out.println(root.val);
	}

	// post-order traverse(non-REC)
	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		TreeNode root = buildTree(array);
		// System.out.println(getNumber(root));
		// System.out.println(getNumberNonREC(root));
		// System.out.println(getDepth(root));
		// System.out.println(getDepthNonREC(root));
		// preOrder(root);
		// inOrder(root);
		// postOrder(root);
	}
}
