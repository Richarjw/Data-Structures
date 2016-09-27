package buildtree;

/**
 * 
 * @author Matt Boutell and <<<Jack Richard>>>.
 * @param <T>
 */

public class BinaryTree {

	private BinaryNode root;

	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinaryTree() {
		root = NULL_NODE;
	}

	/**
	 * Constructs a tree (any tree of characters, not just a BST) with the given
	 * values and number of children, given in a pre-order traversal order. See
	 * the HW spec for more details.
	 * 
	 * @param chars
	 *            One char per node.
	 * @param children
	 *            L,R, 2, or 0.
	 */
	public BinaryTree(String chars, String children) {
		// TODO: Implement this constructor. You may not add any other fields to
		// the BinaryTree class, but you may add local variables and helper
		// methods if you like.
		if (!chars.isEmpty()) {
			int[] arg = new int[1];
			arg[0] = 0; //I use a single value array because it is easier to keep track of rather than an integer
			this.root = new BinaryNode(null);
			this.root.makeTree(chars, children, arg);
		} else {
			this.root = NULL_NODE;
		}
	}

	/**
	 * In-order traversal of the characters
	 */
	@Override
	public String toString() {
		return root.toString();
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

		public Character data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Character element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		@SuppressWarnings("boxing")
		public void makeTree(String chars, String children, int[] arg) {
			// TODO Auto-generated method stub.
			if(this == NULL_NODE){//deals with NULL pointers
				return;
			}
			if (children.charAt(arg[0]) == 'L') {//The next 3 if statements add the right amount of children
				this.left = new BinaryNode(null);
			} else if (children.charAt(arg[0]) == 'R') {//depending on what the character is in children
				this.right = new BinaryNode(null);
			} else if (children.charAt(arg[0]) == '2') {
				this.left = new BinaryNode(null);
				this.right = new BinaryNode(null);
			}
			this.data = chars.charAt(arg[0]);//adds data
			arg[0]++;//updates index value to move to next spot.
			this.left.makeTree(chars, children, arg);
			this.right.makeTree(chars, children, arg);
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + data + right.toString();
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "(" + left.toStructuredString() + this.data
					+ right.toStructuredString() + ")";
		}

	}
}