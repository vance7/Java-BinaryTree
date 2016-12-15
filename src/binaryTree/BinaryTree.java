package binaryTree;

import java.util.*;

public class BinaryTree {
	// TreeNode class
	private static class TreeNode {
		int val;
		TreeNode leftNode;
		TreeNode rightNode;

		public TreeNode(int i) {
			this.val = i;
			this.leftNode = null;
			this.rightNode = null;
		}

		public TreeNode(Object object) {
		}
	}

	// build a binary tree by an array
	public static TreeNode buildTree(int[] array) {
		if (array.length == 1)
			return new TreeNode(array[0]);
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
		System.out.print(root.val);
		preOrder(root.leftNode);
		preOrder(root.rightNode);
	}

	// pre-order traverse(non-REC), use Stack
	public static void preOrderNonREC(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current = root;
		while (current != null || !stack.isEmpty()) {
			if (current != null) {
				stack.push(current);
				System.out.print(current.val);
				current = current.leftNode;
			} else {
				current = stack.pop();
				current = current.rightNode;
			}
		}
	}

	// in-order traverse(REC)
	public static void inOrder(TreeNode root) {
		if (root == null)
			return;
		inOrder(root.leftNode);
		System.out.print(root.val);
		inOrder(root.rightNode);
	}

	// in-order traverse(non-REC)
	public static void inOrderNonREC(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode current = root;
		while (current != null || !stack.isEmpty()) {
			if (current != null) {
				stack.push(current);
				current = current.leftNode;
			} else {
				current = stack.pop();
				System.out.print(current.val);
				current = current.rightNode;
			}
		}
	}

	// post-order traverse(REC)
	public static void postOrder(TreeNode root) {
		if (root == null)
			return;
		postOrder(root.leftNode);
		postOrder(root.rightNode);
		System.out.print(root.val);
	}

