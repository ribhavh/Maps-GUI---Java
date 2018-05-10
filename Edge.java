package hw5;

/**
 * An edge is an immutable class that represents a connection between two nodes.
 * An edge is labeled with E and a node with N.
 * 
 * 
 * @author RibhavHora
 */

public class Edge<N, E> {
	// keeps track of the node the edge is connected to
	private N endNode;

	// keeps track of the label of the edge
	private E label;

	// keeps track of the node starting the edge
	private N startNode;

	// Abstraction Function :
	// endNode is the node the edge is connected to
	// and label is the name/label of the edge itself.
	//
	// Representation function:
	// An edge cannot be null.

	/**
	 * @param endNode
	 *            the node which the edge is attached to
	 * @param label
	 *            the label of the edge
	 * @effects Constructs a new Edge e with an endNode and label
	 */
	public Edge(N endNode, E label) {
		this.endNode = endNode;
		this.label = label;
	}

	/**
	 * @param endNode
	 *            the node which the edge is attached to
	 * @param label
	 *            the label of the edge
	 * @param startNode
	 *            the node which starts the edge
	 * @effects Constructs a new Edge e with an endNode, startNode and label
	 */
	public Edge(N startNode, N endNode, E label) {
		this.endNode = endNode;
		this.label = label;
		this.startNode = startNode;
	}

	/**
	 * @returns E(the label) of the edge
	 */
	public E getEdgeLabel() {
		return label;
	}

	/**
	 * @returns N(label) of the node the edge is connected to
	 */
	public N getEndNode() {
		return endNode;
	}

	/**
	 * @returns N(label) of the node the edge is connected from
	 */
	public N getStartNode() {
		return startNode;
	}

	/**
	 * @returns a string of the the Edge object in EndNode(EdgeLabel) format
	 */
	public String toString() {
		return this.getEndNode() + "(" + this.getEdgeLabel() + ")";
	}

	/**
	 * @returns true if other Edge object has the same label and endNode, or
	 *          returns false
	 */
	@Override
	public boolean equals(Object e2) {
		if (!(e2 instanceof Edge<?, ?>))
			return false;
		Edge<?, ?> e = (Edge<?, ?>) e2;
		return this.getEndNode().equals(e.getEndNode()) && this.getEdgeLabel().equals(e.getEdgeLabel());
	}
}
