package hw6;

import hw4.Graph;
import hw5.MarvelParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import hw4.Edge;

public class MarvelPaths2 {
	
	/**
	MarvelPaths represents a mutable object containing a Graph object and a Map object that stores books and characters.
	
	
	Representation Invariant: All edge labels can be found in the keys of books
	
	
	Abstraction Function: A Graph graph exists where graph.nodes represents a collection of characters while graph.edges represents a 
	collection of Edges that represent characters being in the same book.
	
	*/
	
	
	private Graph<String, String> graph;
	private HashMap<String, Set<String>> books;
	private Graph<String, Double> weight_graph;
	
	
	/**
		@effects: Constructs a new empty MarvelPaths
	 */
	public MarvelPaths2() {
		graph = new Graph<String, String>();
		books = new HashMap<String, Set<String>>();
		weight_graph = new Graph<String, Double>();
	}
	
	
	/**
		@modifies: graph and books
		@effects: clears the graph and books
	 */
	public void clear() {
		graph.clear();
		books.clear();
		weight_graph.clear();
	}
	
	
	
	/** 
		@param: a String file containing the name of the file stored in data
		@effects: creates a new graph from the data in the file
		@throws: IOException through MarvelParser
	 */
	public void createNewGraph(String filename) {
		clear();
		
		try {
			HashSet<String> characters = new HashSet<String>();
			HashMap<String, HashMap<String, Double>> charsConnect = new HashMap<String, HashMap<String, Double>>();
			MarvelParser.readData(filename, books, characters);
			
			for (String ch : characters) {
				graph.addNode(ch);
				weight_graph.addNode(ch);
				charsConnect.put(ch, new HashMap<String, Double>());
			}
			
			for (String book : books.keySet()) {
				
				Set<String> single_book = books.get(book);
				
				for (String temp1 : single_book) {
					for (String temp2 : single_book) {
						
						if (!temp1.equals(temp2)) {
							graph.addEdge(new Edge<String, String>(temp1, temp2, book));
							
							HashMap<String, Double> temp = charsConnect.get(temp1);
							
							Double w = temp.get(temp2);
							if(w == null) {
								w = 0.000;
							}
							temp.put(temp2, w + 1);
						}
					}
				}
			}
			
			for (String ch1 : charsConnect.keySet()) {
				for (String ch2 : charsConnect.get(ch1).keySet()) {
					weight_graph.addEdge(new Edge<String, Double>(ch1, ch2, 1/charsConnect.get(ch1).get(ch2)));
				}
				
			}
			
			
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
		@param: a starting node node1 for the path
		@param: a destination node node2 for the path
		@return: a string listing all of the nodes and edges visited along the shortest path as well as the total weight of the path
	 */
	public String findPath(String node1, String node2){
		
		if (!graph.contains(node1) && !graph.contains(node2) && !node1.equals(node2)) {
			return "unknown character " + node1 + "\nunknown character " + node2 + "\n";
			
		} else if (!graph.contains(node1)) {
			return "unknown character " + node1 + "\n";
			
		} else if (!graph.contains(node2)) {
			return "unknown character " + node2 + "\n";
		}
		
		String path = "path from " + node1 + " to " + node2 + ":\n";
		
		
		PriorityQueue<Path> active = new PriorityQueue<Path>();
		Map<String, Path> finished = new HashMap<String, Path>();
		
		Path minPath = new Path(node1, node1);
		active.add(minPath);
		
		while (active.size() != 0) {
			minPath = active.poll();
			String minDest = minPath.end;
			
			if (minDest.equals(node2)) {
				for (Edge<String, Double> edge : minPath.edges) {
					
					path += edge.getParent() + " to " + edge.getChild() + String.format(" with weight %.3f", edge.getLabel()) + "\n";
				}
				
				return path + String.format("total cost: %.3f\n", minPath.weight);
				
			} else if (finished.containsKey(minDest)) {
				continue;
			}
			
			for (Edge<String, Double> edge : weight_graph.listChildrenSorted(minDest)) {
				
				Path next_path = new Path(minPath);
				next_path.edges.add(edge);
				next_path.end = edge.getChild();
				next_path.weight += edge.getLabel();
				active.add(next_path);
			}
			
			finished.put(minDest, minPath);
			
		}
		
		return path + "no path found\n";
	}
	
	

	public class Path implements Comparable<Path>{
		public String start;
		public String end;
		public Double weight;
		public LinkedList<Edge<String,Double>> edges;

		public Path(String s, String e){
			start = new String(s);
			end = new String(e); 
			edges = new LinkedList<Edge<String,Double>>();
			weight = 0.00;
		}
		public Path(Path other){
			start = new String(other.start);
			end = new String(other.end);
			edges = new LinkedList<Edge<String,Double>>(other.edges);
			weight = other.weight; 
		}

		@Override
		public int compareTo(Path other) { 
			return weight.compareTo(other.weight);
		}
	}
		
	

	/**
		@param: a string chr of the character to be added
		@param: a string book of the book the character was in
		@modifies: graph and books
		@effects: adds the new character to the graph and adds the book to the books if it is not present. Also adds edges to and 
				  from all other characters in the book
	 */
	public void addCharacter(String chr, String book){
		if (!graph.contains(chr)) {
			graph.addNode(chr);
		}
		
		if (books.containsKey(book)) {
			
			for (String char2 : books.get(book)) {
				if (!chr.equals(char2)) {
					graph.addEdge(new Edge<String, String>(chr, char2, book));
					graph.addEdge(new Edge<String, String>(char2, chr, book));
				}
			}
			
		} else {
			books.put(book, new HashSet<String>());
		}
		
		books.get(book).add(chr);
	}

	

	/**
		@return: number of nodes in graph
	 */
	public int numNodes(){
		return graph.numNodes();
	}

	
	
	/**
		@return: number of edges in graph
	 */
	public int numEdges(){
		return graph.numEdges();
	}

	
	
	/**
		@return: number of books in the MarvelPaths object
	 */
	public int numBooks(){
		return books.size();
	}
	
	
	/*
	public void trial() {
		for (Edge<String, Double> tri : weight_graph.getEdges()) {
			System.out.println(tri.getParent() + "   " + tri.getChild() + "   " + tri.getLabel());
		}
	}
	
	private static void MemUse() {
	     Runtime runtime = Runtime.getRuntime();
	     // Run the garbage collector
	     runtime.gc();

	     long memory = runtime.totalMemory() - runtime.freeMemory();
	     System.out.println("Used memory is bytes: " + memory);
	}

	
	
	public static void main(String[] arg) {
		
		Graph<String, String> t = new Graph<String, String>();
		System.out.println(t.getEdges());
		t.addNode("1");
		t.addNode("2");
		Edge<String, String> t1 = new Edge<String, String>("1", "2", "t1");
		Edge<String, String> t2 = new Edge<String, String>("1", "2", "t2");
		t.addEdge(t1);
		t.addEdge(t2);
		t.addEdge(t1);
		System.out.println(t.getEdges());
		
		
		// starting time 
        long start = System.currentTimeMillis();
        
		MarvelPaths2 test = new MarvelPaths2();
		test.createNewGraph("data/large_test.csv");
		System.out.println(test.numNodes());
		System.out.println(test.numEdges());
		System.out.println(test.numBooks());
		
		// ending time 
        long end = System.currentTimeMillis(); 
        System.out.println("takes " + (end - start) + "ms"); 
    	MemUse();
    	
    	//test.trial();
    	
    	
    	

		// starting time 
        start = System.currentTimeMillis();
        
		
		test.createNewGraph("data/marvel.csv");
    	System.out.println(test.findPath("WALRUSS", "OSBORN, LIZ ALLAN"));
		
		// ending time 
        System.currentTimeMillis(); 
        System.out.println("takes " + (end - start) + "ms"); 
    	MemUse();
    	
    	//test.trial();

    }*/

}