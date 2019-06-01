package hw7;

import java.util.LinkedList;

import hw4.Edge;


public class Path implements Comparable<Path>{
	private Building Start;
	private Building End;
	private Double Cost;
	private LinkedList<Edge<Building,Double>> edges;

	/**
		@param: A Building start to designate the first point in the Path
		@param: A Building end to designate the last point in the Path
		@effects: Constructs a new Path following specifications
	 */
	public Path(Building start, Building end){
		Start = start;
		End = end; 
		edges = new LinkedList<Edge<Building,Double>>();
		Cost = 0.0;
	}
	
	/**
		@param: A Path other to be copied
		@effects: Copies an existing Path
	 */
	public Path(Path other){
		Start = other.Start;
		End = other.End;
		edges = new LinkedList<Edge<Building,Double>>(other.edges);
		Cost = other.Cost; 
	}
	
	/**
		@return: this Path object's starting position
	 */
	public Building start() {
		return Start;
	}
	
	/**
		@return: this Path object's ending position
	 */
	public Building end() {
		return End;
	}
	
	/**
		@return: this Path object's total cost
	 */
	public Double cost() {
		return Cost;
	}
	
	/**
		@return: this Path object's list of Edges
	 */
	public LinkedList<Edge<Building,Double>> returnEdges() {
		return new LinkedList<Edge<Building,Double>>(edges);
	}
	
	/**
		@param: an Edge edge to be added to the path
		@modifies: edges, End and Cost
		@effects: adds the edge to the list of edges, sets the child of the edge as the last point in the Path and adds the cost of the
					edge to the total
	 */
	public void addEdge(Edge<Building, Double> edge) {
		edges.add(edge);
		End = edge.getChild();
		Cost += edge.getLabel();
	}

	/** 
		@param: a Path other to be compared
		@requires: other is not empty
		@return: 0 if this == other, a positive number if this > other or a negative number if this < other.
	 */
	@Override
	public int compareTo(Path other) { 
		return Cost.compareTo(other.Cost);
	}
}
