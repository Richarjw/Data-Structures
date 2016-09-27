package graphs;

import java.util.ArrayList;
import java.util.Stack;

/**
 * This class implements an undirected graph to which edges can be added
 * randomly. It's for experimenting with the theory of random graphs, developed
 * by Paul Erdos and Alfred Renyi in 1959.
 * 
 * @author TODO: put your name here
 */
public class RandomGraph {
	// TODO: add any necessary fields and classes for your chosen representation
	Vertex[] vertices;

	class Vertex {
		ArrayList<Vertex> neighbors;
		int label;

		public Vertex(int label) {
			this.label = label;
			this.neighbors = new ArrayList<>();

		}

		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(this.label);
			sb.append(": ");
			for (Vertex v : this.neighbors) {
				sb.append(v.label);
				sb.append(" ");
			}
			return sb.toString();
		}
	}

	/**
	 * Constructs a new graph of the given size with no edges.
	 * 
	 * @param size
	 */
	public RandomGraph(int size) {
		this(size, new int[][] {});
	}

	/**
	 * Constructs a new graph of the given size with the given edges
	 * 
	 * @param size
	 * @param edges
	 *            each element is a pair giving the indices of the two nodes to
	 *            be connected
	 */
	public RandomGraph(int size, int[][] edges) {
		this.vertices = new Vertex[size];
		for (int i = 0; i < size; i++) {
			this.vertices[i] = new Vertex(i);
		}
		for (int[] edge : edges) {
			int v1 = edge[0];
			int v2 = edge[1];
			vertices[v1].neighbors.add(vertices[v2]);
			vertices[v2].neighbors.add(vertices[v1]);
		}
	}

	/**
	 * @return the size of the largest connected component of this graph
	 */
	public int largestConnectedComponentSize() {
		// TODO: implement this method
		int max = -1;
		boolean[] visited = new boolean[vertices.length];
		for (Vertex v : vertices) {
			int compSize = componentSize(v, visited);
			if (compSize > max) {
				max = compSize;
			}
		}
		return max;
	}

	private int componentSize(Vertex v, boolean[] visited) {
		int size = 0;
		Stack<Vertex> stack = new Stack<>();
		stack.push(v);
		while (!stack.isEmpty()) {
			Vertex current = stack.pop();
			if (!visited[v.label]) {
				size++;
				visited[v.label] = true;
			}
			for (Vertex neighbor : current.neighbors) {
				if(!visited[neighbor.label])
					stack.push(neighbor);
				
			}
		}
		return size;
	}

	/**
	 * Adds a new edge, chosen uniformly from the set of missing edges.
	 * 
	 * @throws IllegalStateException
	 *             if this.isComplete()
	 */
	public void addRandomEdge() throws IllegalStateException {
		// TODO: implement this method
	}

	/**
	 * Returns whether this graph is connected, that is, whether there is a path
	 * from any node in the graph to any other node.
	 * 
	 * @return true iff this graph is connected
	 */
	public boolean isConnected() {
		// TODO: implement this method
		return false;
	}

	/**
	 * Returns whether this graph is complete, that is, any two distinct
	 * vertices are neighbors of each other.
	 * 
	 * @return true iff this graph is complete
	 */
	public boolean isComplete() {
		// TODO: implement this method
		return false;
	}

	@Override
	public String toString() {
		/*
		 * TODO: Implement this method by replacing the super call with code to
		 * create a "reasonable" string representation of the Graph. Any
		 * representation that helps you debug the other methods is fine.
		 */
		StringBuilder sb = new StringBuilder();
		for (Vertex v : this.vertices) {
			sb.append(v.toString());
			sb.append("\n");
		}
		return super.toString();
	}
}
