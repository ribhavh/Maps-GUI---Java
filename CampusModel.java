package hw9;

import java.util.*;

import hw5.Edge;
import hw5.Graph;
import hw8.Buildings;
import hw8.CampusMain;
import hw8.PathParser;

/**
 * CampusModel represents the model of the program and has functions like
 * finding the path between two buildings and returning a list of buildings.
 * 
 * @author RibhavHora
 */

public class CampusModel {

	// This class does not represent an ADT.

	/**
	 * @param startNode
	 *            the starting building from who you want the path to begin
	 * @param endNode
	 *            the ending building where you want the path to end
	 * @requires both startNode and endNode are in the graph
	 * @returns a list of edges that show the shortest path between startNode
	 *          and endNode, returns null if path was not found or buildings are
	 *          not in the graph
	 */
	public static List<Edge<Buildings, Double>> findPath(String startNode, String endNode) {
		Graph<Buildings, Double> graph = new Graph<Buildings, Double>();
		try {
			graph = PathParser.parsePathData("src/hw8/data/campus_buildings.dat", "src/hw8/data/campus_paths.dat");
		} catch (Exception e) {
			System.err.println("Caught Exception: " + e.toString());
		}
		Buildings start = CampusMain.fullBuilding(startNode, "src/hw8/data/campus_buildings.dat");
		Buildings end = CampusMain.fullBuilding(endNode, "src/hw8/data/campus_buildings.dat");
		return CampusMain.findPath(start, end, graph);
	}

	/**
	 * @returns an array that contains the abbreviated names of all the
	 *          buildings and an extra place holder string for convenience.
	 */
	public static String[] returnBuildings() {
		Set<Buildings> nameBuildings = new HashSet<Buildings>();
		Map<String, Buildings> shorts = new HashMap<String, Buildings>();
		try {
			PathParser.parseBuildingData("src/hw8/data/campus_buildings.dat", nameBuildings, shorts);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		}
		String[] shortNames = new String[shorts.size() + 1];
		shortNames[0] = "Select Building"; // place holder
		int i = 1;
		for (String s : shorts.keySet()) {
			shortNames[i] = s;
			i++;
		}
		return shortNames;
	}

}
