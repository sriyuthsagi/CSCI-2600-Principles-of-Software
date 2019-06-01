package hw4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Graph<N extends Comparable<N>, L extends Comparable<L>> {
	
	/**
		Graph represents a mutable graph containing a set of Nodes (in the form of String objects) and Edges (in the form of a seperate class)
		
		
		Representation Invariant: All Nodes in the graph are unique and Edges must connect Nodes inside the graph
		
		
		Abstraction Function: A Graph ex exists where ex.nodes represents a collection of Nodes while ex.edges represents a collection of Edges.
		
 	*/
	
	
	private HashMap<N, HashSet<Edge<N,L>>> graph;
	
	
	
	/**
		@effects: Constructs a new empty Graph
	 */
	public Graph(){

		graph = new HashMap<N, HashSet<Edge<N,L>>>();
		checkRep();
	}
	
	
	/**
		@param: A new node new_node to be added
		@modifies: nodes
		@effects: adds the new_node to nodes if new_node is not already present
		@throws: if new_node is null or is already present in the graph
	 */
	public void addNode(N new_node) {
		
		if (graph.keySet().contains(new_node)) {
			throw new RuntimeException("addNode");
		}
		
		graph.put(new_node, new HashSet<Edge<N,L>>());
		checkRep();
	}
	
	
	/**
		@param: A new edge new_edge to be added
		@modifies: edges
		@effects: adds the new_edge to edges
		@throws: if either of the two nodes stored in the new_edge isn't in the graph
	 */
	public void addEdge(Edge<N,L> new_edge) {
		
		if (!(graph.keySet().contains(new_edge.getParent()))) {
			throw new RuntimeException("addEdge1");
		}
		if (!(graph.keySet().contains(new_edge.getChild()))) {
			throw new RuntimeException("addEdge2");
		}
		
		HashSet<Edge<N,L>> temp = graph.get(new_edge.getParent());
		temp.add(new_edge);
		checkRep();
	}
	
	
	
	/**
		@return: this Graph object's Nodes
	 */
	public Set<N> getNodes() {
		return graph.keySet();
	}
	
	
	
	/**
		@return: this Graph object's Edges
	 */
	public HashSet<Edge<N,L>> getEdges() {
		HashSet<Edge<N,L>> temp = new HashSet<Edge<N,L>>();
		
		for (N key : graph.keySet()) {
			temp.addAll(graph.get(key));
		}
		
		return temp;
	}
	
	
	
	/**
		@return: this Graph object's number of Nodes
	 */
	public int numNodes() {
		return graph.size();
	}
	
	
	
	
	/**
		@return: this Graph object's number of Edges
	 */
	public int numEdges() {
		int temp = 0;
		
		for (N key : graph.keySet()) {
			temp += graph.get(key).size();
		}
		
		return temp;
	}
	
	
	
	/**
		@modifies: nodes and edges
		@effects: clears nodes and edges
	 */
	public void clear() {
		graph.clear();
		checkRep();
	}
	
	
	
	/**
		@param: a node n to check for in nodes
		@return: true if node n is on the graph, false otherwise
	 */
	public boolean contains(N n){
		return graph.keySet().contains(n);
	}
	
	
	
	/**
		@param: a String node that represents a parent node for the edges
		@returns: sorts edges that start from the node and are sorted using a TreeSet
	 */
	public TreeSet<Edge<N,L>> listChildrenSorted(N node) {
		return new TreeSet<Edge<N,L>>(graph.get(node));
	}
	
	
	
	
	/**
		@throw: if the object violates the representation invariant 
	 */
	private void checkRep() throws RuntimeException {
		
		// HashSet will not have duplicates so all Nodes are unique
		/*
		for (Edge i: edges) {
			if (!(nodes.contains(i.getParent()))) {
				throw new RuntimeException("checkRep1");
			}
			if (!(nodes.contains(i.getChild()))) {
				throw new RuntimeException("checkRep2");
			}
		}*/
	}
	
	
}
