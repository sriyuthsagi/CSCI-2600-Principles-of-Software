package hw4;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class GraphWrapper {
	
	private Graph<String, String> graph;
	
	
	/**
		This is the constructor of GraphWrapper. It initializes the instance field with a new empty instance of your Graph ADT.
	 */
	public GraphWrapper() {
		graph = new Graph<String, String>();
	}
	
	/**
		Adds a node represented by the string nodeData to your graph. If an identical node already exists in the graph, the 
		output of addNode is not defined, that is, it is left at your discretion.
	 */
	public void addNode(String nodeData) {
		graph.addNode(nodeData);
	}
	
	
	
	
	
	/**
		Creates an edge from parentNode to childNode with label edgeLabel in your graph. If either of the nodes does not exist in 
		the graph, the output of this command is not defined. If an identical edge (same parent, child, and label) already exists, 
		the output of this command is not defined either, as it is left at your discretion whether to allow identical edges in your 
		implementation.
	 */
	public void addEdge(String parentNode, String childNode, String edgeLabel) {
		Edge<String, String> edge = new Edge<String, String>(parentNode, childNode, edgeLabel);
		graph.addEdge(edge);
	}
	
	
	
	
	
	/**
		This operation has no effect on your graph. It returns an iterator which returns the nodes in lexicographical (alphabetical) order.
	 */
	public Iterator<String> listNodes() {
		return Collections.unmodifiableSet(graph.getNodes()).iterator();
	}
	
	
	
	
	
	
	/**
		This operation has no effect on your graph. It returns iterator which returns the list of childNode(edgeLabel) in lexicographical 
		(alphabetical) order by node name and secondarily by edge label. childNode(edgeLabel) means there is an edge with label edgeLabel 
		from parentNode to childNode. If there are multiple edges from parentNode to some childNode, there should be separate entry for 
		each edge. If there is a reflexive edge, parentNode(edgeLabel) should be in the list.
	 */
	public Iterator<String> listChildren(String parentNode) {
		TreeSet<String> children = new TreeSet<String>();
		Edge<String, String> temp_edge;
		Iterator<Edge<String, String>> it = Collections.unmodifiableSet(graph.getEdges()).iterator();
		
		while (it.hasNext()) {
			temp_edge = it.next();
			if(parentNode.equals(temp_edge.getParent())) {
				children.add(temp_edge.getChild() + "(" + temp_edge.getLabel() + ")");
			}
		}
		
		return children.iterator();
		
	}
}