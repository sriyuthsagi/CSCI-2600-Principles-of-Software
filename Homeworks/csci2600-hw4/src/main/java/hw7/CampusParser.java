package hw7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CampusParser {

	/** @param: filename The path to the "CSV" file that contains the <hero, book> pairs                                                                                                
    	@param: buildings The Set that stores all the buildings in the file
    	@effects: adds Building objects into buildings
    	@throws: IOException if file cannot be read of file not properly formatted                                                                                   
	 */
	public static void readNodeData(String filename, Set<Building> buildings) 
			throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line = null;
		
		while ((line = reader.readLine()) != null) {
			String[] fields = line.split(",");
			/*
			if (fields.length != 4) {
				throw new IOException("File "+filename+" not a CSV (name, id, x-coordinate, y-coordinate) file.");
			}*/
			
			Double xcoord = Double.parseDouble(fields[2]);
			Double ycoord = Double.parseDouble(fields[3]);

			Building b = new Building(fields[0], fields[1], xcoord, ycoord);
			buildings.add(b);
		}
		reader.close();
	}
	
	
	/** @param: filename The path to the "CSV" file that contains the <hero, book> pairs                                                                                                
		@param: building_edges The List of Lists that stores the List of where the edges will go
		@effects: adds the IDs of the ends of edges to be implemented
		@throws: IOException if file cannot be read of file not properly formatted                                                                                   
	 */
	public static void readEdgeData(String filename,  List<List<String>> building_edges) 
			throws IOException {

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line = null;

		while ((line = reader.readLine()) != null) {
			String[] fields = line.split(",");
			/*
			if (fields.length != 2) {
				throw new IOException("File "+filename+" not a CSV (id1, id2) file.");
			}*/
			
			List<String> temp = new LinkedList<String>();
			temp.add(fields[0]);
			temp.add(fields[1]);
			building_edges.add(temp);
		}
		
		reader.close();
	}
	
	/*
	public static void main(String[] arg) {

    	String file = "data/RPI_map_data_Nodes.csv";

    	try {
    		Set<Building> chars = new HashSet<Building>();
    		readNodeData(file,chars);
    		for (Building i : chars) {
    			System.out.println(i.name() + " " + i.id() + " " + i.x() + " " + i.y());
    		}

    	} catch (IOException e) {
    		e.printStackTrace();
    	}

    }*/
}
