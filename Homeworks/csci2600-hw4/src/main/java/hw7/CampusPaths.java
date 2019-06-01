package hw7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CampusPaths {
	
	public static void main(String[] argv) {
		
		Campus campus = new Campus();
		
		campus.createNewCampus("data/RPI_map_data_Nodes.csv", "data/RPI_map_data_Edges.csv");
		
		BufferedReader input_reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		
		while (!input.equals("q")) {
			try {
				input = input_reader.readLine();
			} catch (IOException e) {
				break;
			}
			
			if (input.equals("q")) {
				break;
				
			} else if(input.equals("b")) {
				System.out.print(campus.listBuildings());
				
			} else if(input.equals("m")) {
				System.out.println("Menu:");
				System.out.println("b lists all buildings (only buildings) in the form name,id in lexicographic (alphabetical) order of name.");
				System.out.println("r prompts the user for the ids or names of two buildings and prints directions for the shortest route between them.");
				System.out.println("q quits the program.");
				System.out.println("m prints a menu of all commands.");
				
			} else if(input.equals("r")) {
				System.out.print("First building id/name, followed by Enter: ");
				String start;
				try {
					start = input_reader.readLine();
				} catch (IOException e) {
					break;
				}
				
				System.out.print("Second building id/name, followed by Enter: ");
				String end;
				try {
					end = input_reader.readLine();
				} catch (IOException e) {
					break;
				}
				
				System.out.print(campus.findPath(start, end));
				
			} else {
				System.out.println("Unknown option");
			}
		}
		
		return;
		
	}
}