import java.util.ArrayList;

/**
 * More Binary Tree practice problems. This problem creates BSTs of type
 * Integer: 1. Neither problem makes use of the BST ordering property; I just
 * found insert() to be a convenient way to build trees for testing. 2. I used
 * Integer instead of T since the makeTree method sets the data value of each
 * node to be a depth, which is an Integer.
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>.
 * @param <T>
 */

/*
 * TODO: 0 You are to implement the methods below. Use recursion!
 */
public class BinarySearchTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		this.root = NULL_NODE;
	}

	/**
	 * This constructor creates a full tree of Integers, where the value of each
	 * node is just the depth of that node in the tree.
	 * 
	 * @param maxDepth
	 *            The depth of the leaves in the tree.
	 */
	public BinarySearchTree(int maxDepth) {
		// TODO: 1 Write this.
		// Hint: You may find it easier if your recursive helper method is
		// outside of the BinaryNode class.
		if (maxDepth == -1) {
			this.root = NULL_NODE;
		} else {
			this.root = new BinaryNode(0);
			this.root.makeTree(maxDepth, 1);
		}

	}

	public int getSumOfHeights() {
		// TODO. 2 Write this.
		// Can you do it in O(n) time instead of O(n log n) by avoiding repeated
		// calls to height()?
		if(this.root == NULL_NODE)
			return 0;
		return this.root.getSumOfHeights();
	}

	// These are here for testing.
	public void insert(Integer e) {
		root = root.insert(e);
	}

	/**
	 * @return A string showing an in-order traversal of nodes with extra
	 *         brackets so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return root.toStructuredString();
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		@SuppressWarnings("boxing")
		public void makeTree(int maxDepth, int currentDepth) {
			if (currentDepth == maxDepth + 1)
				return;
			this.left = new BinaryNode(currentDepth);
			this.right = new BinaryNode(currentDepth);
			this.left.makeTree(maxDepth, currentDepth + 1);
			this.right.makeTree(maxDepth, currentDepth + 1);
		}

		@SuppressWarnings("boxing")
		public int getSumOfHeights() {
			ArrayList<Integer> height = new ArrayList<>();
			height.add(0,0);
			maxHeight(height);
			return height.get(0);
		}

		@SuppressWarnings("boxing")
		//using an arraylist to keep track off all of the recursive calls to height, this helper
		//changes the first value in height to the sum of all the heights.
		public int maxHeight(ArrayList<Integer> height) {
			if (this == NULL_NODE)
				return -1;
			if(this.left == NULL_NODE && this.right == NULL_NODE){
				return 0;
			}
			int tempHeight = 1 + Math.max(this.right.maxHeight(height),this.left.maxHeight(height));
			height.set(0,height.get(0) + tempHeight);
			return tempHeight;
		}

		public BinaryNode insert(Integer e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(data) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(data) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "[" + left.toStructuredString() + this.data
					+ right.toStructuredString() + "]";
		}

	}
}