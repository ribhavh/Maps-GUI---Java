package hw5;

import java.util.*;

/**
 * A Graph represents a mutable collection of nodes and edges. Two graph are
 * connected by an edge. Edges between two graph are labeled with E.
 * 
 * 
 * @author RibhavHora
 */

public class Graph<N,E> {

	// graph and edges of the graph
	private Map<N, Set<Edge<N,E>>> graph;

	private static final boolean EXPENSIVE = false;

	// Abstraction Function:
	// Keys of graph are nodes and the values are Sets of Edges that are
	// connected
	// that node. If there is no edge attached to a node, that node has a value
	// in the map of null.
	//
	// Representation Invariant:
	// graph != null && No key in the map is null.

	/**
	 * @param node
	 *            The node which will be added to the graph.
	 * @effects Constructs a new Graph g
	 */
	public Graph(N node) {
		graph = new HashMap<N, Set<Edge<N,E>>>();
		graph.put(node, null);
		checkRep();
	}

	/**
	 * @effects Constructs a new Graph g
	 */
	public Graph() {
		graph = new HashMap<N, Set<Edge<N,E>>>();
		checkRep();
	}

	/**
	 * @returns true if the graph is empty or returns false if not
	 */
	public boolean isEmpty() {
		return graph.isEmpty();
	}

	/**
	 * @param node
	 *            node is what will get added to the graph
	 * @requires n != null
	 * @modifies this
	 * @effects Adds a node "node" to the graph
	 */
	public void addSingleNode(N node) {
		if(!checkNode(node))
			graph.put(node, null);
		checkRep();
	}

	/**
	 * @param newNode
	 *            node is what will get added to the graph
	 * @param toNode
	 *            the node to which you want to add a new node
	 * @param label
	 *            the label of the edge that is used to add newNode to toNode
	 * 
	 * @requires newNode != null && toNode !=null && label != null && toNode is
	 *           in the graph
	 * @modifies this
	 * @effects Adds newNode to the graph and connects it to toNode which is
	 *          already in the graph using an edge with label "label".
	 */
	public void addToNode(N newNode, N toNode, E label) {
		graph.put(newNode, null);
		this.addEdge(toNode, newNode, label);
		checkRep();
	}

	/**
	 * @param firstNode
	 *            the node where the edge will start
	 * @param secondNode
	 *            the node where the edge will end
	 * @param label
	 *            the label of the edge that connects the two graph
	 * 
	 * @requires firstNode != null && secondNode !=null && label != null && both
	 *           nodes are in the graph already
	 * @modifies this
	 * @effects Connects two graph already in the graph with an edge and a label
	 */
	public void addEdge(N firstNode, N secondNode, E label) {
		if (graph.get(firstNode) == null)
			graph.put(firstNode, new HashSet<Edge<N,E>>());
		graph.get(firstNode).add(new Edge<N,E>(firstNode, secondNode, label));
		checkRep();
	}

	/**
	 *
	 * @returns a Set containing all the nodes in the graph
	 */
	public Set<N> listNodes() {
		Set<N> result = new HashSet<N>();
		for (N node : graph.keySet())
			result.add(node);
		Set<N> safeResult = Collections.unmodifiableSet(result);
		return safeResult;
	}

	/**
	 * @effects clears the existing graph
	 */
	public void clearGraph() {
		graph.clear();
	}

	/**
	 * @param node
	 *            the node whose connections it checks
	 * @requires node is in the graph && node != null && node has children
	 * @returns a Set which contains a list of all the nodes that are connected
	 *          to "node".
	 */
	public Set<Edge<N,E>> listChildren(N node) {
		if(checkChildren(node)) {
			Set<Edge<N,E>> result = new HashSet<Edge<N,E>>();
			for (Edge<N,E> edges : graph.get(node))
				result.add(edges);
			Set<Edge<N,E>> safeResult = Collections.unmodifiableSet(result);
			return safeResult;
		}
		return new HashSet<Edge<N,E>>();
		
	}

	/**
	 * @param node
	 *            is the node that gets checked
	 * @returns true if the node exists in the graph
	 */
	public boolean checkNode(N node) {
		return graph.containsKey(node);
	}

	/**
	 * @param node
	 *            is the node that gets checked
	 * @returns true if the node does not have anything connected to it
	 */
	public boolean checkChildren(N node) {
		return graph.get(node) != null;
	}

	private void checkRep() {
		assert (graph != null);

		if (EXPENSIVE) {
			for (N node : graph.keySet())
				assert (!node.equals(null)) : "null entered";
		}

	}

}