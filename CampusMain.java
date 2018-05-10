package hw8;

import java.util.*;
import hw5.Edge;
import hw5.Graph;
import hw7.MarvelPaths2;
import hw8.Buildings;

/**
 * CampusMain is a class which gives the entire building information from an
 * abbreviated name and finds the shortest path between two buildings.
 * 
 * 
 * @author RibhavHora
 */

public class CampusMain {

	// This class does not represent an ADT.

	/**
	 * Returns the entire Buildings object from the abbreviated name
	 * 
	 * @param shortName
	 *            the abbreviated name of the building whose entire Buildings object is needed
	 *  @param buildingData
	 *            the name of the file which contains the abbreviated name, full name
	 *            and x and y pixel coordinates separated by tabs    
	 * @returns the corresponding buildings object of the abbreviated shortName
	 */
	public static Buildings fullBuilding(String shortName, String buildingData) {
		assert shortName != null : "shortName cannot be null";
		assert buildingData != null : "buildingData cannot be null";
		Set<Buildings> nameBuildings = new HashSet<Buildings>();
		Map<String, Buildings> shorts = new HashMap<String, Buildings>();
		try {
			PathParser.parseBuildingData(buildingData, nameBuildings, shorts);
		} catch (Exception e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		}
		return shorts.get(shortName);
	}

	/**
	 * @param startNode
	 *            the starting building from who you want the path to begin
	 * @param endNode
	 *            the ending building where you want the path to end
	 * @param graph
	 *            the graph you want to search the path in
	 * @requires both startNode and endNode are in the graph
	 * @returns a list of edges that show the shortest path between startNode
	 *          and endNode, returns null if path was not found or buildings
	 *          are not in the graph
	 */
	public static List<Edge<Buildings, Double>> findPath(Buildings startNode, Buildings endNode,
			Graph<Buildings, Double> graph) {
		return MarvelPaths2.findPath(startNode, endNode, graph);
	}
}
