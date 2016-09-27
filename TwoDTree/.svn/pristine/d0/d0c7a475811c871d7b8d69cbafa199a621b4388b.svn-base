package twodtree;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Stack;

/**
 * A 2D Tree implementation.
 * 
 * @author Matt Boutell and DONE: Jack Richard
 */
public class TwoDTree {
	/*
	 * TODO: Directions: Implement the methods below. See the specifications for
	 * details.
	 */
	private Node root;
	private Node parent;
	private Point2 nearestFound = null;
	public final Node NULL_NODE = new Node();

	/** For drawing the plane. */
	public static final double DOT_RADIUS = 5;
	private int planePanelWidth;
	private int planePanelHeight;

	// For drawing the tree
	private static final int MARGIN = 30;
	private static final double RADIUS_SCALE_FACTOR = 0.75;
	private static final double FONT_SCALE_FACTOR = 1.5;
	private static final double DIRECTION_TYPE_SCALE_FACTOR = 1.1;
	private int treePanelWidth;
	private int treePanelHeight;
	// The number of pixels horizontally and vertically between nodes.
	private int xStep, yStep;
	private double radius;
	// font to use for labeling nodes
	private Font font;
	private int fontSize;

	/**
	 * Constructs an empty tree.
	 * 
	 */
	public TwoDTree() {
		this(0, 0, 0, 0); // When called within params, it won't be visualized
	}

	/**
	 * Constructs an empty tree.
	 * 
	 */
	public TwoDTree(int planePanelWidth, int planePanelHeight,
			int treePanelWidth, int treePanelHeight) {
		root = NULL_NODE;
		parent = NULL_NODE;
		this.planePanelWidth = planePanelWidth;
		this.planePanelHeight = planePanelHeight;
		this.treePanelWidth = treePanelWidth;
		this.treePanelHeight = treePanelHeight;
	}

	/**
	 * Inserts the given point into the tree
	 * 
	 * @param p
	 *            A point to insert.
	 */
	public void insert(Point2 p, String label) {
		int count = 0;
		if (this.root == NULL_NODE) {
			this.root = new Node(p);
			this.root.label = label;
			this.root.dir = Direction.X;
			this.root.depth = count;

		} else {
			this.root.insert(p, label, count);
		}

	}

	/**
	 * Checks to see if the given query point is in this tree.
	 * 
	 * @param q
	 *            Query point.
	 * @return True if and only if the query point is in this tree.
	 * 
	 */
	public boolean contains(Point2 q) {
		// DONE: write this.
		int direction = 0;
		if (this.root == NULL_NODE) {
			return false;
		}
		return this.root.contains(q, direction);
	}

	/**
	 * Finds the closest point in the tree to the query point.
	 * 
	 * @param q
	 *            The query point
	 * @throws IllegalStateException. If
	 *             the tree is empty.
	 * @return The closest point
	 */
	public Point2 nearestNeighbor(Point2 q) throws IllegalStateException {
		// TODO: write this.

		if (this.root == NULL_NODE)
			throw new IllegalStateException();
		Stack<Point2> nodes = new Stack<>();
		this.root.pushStack(nodes);
		Point2 closest = nodes.pop();
		while (!nodes.isEmpty()) {
			Point2 check = nodes.pop();
			if (closest.distanceTo(q) > check.distanceTo(q)) {
				closest = check;
			}
		}
		return closest;

	}

	public void drawTree(Graphics2D g) {
		int nodeCountPlusOne = root.setInOrderNumbers(1);
		this.xStep = (this.treePanelWidth - 2 * MARGIN) / nodeCountPlusOne;
		this.yStep = (this.treePanelHeight - 2 * MARGIN) / (height() + 2);
		this.radius = ((xStep < yStep) ? xStep : yStep) * RADIUS_SCALE_FACTOR;
		this.fontSize = (int) (radius * FONT_SCALE_FACTOR * 96 / 72);
		this.font = new Font("Monospaced", Font.BOLD, fontSize);
		root.drawTree(g, -1, -1);
	}

	public void clear() {
		root = NULL_NODE;
		nearestFound = null;
	}

	@Override
	public String toString() {
		if (root == NULL_NODE) {
			return "()";
		}
		StringBuilder sb = new StringBuilder();
		root.buildString(sb);
		return sb.toString();
	}

