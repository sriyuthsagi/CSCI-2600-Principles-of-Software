package hw5;

import hw4.Graph;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import hw4.Edge;

public class MarvelPaths {
	
	/**
	MarvelPaths represents a mutable object containing a Graph object and a Map object that stores books and characters.
	
	
	Representation Invariant: All edge labels can be found in the keys of books
	
	
	Abstraction Function: A Graph graph exists where graph.nodes represents a collection of characters while graph.edges represents a 
	collection of Edges that represent characters being in the same book.
	
	*/
	
	
	private Graph<String, String> graph;
	private HashMap<String, Set<String>> books;
	
	
	/**
		@effects: Constructs a new empty MarvelPaths
	 */
	public MarvelPaths() {
		graph = new Graph<String, String>();
		books = new HashMap<String, Set<String>>();
	}
	
	
	/**
		@modifies: graph and books
		@effects: clears the graph and books
	 */
	public void clear() {
		graph.clear();
		books.clear();
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
			MarvelParser.readData(filename, books, characters);
			
			for (String ch : characters) {
				graph.addNode(ch);
			}
			
			for (String book : books.keySet()) {
				
				Set<String> single_book = books.get(book);
				
				for (String temp1 : single_book) {
					for (String temp2 : single_book) {
						
						if (!temp1.equals(temp2)) {
							graph.addEdge(new Edge<String, String>(temp1, temp2, book));
						}
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
		@param: a starting node node1 for the path
		@param: a destination node node2 for the path
		@return: a string listing all of the nodes and edges visited along the shortest path
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
		
		LinkedList<String> Q = new LinkedList<String>();
		Map<String, LinkedList<Edge<String, String>>> M = new HashMap<String, LinkedList<Edge<String, String>>>();
	
		Q.add(node1);
		M.put(node1, new LinkedList<Edge<String, String>>());
	
		while (Q.size() != 0) {
			String temp_node = Q.pop();
			
			if (temp_node.equals(node2)) {
				for (Edge<String, String> e : M.get(temp_node)) {
					path += e.getParent() + " to " + e.getChild() + " via " + e.getLabel() + "\n";
				}
				return path;
			}
			
			boolean next_break = false;
			
			Iterator<Edge<String, String>> itr = graph.listChildrenSorted(temp_node).iterator();
			while (itr.hasNext()) {
				Edge<String, String> e = itr.next();
				
				if (temp_node.equals(e.getParent())) {
					next_break = true;
					
					LinkedList<Edge<String, String>> next_path = new LinkedList<Edge<String, String>>(M.get(temp_node));
					next_path.addLast(e);
					
					if (!M.containsKey(e.getChild())) {
						M.put(e.getChild(), next_path);
						Q.add(e.getChild());
						
					} else if (M.get(e.getChild()).size() > next_path.size()) {
							M.put(e.getChild(), next_path);
					}
					
				} else if (next_break) {
					next_break = false;
					break;
				}
			}
		}
		
		return path + "no path found\n";
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
	
	
	private static void MemUse() {
	     Runtime runtime = Runtime.getRuntime();
	     // Run the garbage collector
	     runtime.gc();

	     long memory = runtime.totalMemory() - runtime.freeMemory();
	     System.out.println("Used memory is bytes: " + memory);
	}

	
	
	public static void main(String[] arg) {
		
		
		
		// starting time 
        long start = System.currentTimeMillis();
        
		MarvelPaths test = new MarvelPaths();
		test.createNewGraph("data/two_connected_nodes.csv");
		System.out.println(test.numNodes());
		System.out.println(test.numEdges());
		System.out.println(test.numBooks());
		
		// ending time 
        long end = System.currentTimeMillis(); 
        System.out.println("takes " + (end - start) + "ms"); 
    	MemUse();
    	
    	
    	
    	
    	

		// starting time 
        start = System.currentTimeMillis();
        
		
		test.createNewGraph("data/marvel.csv");
    	System.out.println(test.findPath("WALRUSS", "OSBORN, LIZ ALLAN"));
		
		// ending time 
        System.currentTimeMillis(); 
        System.out.println("takes " + (end - start) + "ms"); 
    	MemUse();

    }

}