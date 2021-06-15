package DraftClasses;

import TrafficGraph.DirectedEdge;

public class Road {

	private final int from;
	private final int to;
	private final double length;
	//private final int numLanes;

	    /**
	     * Initializes a directed edge from vertex {@code v} to vertex {@code w} with
	     * the given {@code weight}.
	     * @param from the tail vertex
	     * @param to the head vertex
	     * @param length length of the road
	     * @throws IllegalArgumentException if either {@code v} or {@code w}
	     *    is a negative integer
	     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
	     */
	public Road(int from, int to, double length) {
		if (from < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
		if (to < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
		if (length < 0) throw new IllegalArgumentException("Road lengths must be nonnegative integers");
		this.from = from;
		this.to = to;
		this.length = length;
	    }

    /**
     * Returns the tail vertex of the directed edge.
     * @return the tail vertex of the directed edge
     */
	public int from() {
		return from;
	}
	
	/**
     * Returns the head vertex of the directed edge.
     * @return the head vertex of the directed edge
     */
	public int to() {
		return to;
	}

	/**
     * Returns the weight of the directed edge.
     * @return the weight of the directed edge
     */
    public double length() {
        return length;
    }

    /**
     * Returns a string representation of the directed edge.
     * @return a string representation of the directed edge
     */
    public String toString() {
        return from + "->" + to + " " + String.format("%5.5f", length);
    }

    
    
    /**
     * Unit tests the {@code DirectedEdge} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        DirectedEdge e = new DirectedEdge(12, 34, 5.67);
        System.out.println(e);
    }
}
