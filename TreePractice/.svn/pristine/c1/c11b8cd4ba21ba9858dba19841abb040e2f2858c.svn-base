/**
 * Binary Tree practice problems
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>. 2014.
 * @param <T>
 */

/*
 * DONE: 0 You are to implement the four methods below. I took most of them from
 * a CSSE230 exam given in a prior term. These can all be solved by recursion -
 * I encourage you to do so too, since most students find practicing recursion
 * to be more useful.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

	private BinaryNode root;
	private BinaryNode parent;
	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		root = NULL_NODE;

	}

	/**
	 * This method counts the number of occurrences of positive Integers in the
	 * tree that is of type Integer. Hint: You may assume this tree contains
	 * integers, so may use a cast.
	 * 
	 * @return The number of positive integers in the tree.
	 */

	@SuppressWarnings("boxing")
	public int countPositives() {
		// DONE: 1 Write this.
		// Implemented recursively in BinaryNode Class
		return this.root.countPositives(this.root);
	}

	/**
	 * Recall that the depth of a node is number of edges in a path from this
	 * node to the root. Returns the depth of the given item in the tree. If the
	 * item isn't in the tree, then it returns -1.
	 * 
	 * @param item
	 * @return The depth, or -1 if it isn't in the tree.
	 */
	public int getDepth(T item) {
		// DONE: 2 Write this.
		if (this.root == NULL_NODE) {
			return -1;
		}
		if (this.root.contains(item)) {
			return this.root.getDepth(this.root, item);
		}
		return -1;
	}

	/**
	 * This method visits each node of the BST in pre-order and determines the
	 * number of children of each node. It produces a string of those numbers.
	 * If the tree is empty, an empty string is to be returned. For the
	 * following tree, the method returns the string: "2200110"
	 * 
	 * 10 5 15 2 7 18 10
	 * 
	 * @return A string representing the number of children of each node when
	 *         the nodes are visited in pre-order.
	 */

	public String numChildrenOfEachNode() {
		// DONE: 3 Write this.
		if (this.root == NULL_NODE) {
			return "";
		}
		return this.root.numChildrenOfEachNode();
	}

	/**
	 * This method determines if a BST forms a zig-zag pattern. By this we mean
	 * that each node has exactly one child, except for the leaf. In addition,
	 * the nodes alternate between being a left and a right child. An empty tree
	 * or a tree consisting of just the root are both said to form a zigzag
	 * pattern. For example, if you insert the elements 10, 5, 9, 6, 7 into a
	 * BST in that order. , you will get a zig-zag.
	 * 
	 * @return True if the tree forms a zigzag and false otherwise.
	 */
	public boolean isZigZag() {
		// DONE: 4 Write this.
		if (this.root == NULL_NODE)
			return true;
		if(this.root.left == NULL_NODE && this.root.right != NULL_NODE){
			this.parent = this.root;
			return this.root.right.isZigZag();
		}
		if(this.root.right == NULL_NODE && this.root.left !=NULL_NODE){
			this.parent = this.root;
			return this.root.left.isZigZag();
		}
		if(this.root.right == NULL_NODE && this.root.right == NULL_NODE){
			return true;
		}
		return false;
	}

	public void insert(T e) {
		root = root.insert(e);
	}

	// /////////////// BinaryNode
	public class BinaryNode {

		public T element;
		public BinaryNode left;
		public BinaryNode right;


		public BinaryNode(T element) {
			this.element = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
//			this.parent = NULL_NODE;
		}

		public boolean isZigZag() {
			if (this.left == NULL_NODE && this.right == NULL_NODE) {
				return true;
			}
			if (this.left != NULL_NODE && this.right != NULL_NODE) {
				return false;
			}
			if (this.right != NULL_NODE) {
				if (parent.left != NULL_NODE) {
					parent = this;
					return this.right.isZigZag();
				}
				return false;
			}
			if (parent.right != NULL_NODE) {
				parent = this;
				return this.left.isZigZag();
			}
			return false;

		}

		public String numChildrenOfEachNode() {
			if (this.left == NULL_NODE && this.right == NULL_NODE) {
				return "0";
			}
			if (this.left != NULL_NODE && this.right == NULL_NODE) {
				return "1" + this.left.numChildrenOfEachNode();
			}
			if (this.right != NULL_NODE && this.left == NULL_NODE) {
				return "1" + this.right.numChildrenOfEachNode();
			}
			return "2" + this.left.numChildrenOfEachNode()
					+ this.right.numChildrenOfEachNode();
		}

		public boolean contains(T item) {
			// checks right subtree if less than 0
			if (this.element.compareTo(item) < 0) {
				if (this.right != NULL_NODE) {
					return this.right.contains(item);
				}
				return false;
			}// left subtree if data is greater than item
			if (this.element.compareTo(item) > 0) {
				if (this.left != NULL_NODE) {
					return this.left.contains(item);
				}
				return false;
			}
			return true;
		}

		public int getDepth(BinaryNode node, T item) {
			if (node.element.compareTo(item) == 0) {
				return 0;
			}
			if (node.element.compareTo(item) > 0) {
				return 1 + getDepth(node.left, item);
			}
			return 1 + getDepth(node.right, item);

		}

		public BinaryNode insert(T e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e.compareTo(element) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(element) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public int countPositives(BinaryNode node) {
			if (node == NULL_NODE) {
				return 0;
			}
			if (node.left == NULL_NODE && node.right == NULL_NODE) {
				if ((int) node.element >= 1) {
					return 1;
				}
				return 0;
			}
			if (node.left == NULL_NODE) {
				if ((int) node.element >= 1) {
					return 1 + countPositives(node.right);
				}
				// return countPositives(node.right);
			}
			if (node.right == NULL_NODE) {
				if ((int) node.element >= 1) {
					return 1 + countPositives(node.left);
				}
				// return countPositives(node.left);
			}
			if ((int) node.element >= 1) {
				return 1 + countPositives(node.left)
						+ countPositives(node.right);
			}
			return countPositives(node.left) + countPositives(node.right);
		}
	}
}