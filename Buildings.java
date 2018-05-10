package hw8;

/**
 * Buildings is an immutable class which contains the location and name of
 * buildings and end points of path segments.
 * 
 * 
 * @author RibhavHora
 */

public class Buildings {
	// x is the x coordinate of the location in pixels
	private double x;
	// y is the y coordinate of the location in pixels
	private double y;
	// longName stores the name of the building
	private String longName;
	// shortName stores the abbreviated name of the building
	private String shortName;

	// Abstraction Function :
	// x and y represent the location of an end point
	// longName and shortName represent the name of the building
	// and are empty strings if it is an end point which is not a building
	//
	// Representation function:
	// longName, shortName cannot be null

	/**
	 * Constructs a new Buildings with values
	 * 
	 * @param x
	 *            the x coordinate of the pixels
	 * @param y
	 *            the y coordinate of the pixels
	 * @param longName
	 *            name of the building or empty if not a building
	 * @param shortName
	 *            abbreviated name of the building or empty if not a building
	 * @effects Constructs a new Buildings with coordinates and name if any
	 */
	public Buildings(double x, double y, String longName, String shortName) {
		this.x = x;
		this.y = y;
		this.longName = longName;
		this.shortName = shortName;
		checkRep();
	}

	/**
	 * Constructs an empty Buildings
	 * 
	 * @effects Constructs a new empty Buildings
	 */
	public Buildings() {
		this.x = 0.0;
		this.y = 0.0;
		this.longName = "";
		this.shortName = "";
	}

	/**
	 * Gives the x coordinate of the buildings
	 * 
	 * @returns the x coordinate of the buildings
	 */
	public double getX() {
		return x;
	}

	/**
	 * Gives the y coordinate of the buildings
	 * 
	 * @returns the y coordinate of the buildings
	 */
	public double getY() {
		return y;
	}

	/**
	 * Gives the full name of the end point if it is a building
	 * 
	 * @returns the full name of the end point if it is a building or empty
	 *          string
	 */
	public String getLong() {
		return longName;
	}

	/**
	 * Gives the abbreviated name of the end point if it is a building
	 * 
	 * @returns the abbreviated name of the end point if it is a building or
	 *          empty string
	 */
	public String getShort() {
		return shortName;
	}

	/**
	 * toString in the format (x, y) where x and y are rounded to the nearest
	 * integer
	 * 
	 * @returns string of a buildings in the format (x, y) where x and y are
	 *          rounded to the nearest integer
	 */
	public String toString() {
		return "(" + Math.round(x) + ", " + Math.round(y) + ")";
	}

	/**
	 * Compares two buildings
	 * 
	 * @returns true if other Buildings object has the same position
	 */
	@Override
	public boolean equals(Object e2) {
		if (!(e2 instanceof Buildings))
			return false;
		Buildings b = (Buildings) e2;
		if (this.getLong().equals("") || b.getLong().equals("")) // comapring two non buildings
			return (this.getX() == b.getX() && this.getY() == b.getY());
		else
			return (this.getX() == b.getX() && this.getY() == b.getY()) || (this.getShort().equals(b.getShort()))
					|| (this.getLong().equals(b.getLong()));

	}

	/**
	 * Gives a hashCode for Buildings
	 * 
	 * @returns a hashCode for a Buildings
	 */
	public int hashCode() {
		return (int) x + (int) y;
	}
	
	// makes sure the rep invariant does not break
	private void checkRep() {
		assert !longName.equals(null);
		assert !shortName.equals(null);
	}

}