	public void draw(Graphics2D g2, double minX, double maxX, double minY,
			double maxY) {
		root.drawInPlane(g2, minX, maxY, minY, maxY);

		if (nearestFound != null) {
			Ellipse2D.Double nodeDot = new Ellipse2D.Double(
					screenX(nearestFound.x) - DOT_RADIUS,
					screenY(nearestFound.y) - DOT_RADIUS, DOT_RADIUS * 2,
					DOT_RADIUS * 2);
			g2.setColor(Color.RED);
			g2.fill(nodeDot);
		}
	}

	private int screenX(double x) {
		return (int) (x * planePanelWidth);
	}

	private int screenY(double y) {
		return (int) (y * planePanelHeight);
	}

	private int height() {
		return root.height();
	}

	enum Direction {
		X, Y
	}

	public class Node {
		// The two data fields: a label and a point
		public String label;
		public Point2 p;

		// Children
		Node topLeft;
		Node bottomRight;
		public Direction dir;
		// Each node knows the bounds it is part of. Helpful when searching.
		public RectHV bounds;

		// For tree drawing
		private int depth;
		private int inOrderNumber;
		public Stack<Point2> possibles = new Stack<>();

		private Node parent;

		// This one is used by the NULL_NODE.
		public Node() {
			// do nothing
		}

		public void pushStack(Stack<Point2> nodes) {
			nodes.push(this.p);
			if (this.topLeft != NULL_NODE) {
				this.topLeft.pushStack(nodes);
			}
			if (this.bottomRight != NULL_NODE) {
				this.bottomRight.pushStack(nodes);
			}

		}

		public void insert(Point2 p2, String label2, int count) {
			if (count % 2 == 0) { // checks left/right value of point (x)
				if (this.p.x < p2.x) {
					if (this.bottomRight == NULL_NODE) {
						this.bottomRight = new Node(p2);
						this.bottomRight.parent = this;
						this.bottomRight.label = label2;
						this.bottomRight.dir = Direction.X;
						this.depth = count;
						return;
					}
					this.bottomRight.insert(p2, label2, count + 1);
				} else {
					if (this.topLeft == NULL_NODE) {
						this.topLeft = new Node(p2);
						this.topLeft.label = label2;
						this.topLeft.parent = this;
						this.topLeft.dir = Direction.X;
						this.depth = count;
						return;
					}
					this.topLeft.insert(p2, label2, count + 1);
				}
			} else {
				if (this.p.y < p2.y) { // Checks top/bottom value of point (y)
					if (this.bottomRight == NULL_NODE) {
						this.bottomRight = new Node(p2);
						this.bottomRight.label = label2;
						this.bottomRight.parent = this;
						this.bottomRight.dir = Direction.Y;
						this.depth = count;
						return;
					}
					this.bottomRight.insert(p2, label2, count + 1);
				} else {
					if (this.topLeft == NULL_NODE) {
						this.topLeft = new Node(p2);
						this.topLeft.label = label2;
						this.topLeft.parent = this;
						this.topLeft.dir = Direction.Y;
						this.depth = count;
						return;
					}
					this.topLeft.insert(p2, label2, count + 1);
				}
			}

		}

		public boolean contains(Point2 q, int direction) {
			if (this.p.equals(q))
				return true;
			if (direction % 2 == 0) {
				if (this.p.x < q.x) {
					if (this.bottomRight == NULL_NODE)
						return false;
					return this.bottomRight.contains(q, direction + 1);
				}
				if (this.topLeft == NULL_NODE)
					return false;
				return this.topLeft.contains(q, direction + 1);
			}
			if (this.p.y < q.y) {
				if (this.bottomRight == NULL_NODE)
					return false;
				return this.bottomRight.contains(q, direction + 1);
			}
			if (this.topLeft == NULL_NODE)
				return false;
			return this.topLeft.contains(q, direction + 1);
		}

		public Node(Point2 p) {
			if (p == null) {
				return;
			}
			this.p = new Point2(p);
			this.topLeft = NULL_NODE;
			this.bottomRight = NULL_NODE;
			this.bounds = null;
			this.parent = NULL_NODE;
		}

		// You will probably use this when writing insert()
		public Node(double x, double y, String label, Direction dir,
				RectHV bounds, int depth) {
			this.p = new Point2(x, y);
			this.label = label;
			this.dir = dir;
			this.topLeft = NULL_NODE;
			this.bottomRight = NULL_NODE;
			this.bounds = bounds;
			this.depth = depth;
		}

		private void buildString(StringBuilder sb) {
			if (this == NULL_NODE) {
				return;
			}
			if (topLeft != NULL_NODE) {
				sb.append("(");
				topLeft.buildString(sb);
				sb.append(")");
			}
			sb.append(String.format("%s(%4.2f,%4.2f)", label, p.x, p.y));
			if (bottomRight != NULL_NODE) {
				sb.append("(");
				bottomRight.buildString(sb);
				sb.append(")");
			}
		}

