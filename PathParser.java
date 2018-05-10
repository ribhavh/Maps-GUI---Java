package hw8;

import hw8.Buildings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import hw5.Graph;

/**
 * PathParser reads the building file and paths file and creates a Graph from it
 * where end points/buildings are connected by distances between them.
 * 
 * 
 * @author RibhavHora
 */

public class PathParser {

	// This class does not represent an ADT.

	/**
	 * A checked exception class for bad data files
	 */
	@SuppressWarnings("serial")
	public static class MalformedDataException extends Exception {
		public MalformedDataException() {
		}

		public MalformedDataException(String message) {
			super(message);
		}

		public MalformedDataException(Throwable cause) {
			super(cause);
		}

		public MalformedDataException(String message, Throwable cause) {
			super(message, cause);
		}
	}

	/**
	 * Reads the data from filename and stores it
	 * 
	 * @param fileName
	 *            the name of the file which contains the data about the
	 *            buildings in short name, long name, x, y format separated by
	 *            tabs.
	 * @param buildingLocations
	 *            stores the buildings in fileName
	 * @param map
	 *            stores a map from short names to their buildings objects.
	 * 
	 * @effects Stores the data from fileName in buildingLocations and makes a
	 *          map of abbreviated name to their respective buildings objects.
	 */
	public static void parseBuildingData(String filename, Set<Buildings> buildingLocations, Map<String, Buildings> map)
			throws MalformedDataException {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
				// Add on - Ignore comment lines.
				if (inputLine.startsWith("#")) {
					continue;
				}
				String[] tokens = inputLine.split("\t"); // split of the line
															// into four
				if (tokens.length != 4) {
					throw new MalformedDataException("Line should contain exactly three tabs: " + inputLine);
				}
				String shortName = tokens[0];
				String longName = tokens[1];
				Double x = Double.parseDouble(tokens[2]);
				Double y = Double.parseDouble(tokens[3]);
				buildingLocations.add(new Buildings(x, y, longName, shortName)); // the buildings objects
				map.put(shortName, new Buildings(x, y, longName, shortName)); // the map from short name to its buildings object
			}
		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println(e.toString());
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	/**
	 * Reads the data from pathData and builds a graph
	 * 
	 * @param buildingData
	 *            the name of the file which contains the data about the
	 *            buildings in short name, long name, x, y format separated by
	 *            tabs.
	 * @param pathData
	 *            the name of the file which contains the locations of all end points
	 *            and to which points they are connected and the distance between them
	 *            x,y
	 *            \tx1,y1: dist
	 *            x,y is connected to x1,y1 with a distance of dist 
	 * 
	 * @returns a graph of all the end points or buildings if the end point is a building.
	 * 			The graph has buildings objects connected by the distance between them.
	 */
	public static Graph<Buildings, Double> parsePathData(String buildingData, String pathData)
			throws MalformedDataException {
		BufferedReader reader = null;
		Graph<Buildings, Double> graph = new Graph<Buildings, Double>();
		try {
			reader = new BufferedReader(new FileReader(pathData));
			Set<Buildings> nameBuildings = new HashSet<Buildings>();
			Map<String, Buildings> shorts = new HashMap<String, Buildings>();
			PathParser.parseBuildingData(buildingData, nameBuildings, shorts);
			String inputLine;
			inputLine = reader.readLine();
			while (inputLine != null) {
				// Add on - Ignore comment lines.
				if (inputLine.startsWith("#")) {
					continue;
				}
				String[] mainLine = inputLine.split(",");
				while ((inputLine = reader.readLine()) != null && inputLine.startsWith("\t")) {
					String[] firstSplit = inputLine.split(": ");
					String connectedPoint = firstSplit[0].replace("\t",""); // getting rid of the starting tab
					String[] secondPoint = connectedPoint.split(",");
					if (firstSplit.length != 2) {
						throw new MalformedDataException("Line should contain exactly two values seperated by : ");
					}
					boolean ifAdded1 = false; // to check if the point is a building and was added as one
					boolean ifAdded2 = false; // to check if the point is a building and was added as one
					double x1 = Double.parseDouble(mainLine[0]); // firstLine x coordinate
					double y1 = Double.parseDouble(mainLine[1]); // firstLine y coordinate
					double x2 = Double.parseDouble(secondPoint[0]); // tab line x coordinate
					double y2 = Double.parseDouble(secondPoint[1]); // tab line y coordinate
					Buildings b1 = new Buildings();
					Buildings b2 = new Buildings();
					for (Buildings b : nameBuildings) { // to check if the point is a building so we add it as a building
						if (b.getX() == x1 && b.getY() == y1) {
							b1 = b;
							graph.addSingleNode(b);
							ifAdded1 = true;
						}
						if (b.getX() == x2 && b.getY() == y2) {
							b2 = b;
							graph.addSingleNode(b2);
							ifAdded2 = true;
						}
					}
					if (!ifAdded1) { // if its not a building
						b1 = new Buildings(x1, y1, "", "");
						graph.addSingleNode(b1);
					}
					if (!ifAdded2) { // if its not a building
						b2 = new Buildings(x2, y2, "", "");
						graph.addSingleNode(b2);
					}
					graph.addEdge(b1, b2, Double.parseDouble(firstSplit[1])); // creating the graph
				}
			}
		} catch (IOException e) {
			System.err.println(e.toString());
			e.printStackTrace(System.err);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					System.err.println(e.toString());
					e.printStackTrace(System.err);
				}
			}
		}
		return graph;
	}

}
