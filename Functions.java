package hw8;

import java.util.*;

import hw5.Edge;
import hw5.Graph;

/**
 * Functions has the ability to do various tasks like printing the menu, finding
 * path between two buildings, and listing buildings.
 * 
 * 
 * @author RibhavHora
 */

public class Functions {

	// This class does not represent an ADT.

	/**
	 * Prints the menu of functions
	 * 
	 * @effects Prints the menu of functions
	 */
	public static void menu() {
		System.out.println("Menu: ");
		System.out.println("\tr to find a route");
		System.out.println("\tb to see a list of all buildings");
		System.out.println("\tq to quit");
		System.out.println();

	}

	/**
	 * Prints the shortest path between two buildings
	 * 
	 * @param console
	 *            the Scanner which is used to take in input
	 * @effects Prints the shortest path between two buildings on UW campus in
	 *          the format - Walk ___ feet N to this location.
	 */
	public static void findPath(Scanner console) {
		System.out.print("Abbreviated name of starting building: ");
		String startNode = console.nextLine();
		Buildings start = CampusMain.fullBuilding(startNode, "src/hw8/data/campus_buildings.dat");
		System.out.print("Abbreviated name of ending building: ");
		String endNode = console.nextLine();
		Buildings end = CampusMain.fullBuilding(endNode, "src/hw8/data/campus_buildings.dat");
		Graph<Buildings, Double> graph = new Graph<Buildings, Double>();
		try {
			graph = PathParser.parsePathData("src/hw8/data/campus_buildings.dat", "src/hw8/data/campus_paths.dat");
		} catch (Exception e) {
			System.err.println("Caught Exceptionnn: " + e.toString());
		}
		List<Edge<Buildings, Double>> edges = CampusMain.findPath(start, end, graph);
		if (!graph.checkNode(start) || !graph.checkNode(end)) {
			if (!graph.checkNode(start))
				System.out.println("Unknown building: " + startNode);
			if (!graph.checkNode(end))
				System.out.println("Unknown building: " + endNode);
		} else {
			System.out.println("Path from " + start.getLong() + " to " + end.getLong() + ":");
			if (edges == null) {
				System.out.println("no path found");
			} else if (edges.isEmpty()) {
				System.out.println("Total distance: 0 feet");
			} else {
				double totalFeet = 0.0;
				for (Edge<Buildings, Double> edge : edges) {
					System.out.println("\tWalk " + Math.round(edge.getEdgeLabel()) + " feet " + degreesFinder(edge)
							+ " to " + edge.getEndNode());
					totalFeet += edge.getEdgeLabel();
				}
				System.out.println("Total distance: " + Math.round(totalFeet) + " feet");
			}
		}
		System.out.println();
	}

	/**
	 * Finds the direction between two points on the map
	 * 
	 * @param edge
	 *            the edge whose starting and ending is used
	 * 
	 * @returns a string which shows the direction in going from the starting
	 *          edge to the ending edge
	 */
	private static String degreesFinder(Edge<Buildings, Double> edge) {
		Buildings b1 = edge.getStartNode();
		Buildings b2 = edge.getEndNode();
		Double degrees = Math.atan2(b1.getY() - b2.getY(), b2.getX() - b1.getX());
		if (degrees > (Math.PI / 8) && degrees < ((3 * Math.PI) / 8))
			return "NE";
		else if (degrees >= ((3 * Math.PI) / 8) && degrees <= ((5 * Math.PI) / 8))
			return "N";
		else if (degrees > ((5 * Math.PI) / 8) && degrees < ((7 * Math.PI) / 8))
			return "NW";
		else if (degrees >= ((Math.PI) / -8) && degrees <= ((Math.PI) / 8))
			return "E";
		else if (degrees > ((-7 * Math.PI) / 8) && degrees < ((-5 * Math.PI) / 8))
			return "SW";
		else if (degrees > ((-5 * Math.PI) / 8) && degrees < ((-3 * Math.PI) / 8))
			return "S";
		else if (degrees > ((-3 * Math.PI) / 8) && degrees < ((Math.PI) / -8))
			return "SE";
		else
			return "W";

	}

	/**
	 * Prints a list of all the buildings on UW campus
	 * 
	 * @returns Prints a list of all the buildings on UW campus
	 */
	public static void listBuildings() {
		Set<Buildings> nameBuildings = new HashSet<Buildings>();
		Map<String, Buildings> shorts = new HashMap<String, Buildings>();
		try {
			PathParser.parseBuildingData("src/hw8/data/campus_buildings.dat", nameBuildings, shorts);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		}
		List<Buildings> sortedList = new ArrayList<Buildings>(nameBuildings);
		Collections.sort(sortedList, new Comparator<Buildings>() {
			public int compare(Buildings b1, Buildings b2) {
				return b1.getShort().compareTo(b2.getShort());
			}
		});
		System.out.println("Buildings:");
		for (Buildings b : sortedList) {
			System.out.println("\t" + b.getShort() + ": " + b.getLong());
		}
		System.out.println();
	}

}
