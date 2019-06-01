package hw4;

public class Edge<N extends Comparable<N>, L extends Comparable<L>> implements Comparable<Edge<N,L>> {
	
	/**
		Graph represents an immutable object containing a directed edge between two nodes with a label 
		
		
		Representation Invariant: The starting node, ending node and label cannot be empty
		
		
		Abstraction Function: A node ex exists where ex.parentNode represents a node while ex.childNode represents another node and ex.label 
		represents a label for the edge
		
 	*/
	
	
	private final N parentNode;
	private final N childNode;
	private final L label;
	
	
	
	/**
		@param: node1 The start-node of the new Edge
		@param: node2 The end-node of the new Edge
		@param: lab The length of the new Edge
		@effects: creates a new edge from node1 to node2 with a label lab
	 */
	public Edge(N node1, N node2, L lab) {
		parentNode = node1;
		childNode = node2;
		label = lab;
		checkRep();
	}
	
	
	/**
		@return: this Edge object's parent Node
	 */
	public N getParent() {
		return parentNode;
	}
	
	
	/**
		@return: this Edge object's child Node
	 */
	public N getChild() {
		return childNode;
	}
	
	
	
	/**
		@return: this Edge object's label
	 */
	public L getLabel() {
		return label;
	}
	
	
	
	/**
		@throw: if the object violates the representation invariant 
	 */
	private void checkRep() throws RuntimeException {/*
		if(parentNode == null){
			throw new RuntimeException("Edge has no parentNode");
		}
		if(childNode == null){
			throw new RuntimeException("Edge has no childNode");
		}
		if(label == null){
			throw new RuntimeException("Edge has no label");
		}*/
	}
	
	
	
	
	/** 
		@param: an Edge other_edge to be compared
		@requires: other_edge is not empty
		@return: 0 if this = other_edge, a positive number if this > other_edge or a negative number if this < other_edge.
	 */
	@Override
    public int compareTo(Edge<N,L> other_edge) {
        if (parentNode.equals(other_edge.getParent()) && childNode.equals(other_edge.getChild()) ) {
            return label.compareTo(other_edge.label);
            
        } else if (parentNode.equals(other_edge.getParent()) ) {
            return childNode.compareTo(other_edge.getChild());
            
        } else {
            return parentNode.compareTo(other_edge.getParent());
        }
    }
	
}
