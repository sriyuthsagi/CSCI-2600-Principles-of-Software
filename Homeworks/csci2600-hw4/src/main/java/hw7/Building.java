package hw7;

public class Building implements Comparable<Building> {

	private String Name;
	private String ID;
	private Double xcoord;
	private Double ycoord;

	/**
		@param: A String designating the name of the building
		@param: A String designating the ID of the building
		@param: A Double designating the x coordinate of the building
		@param: A Double designating the y coordinate of the building
		@effects: Constructs a new Building following specifications
	 */
	public Building(String name, String id, Double x, Double y) {
		Name = new String(name);
		ID = new String(id);
		xcoord = x;
		ycoord = y;
	}
	
	/**
		@return: this Building object's name
	 */
	public String name() {
		return new String(Name);
	}
	
	/**
		@return: this Building object's ID
	 */
	public String id() {
		return new String(ID);
	}
	
	/**
		@return: this Building object's x coordinate
	 */
	public Double x() {
		return xcoord;
	}
	
	/**
		@return: this Building object's y coordinate
	 */
	public Double y() {
		return ycoord;
	}
	
	/** 
		@param: a Building other to be compared
		@requires: other is not empty
		@return: 0 if this == other, a positive number if this > other or a negative number if this < other.
	 */
	@Override
	public int compareTo(Building other) { 
		if (Name.equals(other.Name)) {
			return ID.compareTo(other.ID);
			
		} else {
			return Name.compareTo(other.Name);
		}
	}
}
