package graphs;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * This class implements an undirected graph to which edges can be added
 * randomly. It's for experimenting with the theory of random graphs, developed
 * by Paul Erdos and Alfred Renyi in 1959.
 * 
 * @author TODO: put your name here
 */
public class RandomGraph {
	// You may copy code you wrote or we wrote together to do this. 
	private Vertex[] vertices;

	class Vertex {
		ArrayList<Vertex> neighbors;
		int label;

		public Vertex(int label) {
			this.label = label;
			this.neighbors = new ArrayList<Vertex>();
		}

		@Override
		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append(label + ":");
			for (Vertex v : this.neighbors) {
				s.append(" " + v.label);
			}
			return s.toString();
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
			this.vertices[v1].neighbors.add(this.vertices[v2]);
			this.vertices[v2].neighbors.add(this.vertices[v1]);
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Vertex v : this.vertices) {
			s.append(v.toString() + "\n");
		}
		return s.toString();
	}
	public Vertex findVertex(int startLabel){
		for(int i = 0; i < this.vertices.length; i++){
			if(vertices[i].label == startLabel){
				return vertices[i];
			}
		}
		return null;
	}

	public TreeSet<Integer> reachableInTwo(int startLabel) {
		// DONE: write this
		TreeSet<Integer> tree = new TreeSet<>();
		Vertex start = findVertex(startLabel);
		for(int i = 0; i <start.neighbors.size();i++){
			ArrayList<Vertex> current = start.neighbors.get(i).neighbors;
			for(int k = 0; k <current.size(); k++){
				if(!current.get(k).equals(start.neighbors.get(i))){
					tree.add(current.get(k).label);
				}
			}
		}
		if(start.neighbors.size() !=0)
			tree.add(startLabel);
		return tree;
	}
}