	// post-order traverse(non-REC)
	public static void postOrderNonREC(TreeNode root) {
		if (root == null)
			return;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		Stack<TreeNode> outPut = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode current = stack.pop();
			outPut.push(current);
			if (current.leftNode != null)
				stack.push(current.leftNode);
			if (current.rightNode != null)
				stack.push(current.rightNode);
		}
		while (!outPut.isEmpty())
			System.out.print(outPut.pop().val);
	}

	// level traverse (non-REC) rec method is pretty rare.
	public static void levelTraverse(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			System.out.print(current.val);
			if (current.leftNode != null)
				queue.add(current.leftNode);
			if (current.rightNode != null)
				queue.add(current.rightNode);
		}
	}

	// return the number of kth depth of the tree(REC)
	public static int getNumberOfKthLevel(TreeNode root, int k) {
		if (root == null)
			return 0;
		else if (k == 1)
			return 1;
		else {
			int leftNodes = getNumberOfKthLevel(root.leftNode, k - 1);
			int rightNodes = getNumberOfKthLevel(root.rightNode, k - 1);
			return leftNodes + rightNodes;
		}
	}

	// return the number of kth depth of the tree(non-REC)
	public static int getNumberOfKthLevelNonREC(TreeNode root, int k) {
		if (root == null || k == 0)
			return 0;
		if (k == 1)
			return 1;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		int currentDepth = 1;
		int currentLevelNodes = 1;
		int nextLevelNodes = 0;
		while (!queue.isEmpty() && currentDepth < k) {
			TreeNode curr = queue.remove();
			currentLevelNodes--;
			if (curr.leftNode != null) {
				queue.add(curr.leftNode);
				nextLevelNodes++;
			}
			if (curr.rightNode != null) {
				queue.add(curr.rightNode);
				nextLevelNodes++;
			}
			if (currentLevelNodes == 0) {
				currentLevelNodes = nextLevelNodes;
				nextLevelNodes = 0;
				currentDepth++;
			}
		}
		return currentLevelNodes;
	}

	// return the number of leaf(REC)
	public static int getLeaf(TreeNode root) {
		if (root == null)
			return 0;
		if (root.leftNode == null && root.rightNode == null)
			return 1;
		return getLeaf(root.leftNode) + getLeaf(root.rightNode);
	}

	// return the number of leaf(non-REC)
	public static int getLeafNonREC(TreeNode root) {
		if (root == null)
			return 0;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		int leaf = 0;
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode curr = queue.remove();
			if (curr.leftNode != null)
				queue.add(curr.leftNode);
			if (curr.rightNode != null)
				queue.add(curr.rightNode);
			if (curr.leftNode == null && curr.rightNode == null)
				leaf++;
		}
		return leaf;
	}

	// determine whether two trees are same(REC)
	public static boolean sameTree(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return true;
		else if (root1 == null && root2 != null)
			return false;
		else if (root1 != null && root2 == null)
			return false;
		else {
			boolean left = sameTree(root1.leftNode, root2.leftNode);
			boolean right = sameTree(root1.rightNode, root2.rightNode);
			return left && right;
		}
	}

	// determine whether two trees are same(non-REC)
	public static boolean sameTreeNonREC(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		s1.push(root1);
		s2.push(root2);
		while (!s1.isEmpty() && !s2.isEmpty()) {
			TreeNode curr1 = s1.pop();
			TreeNode curr2 = s2.pop();
			if (curr1 == null && curr2 == null)
				continue;
			if (curr1 != null && curr2 != null && curr1.val == curr2.val) {
				s1.push(curr1.leftNode);
				s1.push(curr1.rightNode);
				s2.push(curr2.leftNode);
				s2.push(curr2.rightNode);
			} else
				return false;
		}
		return true;
	}

	// get the mirror tree by changing the structure(REC)
	public static TreeNode getMirror(TreeNode root) {
		if (root == null)
			return root;
		TreeNode left = getMirror(root.leftNode);
		TreeNode right = getMirror(root.rightNode);
		root.rightNode = left;
		root.leftNode = right;
		return root;
	}

	// get the mirror tree by changing the structure(non-REC)
	public static TreeNode getMirrorNonREC(TreeNode root) {
		if (root == null)
			return root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.isEmpty()) {
			TreeNode curr = stack.pop();

			if (curr.leftNode != null)
				stack.push(curr.leftNode);
			if (curr.rightNode != null)
				stack.push(curr.rightNode);
			TreeNode temp = curr.leftNode;
			curr.leftNode = curr.rightNode;
			curr.rightNode = temp;
		}
		return root;
	}

	// get the mirror tree by NOT changing the structure(REC)
	public static TreeNode getMirrorCopy(TreeNode root) {
		if (root == null)
			return root;
		TreeNode copy = new TreeNode(root.val);
		copy.leftNode = getMirrorCopy(root.rightNode);
		copy.rightNode = getMirrorCopy(root.leftNode);
		return copy;
	}

	// get the mirror tree by NOT changing the structure(non-REC)
	public static TreeNode getMirrorCopyNonREC(TreeNode root) {
		if (root == null)
			return root;
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		s1.push(root);
		TreeNode newRoot = new TreeNode(root.val);
		s2.push(newRoot);
		while (!s1.isEmpty()) {
			TreeNode curr1 = s1.pop();
			TreeNode curr2 = s2.pop();
			if (curr1.leftNode != null) {
				s1.push(curr1.leftNode);
				curr2.rightNode = new TreeNode(curr1.leftNode.val);
				s2.push(curr2.rightNode);
			}
			if (curr1.rightNode != null) {
				s1.push(curr1.rightNode);
				curr2.leftNode = new TreeNode(curr1.rightNode.val);
				s2.push(curr2.leftNode);
			}
		}
		return newRoot;
	}

	// check whether two trees are symmetric(REC)
	public static boolean isMirror(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null)
			return true;
		if (root1 == null || root2 == null)
			return false;
		if (root1.val != root2.val)
			return false;
		return isMirror(root1.leftNode, root2.rightNode) && isMirror(root1.rightNode, root2.leftNode);
	}

	// check whether a tree is symmetric(REC)
	public static boolean isMirrorTree(TreeNode root) {
		if (root == null)
			return true;
		else
			return isMirror(root.leftNode, root.rightNode);
	}

	// return lowest common ancestor(REC)
	public static TreeNode LCA(TreeNode root, TreeNode n1, TreeNode n2) {
		if (!inTree(root, n1) || !inTree(root, n2))
			return new TreeNode(-1);
		if (inTree(root.leftNode, n1)) {
			if (inTree(root.rightNode, n2))
				return root;
			else
				return LCA(root.leftNode, n1, n2);

		} else {
			if (inTree(root.leftNode, n2))
				return root;
			else
				return LCA(root.rightNode, n1, n2);
		}

	}

	// check whether a node is in the tree(REC)
	public static boolean inTree(TreeNode root, TreeNode n) {
		if (root == null || n == null)
			return false;
		if (root == n)
			return true;
		return inTree(root.leftNode, n) || inTree(root.rightNode, n);
	}

	// check is balanced tree(REC)
	public static boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;
		if (Math.abs(getDepth(root.leftNode) - getDepth(root.rightNode)) > 1)
			return false;
		return isBalanced(root.leftNode) && isBalanced(root.rightNode);
	}

	// check if the tree is full tree. Each node has two or 0 child nodes.
	public static boolean checkFullTree(TreeNode root) {
		if (root == null)
			return true;
		if (root.leftNode != null && root.rightNode == null)
			return false;
		if (root.leftNode == null && root.rightNode != null)
			return false;
		return checkFullTree(root.leftNode) && checkFullTree(root.rightNode);
	}

	// check if the tree is full tree. (non-REC)
	public static boolean checkFullTreeNonREC(TreeNode root) {
		if (root == null)
			return true;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode curr = queue.remove();
			if (curr.leftNode != null && curr.rightNode != null) {
				queue.add(curr.leftNode);
				queue.add(curr.rightNode);
			}
			if (curr.leftNode != null && curr.rightNode == null)
				return false;
			if (curr.leftNode == null && curr.rightNode != null)
				return false;
		}
		return true;
	}

	// check if the tree is complete tree (non-REC)
	public static boolean checkCompletedTreeNonREC(TreeNode root) {
		if (root == null)
			return true;
		Queue<TreeNode> queue = new LinkedList<>();
		boolean mustHaveNoChild = false;
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode curr = queue.remove();
			if (mustHaveNoChild) {
				if (curr.leftNode != null || curr.rightNode != null) {
					return false;
				}
			} else {
				if (curr.leftNode != null && curr.rightNode != null) {
					queue.add(curr.leftNode);
					queue.add(curr.rightNode);
				} else if (curr.leftNode != null && curr.rightNode == null) {
					queue.add(curr.leftNode);
					mustHaveNoChild = true;
				} else if (curr.leftNode == null && curr.rightNode != null) {
					return false;
				} else
					mustHaveNoChild = true;
			}
		}
		return true;
	}

	// determine if the tree has a root-to-leaf path such that adding up all the
	// values along the path equals the given sum.
	public static boolean hasPathSum(TreeNode root, int sum) {
		if (root == null)
			return false;
		if (root.leftNode == null && root.rightNode == null)
			return root.val == sum;
		return hasPathSum(root.leftNode, sum - root.val) || hasPathSum(root.rightNode, sum - root.val);
	}

	// determine the paths which sum to a given value(no need from root to leaf)
	public static int pathSum(TreeNode root, int sum) {
		if (root == null)
			return 0;
		return findPath(root, sum) + pathSum(root.leftNode, sum) + pathSum(root.rightNode, sum);
	}

	public static int findPath(TreeNode root, int sum) {
		int res = 0;
		if (root == null)
			return res;
		if (sum == root.val)
			res++;
		res += findPath(root.leftNode, sum - root.val);
		res += findPath(root.rightNode, sum - root.val);
		return res;
	}

	// construct a tree according to inOrder and preOrder
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return helper(0, 0, preorder.length - 1, preorder, inorder);
	}

	public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (inStart > inEnd || preStart > preorder.length - 1)
			return null;
		TreeNode root = new TreeNode(preorder[preStart]);
		int index = 0;
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val)
				index = i;
		}
		root.leftNode = helper(preStart + 1, inStart, index - 1, preorder, inorder);
		root.rightNode = helper(preStart + index + 1 - inStart, index + 1, inEnd, preorder, inorder);
		return root;
	}

	
	public static void main(String[] args) {
	}
}