		private void drawInPlane(Graphics2D g2, double minX, double maxX,
				double minY, double maxY) {
			if (this == NULL_NODE) {
				return;
			}

			// Dot
			Ellipse2D.Double nodeDot = new Ellipse2D.Double(screenX(p.x)
					- DOT_RADIUS, screenY(p.y) - DOT_RADIUS, DOT_RADIUS * 2,
					DOT_RADIUS * 2);
			g2.fill(nodeDot);

			// Label
			int xOffset = this.dir == Direction.X ? 10 : 0;
			int yOffset = this.dir == Direction.X ? 0 : 20;
			g2.drawString(label, (int) screenX(p.x) + xOffset,
					(int) screenY(p.y) + yOffset);

			if (dir == Direction.X) {
				// Draw vertical line from (x, minY) to (x, maxY)
				Line2D.Double line = new Line2D.Double(new Point2D.Double(
						screenX(p.x), screenY(minY)), new Point2D.Double(
						screenX(p.x), screenY(maxY)));
				g2.draw(line);
				topLeft.drawInPlane(g2, minX, p.x, minY, maxY);
				bottomRight.drawInPlane(g2, p.x, maxX, minY, maxY);
			} else {
				// VERTICAL separation, so draw horizontal line
				Line2D.Double line = new Line2D.Double(new Point2D.Double(
						screenX(minX), screenY(p.y)), new Point2D.Double(
						screenX(maxX), screenY(p.y)));
				g2.draw(line);
				topLeft.drawInPlane(g2, minX, maxX, minY, p.y);
				bottomRight.drawInPlane(g2, minX, maxX, p.y, maxY);
			}
		}

		private void drawTree(Graphics2D g, double parentX, double parentY) {
			if (this == NULL_NODE) {
				return;
			}

			double centerX = this.inOrderNumber * xStep + MARGIN;
			double centerY = (this.depth + 1) * yStep + MARGIN;

			if (parentX > 0 && parentY > 0) {
				// Draws line
				g.setColor(Color.BLACK);
				double deltaX = parentX - centerX;
				double deltaY = parentY - centerY;
				double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
				double xOffset = deltaX * radius / distance;
				double yOffset = deltaY * radius / distance;
				Point2D.Double selfEdge = new Point2D.Double(centerX + xOffset,
						centerY + yOffset);
				Point2D.Double parentEdge = new Point2D.Double(parentX
						- xOffset, parentY - yOffset);
				g.draw(new Line2D.Double(selfEdge, parentEdge));
			}

			// Draws the circle
			Ellipse2D.Double circ = new Ellipse2D.Double(centerX - radius,
					centerY - radius, 2 * radius, 2 * radius);
			g.setColor(Color.WHITE);
			g.fill(circ);
			g.setColor(Color.BLACK);
			g.draw(circ);

			// Labels the circle
			g.setFont(font);
			// coefficients on radius determined experimentally
			g.drawString(label.toString(), (int) (centerX - 0.5 * radius),
					(int) (centerY + 0.6 * radius));

			// Direction
			Point2D.Double first = new Point2D.Double(centerX, centerY);
			Point2D.Double second = new Point2D.Double(centerX, centerY);
			if (dir == Direction.X) {
				first.y -= radius * DIRECTION_TYPE_SCALE_FACTOR;
				second.y += radius * DIRECTION_TYPE_SCALE_FACTOR;
			} else {
				first.x -= radius * DIRECTION_TYPE_SCALE_FACTOR;
				second.x += radius * DIRECTION_TYPE_SCALE_FACTOR;
			}
			g.setStroke(new BasicStroke(2));
			g.draw(new Line2D.Double(first, second));

			// Draw children
			topLeft.drawTree(g, centerX, centerY);
			bottomRight.drawTree(g, centerX, centerY);
		}

		// The next two are helpers for the drawTree.
		private int height() {
			if (this == NULL_NODE) {
				return -1;
			}
			return Math.max(topLeft.height(), bottomRight.height()) + 1;
		}

		private int setInOrderNumbers(int nextNumber) {
			if (this == NULL_NODE) {
				return nextNumber;
			}
			nextNumber = topLeft.setInOrderNumbers(nextNumber);
			this.inOrderNumber = nextNumber++;
			return bottomRight.setInOrderNumbers(nextNumber);
		}
	}
}