package hw7;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

import hw4.Edge;
import hw4.Graph;

public class Campus {
	private Graph<Building, Double> graph;
	private Map<String, Building> ID_Building;
	private Map<String, String> Name_ID;
	
	/**
		@effects: Constructs a new empty Campus
	 */
	public Campus(){
		graph = new Graph<Building, Double>();
		ID_Building = new HashMap<String, Building>();
		Name_ID = new HashMap<String, String>();
	}
	
	
	/**
		@modifies: graph, ID_Building and Name_ID
		@effects: clears the graph, ID_Building and Name_ID
	 */
	public void clear(){
		graph.clear();
		ID_Building.clear();
		Name_ID.clear();
	}
	
	
	/** 
		@param: a String node_file containing the name of the file stored the node data
		@param: a String edge_file containing the name of the file stored the edge data
		modifies: graph, ID_Building and Name_ID
		@effects: creates a new Campus from the data in the files
		@throws: IOException through CampusParser
	 */
	public void createNewCampus(String node_file, String edge_file) {
		clear();
		
		try{
			Set<Building> buildings = new TreeSet<Building>();
			CampusParser.readNodeData(node_file, buildings);
			
			for (Building b : buildings) {
				graph.addNode(b);
				ID_Building.put(b.id(), b);
				Name_ID.put(b.name(), b.id());
			}
			
			List<List<String>> building_edges = new LinkedList<List<String>>();
			CampusParser.readEdgeData(edge_file, building_edges);

			for (List<String> single : building_edges) {
				Building node1 = ID_Building.get(single.get(0));
				Building node2 = ID_Building.get(single.get(1));
				
				Double x1 = node1.x();
				Double x2 = node2.x();
				Double y1 = node1.y();
				Double y2 = node2.y();
				Double label = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
				graph.addEdge(new Edge<Building, Double>(node1, node2, label));
				graph.addEdge(new Edge<Building, Double>(node2, node1, label));
			}
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
		@return: a String listing all the buildings in the Campus in order
	 */
	public String listBuildings() {
		
		String list = "";
		
		TreeSet<Building> buildings = new TreeSet<Building>(graph.getNodes());
		
		for (Building b : buildings) {
			if (!b.name().equals("")) {
				list += b.name() + "," + b.id() + "\n";
			}
		}
		
		return list;
		
	}
	
	
	/**
		@param: a starting node node1 for the path
		@param: a destination node node2 for the path
		@return: a String listing all of the nodes and directions traveled visited along the shortest path
	 */
	public String findPath(String node1, String node2) {
		
		Building start;
		try {
			Integer.parseInt(node1);
			start = ID_Building.get(node1);
			
		} catch (Exception e) {
			start = ID_Building.get(Name_ID.get(node1));
		}
		
		Building end;
		try {
			Integer.parseInt(node2);
			end = ID_Building.get(node2);
			
		} catch (Exception e) {
			end = ID_Building.get(Name_ID.get(node2));
		}
		
		String output = "";
		
		if (start == null && end == null && node1.equals(node2)) {
			return output + "Unknown building: [" + node1 + "]\n";
			
		} else if (start == null && end == null) {
			output += "Unknown building: [" + node1 + "]\n";
			return output + "Unknown building: [" + node2 + "]\n";
			
		} else if (start == null && end != null) {
			return output + "Unknown building: [" + node1 + "]\n";
			
		} else if (start != null && end == null) {
			return output + "Unknown building: [" + node2 + "]\n";
		}
		
		if (start.name().equals("") && end.name().equals("") && node1.equals(node2)) {
			return output + "Unknown building: [" + node1 + "]\n";
			
		} else if (start.name().equals("") && end.name().equals("")) {
			output += "Unknown building: [" + node1 + "]\n";
			return output + "Unknown building: [" + node2 + "]\n";
			
		} else if (start.name().equals("") && !end.name().equals("")) {
			return output + "Unknown building: [" + node1 + "]\n";
			
		} else if (!start.name().equals("") && end.name().equals("")) {
			return output + "Unknown building: [" + node2 + "]\n";
		}
		
		PriorityQueue<Path> active = new PriorityQueue<Path>();
		Map<Building, Path> finished = new HashMap<Building, Path>();
		
		Path minPath = new Path(start, start);
		active.add(minPath);
		
		while (active.size() != 0) {
			minPath = active.poll();
			Building minDest = minPath.end();
			
			if (minDest.compareTo(end) == 0) {
				output += "Path from " + start.name() + " to " + end.name() + ":\n";
				
				for (Edge<Building, Double> edge : minPath.returnEdges()) {
					if (edge.getChild().name().equals("")) {
						output += "\tWalk " + getDirection(edge) + " to (Intersection " + edge.getChild().id() + ")\n";
					} else {
						output += "\tWalk " + getDirection(edge) + " to (" + edge.getChild().name() + ")\n";
					}
				}
				
				return output + String.format("Total distance: %.3f pixel units.\n", minPath.cost());
				
			} else if (finished.containsKey(minDest)) {
				continue;
			}
			
			for (Edge<Building, Double> edge : graph.listChildrenSorted(minDest)) {
				
				if( finished.containsKey(edge.getParent()) || finished.containsKey(edge.getChild()) ){ continue; }
				Path next_path = new Path(minPath);
				next_path.addEdge(edge);
				active.add(next_path);
			}
			
			finished.put(minDest, minPath);
			
		}
		
		return output + "There is no path from " + start.name() + " to " + end.name() + ".\n";
	}
	
	
	/**
		@param: an Edge edge which is directed
		@return: a String stating the direction moved by the edge
	 */
	public String getDirection(Edge<Building, Double> edge) {
		Building b1 = edge.getParent();
		Building b2 = edge.getChild();
		
		Double x1 = b1.x();
		Double x2 = b2.x();
		Double y1 = b1.y();
		Double y2 = b2.y();
		Double angle = Math.toDegrees(Math.atan2(x2 - x1, y2 - y1));
		
		if (157.5 <= angle || angle < -157.5){
			return "North";
		} else if (-157.5 <= angle && angle < -112.5) {
			return "NorthWest";
		} else if (-112.5 <= angle && angle < -67.5) {
			return "West";
		} else if (-67.5 <= angle && angle < -22.5) {
			return "SouthWest";
		} else if (-22.5 <= angle && angle < 22.5) {
			return "South";
		} else if (22.5 <= angle && angle < 67.5) {
			return "SouthEast"; 
		} else if (67.5 <= angle && angle < 112.5) {
			return "East";
		} else {
			return "NorthEast";
		}
		
		
	}
	
	
	/*
	public static void main(String[] argv) {
		Campus c = new Campus();
		c.createNewCampus("data/RPI_map_data_Nodes.csv", "data/RPI_map_data_Edges.csv");
		
		System.out.print(c.findPath("108", "108"));
		
		//int count = 0;
		//for (Edge<Building, Double> edge : c.graph.getEdges()) {
		//	System.out.println(edge.getParent().name() + " " + edge.getChild().name() + " " + edge.getLabel());
		//	count++;
		//}
		//System.out.println(count);
		//System.out.println(c.graph.numNodes());
		//System.out.println(c.graph.numEdges());
		
	}*/

}
