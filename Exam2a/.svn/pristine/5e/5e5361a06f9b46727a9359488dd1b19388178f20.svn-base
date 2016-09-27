import java.util.Stack;

/**
 * Exam 2a. Tree methods.
 * 
 * @author Matt Boutell and TODO: You!
 * @param <T>
 */

/*
 * TODO: Directions: Implement the methods below. See the paper for details.
 */
public class BinarySearchTree {

	BinaryNode root;
	Stack<String> stack;

	private final BinaryNode NULL_NODE = new BinaryNode('\0');

	public BinarySearchTree() {
		this.root = NULL_NODE;

		this.stack = new Stack<>();
	}

	public boolean isLinkedList() {
		// DONE
		if (this.root == NULL_NODE) {
			return true;
		}
		return this.root.isLinkedList();

	}

	public String toPostOrder() {
		
		return this.root.toPostOrder();
	}

	public String pathString(char item) {
		// TODO: write me. See the paper for details
		if (this.root == NULL_NODE) {
			return "";
		}
		if (!this.root.contains(item)) {
			return "";
		}
		return this.root.pathString(item);
	}

	@SuppressWarnings("boxing")
	public int evaluate() {
		// TODO: write me. See the paper for details
		this.stack = new Stack<>();
		if (this.root.left == NULL_NODE && this.root.right == NULL_NODE) {
			return Character.getNumericValue(this.root.data);
		}
		String str = this.root.toPostOrder();
		for (int i = 0; i < str.length(); i++) {
			if (!isOperator(str.charAt(i))) {
				this.stack.push(str.substring(i,i+1));
			} else {
				char operator = str.charAt(i);
				String tempA = this.stack.pop();
				String tempB = this.stack.pop();
				String after = operate(tempB, operator, tempA);
				this.stack.push(after);
			}
		}
		return Integer.parseInt(this.stack.pop());
	}

	private String operate(String tempA, char operator, String tempB) {

		int int1 = Integer.parseInt(tempA);
		int int2 = Integer.parseInt(tempB);
		if (operator == '+')
			return Integer.toString(int1 + int2);
		if (operator == '-')
			return Integer.toString(int1 - int2);
		if (operator == '/') {

			return Integer.toString(int1 / int2);
		}
		if (operator == '*')
			return Integer.toString(int1 * int2);
		return Integer.toString((int) Math.pow(int1, int2));
	}

	public boolean isOperator(char a) {
		if (a == '+' || a == '-' || a == '*' || a == '/' || a == '^')
			return true;
		return false;
	}

	// Other methods used by the unit tests are below BinaryNode.

	public class BinaryNode {
		public char data;
		public BinaryNode left;
		public BinaryNode right;

		// Helpers here:

		public boolean isLinkedList() {
			if (this.left == NULL_NODE && this.right == NULL_NODE) {
				return true;
			}
			if (this.left != NULL_NODE && this.right != NULL_NODE) {
				return false;
			}
			if (this.left != NULL_NODE && this.right == NULL_NODE) {
				return this.left.isLinkedList();
			}
			return this.right.isLinkedList();
		}

		public String toPostOrder() {
			if (this.isLeaf()) {
				return Character.toString(this.data);
			}
			if (this.left != NULL_NODE && this.right == NULL_NODE) {
				return this.left.toPostOrder() + Character.toString(this.data);
			}
			if (this.right != NULL_NODE && this.left == NULL_NODE) {
				return this.right.toPostOrder() + Character.toString(this.data);
			}
			return this.left.toPostOrder() + this.right.toPostOrder()
					+ Character.toString(this.data);

		}

		private boolean isLeaf() {
			if (this.left == NULL_NODE && this.right == NULL_NODE) {
				return true;
			}
			return false;
		}

		public String pathString(char item) {
			if (Character.valueOf(this.data).compareTo(Character.valueOf(item)) == 0) {
				return Character.toString(this.data);
			}
			if (Character.valueOf(this.data).compareTo(Character.valueOf(item)) < 0) {
				return Character.toString(this.data)
						+ this.right.pathString(item);
			}
			if (Character.valueOf(this.data).compareTo(Character.valueOf(item)) > 0) {
				return Character.toString(this.data)
						+ this.left.pathString(item);
			}
			return "";
		}

		public boolean contains(char item) {
			if (Character.valueOf(this.data).compareTo(Character.valueOf(item)) < 0) {
				if (this.right != NULL_NODE) {
					return this.right.contains(item);
				}
				return false;
			}
			if (Character.valueOf(this.data).compareTo(Character.valueOf(item)) > 0) {
				if (this.left != NULL_NODE) {
					return this.left.contains(item);
				}
				return false;
			}
			return true;
		}

		// The rest of the methods are used by the unit tests and for debugging
		public BinaryNode(char element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public BinaryNode(char element, BinaryNode left, BinaryNode right) {
			this(element);
			this.left = left;
			this.right = right;
		}

		public BinaryNode insert(char e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e < data) {
				left = left.insert(e);
			} else if (e > data) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + this.data + right.toString();
		}

		public String toIndentString(String indent) {
			if (this == NULL_NODE) {
				return indent + "NULL\n";
			}

			String myInfo = indent + String.format("%c\n", this.data);

			return myInfo + this.left.toIndentString(indent + "  ")
					+ this.right.toIndentString(indent + "  ");
		}
	}

	// The next methods are used by the unit tests
	public void insert(char e) {
		root = root.insert(e);
	}

	/**
	 * Constructs a binary tree using the given in-order and pre-order
	 * traversals.
	 * 
	 * @param preOrder
	 * @param inOrder
	 */
	public BinarySearchTree(String preOrder, String inOrder) {
		root = buildTree(preOrder, inOrder);
	}

	/**
	 * Feel free to call from tests to use to verify the shapes of your trees
	 * while debugging. Just remove the calls you are done so the output isn't
	 * cluttered.
	 * 
	 * @return A string showing a traversal of the nodes where children are
	 *         indented so that the structure of the tree can be determined.
	 * 
	 */
	public String toIndentString() {
		return root.toIndentString("");
	}

	@Override
	public String toString() {
		return root.toString();
	}

	/**
	 * Builds a binary tree with the given in-order and pre-order traversals.
	 * This is a nice algorithm. :)
	 * 
	 * @param preOrder
	 * @param inOrder
	 * 
	 * @return the root of the generated binary tree, or null for an empty tree
	 */
	BinaryNode buildTree(String preOrder, String inOrder) {
		if (inOrder.length() == 0) {
			return NULL_NODE;
		}
		char elem = preOrder.charAt(0);
		int elemsInOrderIndex = inOrder.indexOf(elem);
		if (elemsInOrderIndex < 0) {
			throw new IllegalArgumentException(
					"no valid tree for given arguments");
		}

		String leftPreOrder = preOrder.substring(1, elemsInOrderIndex + 1);
		String rightPreOrder = preOrder.substring(elemsInOrderIndex + 1);
		String leftInOrder = inOrder.substring(0, elemsInOrderIndex);
		String rightInOrder = inOrder.substring(elemsInOrderIndex + 1);
		BinaryNode left = buildTree(leftPreOrder, leftInOrder);
		BinaryNode right = buildTree(rightPreOrder, rightInOrder);
		return new BinaryNode(elem, left, right);
	}
}