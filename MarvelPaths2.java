package hw7;

import java.util.*;

/**
 * MarvelPaths2 builds a weighted graph from a file and finds the least weighted path
 * between two nodes.
 * 
 * 
 * @author RibhavHora
 */

import hw5.Graph;
import hw6.MarvelPaths;
import hw5.Edge;

public class MarvelPaths2 {
	
	// This class does not represent an ADT.

	/**
	 * @param fileName
	 *            the name of the file whose graph you want to create (TSV format)
	 * @returns a graph of the characters connected by how well connected they are -
	 * 			inverse of the number of books that two characters have in common
	 */
	public static Graph<String, Double> buildGraph(String fileName) {
		assert fileName != null : "fileName cannot be null"; // error checking
		Graph<String, String> oldGraph = MarvelPaths.buildGraph(fileName); // using non weighted graph
		Graph<String, Double> mainGraph = new Graph<String, Double>(); // the new graph we're working on
		for (String character : oldGraph.listNodes()) {
			mainGraph.addSingleNode(character); // adds all characters in the
												// graph
		}
		for (String character : oldGraph.listNodes()) {
			List<Edge<String, String>> edges = new ArrayList<Edge<String, String>>(oldGraph.listChildren(character)); // converting to list for easier use
			Set<String> visited = new HashSet<String>(); // keeps track of edges that already been counted
			for (Edge<String, String> edge : edges) {
				double countOfConnections = 0; // counts the same edges connected to a node
				if (!visited.contains(edge.getEndNode())) { // only if we haven't already counted this edge
					for (int i = 0; i < edges.size(); i++) {
						if (edge.getEndNode().equals(edges.get(i).getEndNode()))
							countOfConnections++;
					}
					visited.add(edge.getEndNode()); // updates what has been visited
					mainGraph.addEdge(character, edge.getEndNode(), 1.0 / countOfConnections); // main function
				}
			}
		}
		return mainGraph;
	}
	
	/**
	 * @param startNode
	 *            the starting character from who you want the path to begin
	 * @param endNode
	 *            the ending character where you want the path to end
	 * @param graph
	 *            the graph you want to search the path in
	 * @requires both startNode and endNode are in the graph 
	 * @returns a list of edges that show the path with the least cost between startNode
	 *          and endNode, returns null if path was not found or characters
	 *          are not in the graph
	 */
	public static <N> List<Edge<N, Double>> findPath(N startNode, N endNode, Graph<N, Double> graph) {
		assert graph != null : "graph cannot be null";
		if (graph.checkNode(startNode) && graph.checkNode(endNode)) { // additional error checking
			PriorityQueue<List<Edge<N, Double>>> active = new PriorityQueue<List<Edge<N, Double>>>(
					new Comparator<List<Edge<N, Double>>>() { // comparator arranges list by total weights of edges.
						public int compare(List<Edge<N, Double>> firstList, List<Edge<N, Double>> secondList) {
							double firstTotal = 0.0;
							double secondTotal = 0.0;
							for (Edge<N, Double> e : firstList) {
								firstTotal += e.getEdgeLabel();
							}
							for (Edge<N, Double> e : secondList) {
								secondTotal += e.getEdgeLabel();
							}
							if (firstTotal < secondTotal)
								return -1;
							else if (firstTotal > secondTotal)
								return 1;
							else
								return 0; // they are equal

						}
					});
			Set<N> finished = new HashSet<N>(); // nodes whose minimum paths we know
			List<Edge<N, Double>> start = new ArrayList<Edge<N, Double>>(); // initial path of startNode
			start.add(new Edge<N, Double>(startNode, 0.0)); // path to itself
			active.add(start);
			while (!active.isEmpty()) {
				List<Edge<N, Double>> minPath = active.remove(); // lowest cost path in active
				N minDest = minPath.get(minPath.size() - 1).getEndNode(); // destination node in minPath
				if (minDest.equals(endNode)) {
					minPath.remove(0);
					return minPath;
				}
				if (finished.contains(minDest)) {
					continue; // ends the iteration 
				}
				for (Edge<N, Double> edge : graph.listChildren(minDest)) {
					if (!finished.contains(edge.getEndNode())) {
						List<Edge<N, Double>> newPath = new ArrayList<Edge<N, Double>>();
						for (Edge<N, Double> edges : minPath) {
							newPath.add(edges); // making a copy of minPath
						}
						newPath.add(edge); 
						active.add(newPath); // updating the path
					}
				}
				finished.add(minDest);
			}
		}
		return null; // if path between the characters not found or they are not
						// in the graph
	}

}
