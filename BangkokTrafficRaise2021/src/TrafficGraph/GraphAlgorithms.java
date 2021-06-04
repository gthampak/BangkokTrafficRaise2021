package TrafficGraph;

/**
 * Common algorithms for Graphs. 
 * They all assume working with a EdgeWeightedDirected graph.
 * 
 * @author eshaan
 * @author guy
 * @version 1.0
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

public class GraphAlgorithms {

	/**
	 * Reverses the edges of a graph
	 * 
	 * @param g
	 *            edge weighted directed graph
	 * @return graph like g except all edges are reversed
	 */
	public static EdgeWeightedDigraph graphEdgeReversal(EdgeWeightedDigraph g) {
		
		// creates new graph that will be reversed version
		EdgeWeightedDigraph reversed = new EdgeWeightedDigraph(g.V());
		
		// loop through all the edges in the graph to be reversed
		for (DirectedEdge edge: g.edges()) {
			
			// get starting and end edges
			int start = edge.from();
			int end = edge.to();
			
			// create new edge that is the same edge as the original graph, but 
			// from the end to the start, i.e., a reversed edge
			DirectedEdge reversedEdge = new DirectedEdge(end, start, edge.weight());
			
			// add this reversed edge to the reversed graph
			reversed.addEdge(reversedEdge);
		}

		return reversed;
	}

	/**
	 * Performs breadth-first search of g from vertex start.
	 * 
	 * @param g
	 *            directed edge weighted graph
	 * @param start
	 *            index of starting vertex for search
	 */
	public static void breadthFirstSearch(EdgeWeightedDigraph g, int start) {
		
		//reset graph so that no vertex starts marked as visited	
		g.reset();
		
		// the following code is regular BFS. 
		
		Deque<Integer> q = new ArrayDeque<Integer>();
		// adds starting index to queue
		q.addLast(start);
		DirectedEdge startEdge = new DirectedEdge(start, start, 0);
		// makes sure the starting edge is visited
		g.visit(startEdge, startEdge.weight());

		// while there are vertices in the graph to visit
		while( !q.isEmpty() ) {
			// remove first vertex at the front of the queue
			int v = q.removeFirst();

			// for each of its adjacent edges
			for( DirectedEdge adj: g.adj(v) ) {
				// if the adjacent vertex has not been visited
				if(!g.isVisited(adj.to())) {
					// visit the adjacent vertex
					g.visit(adj, adj.weight());
					// add the adjacent vertex to the end of the queue
					q.addLast(adj.to());
				}
			}
		}
	}

	/**
	 * Calculates whether the graph is strongly connected
	 * 
	 * @param g
	 *            directed edge weighted graph
	 * @return whether graph g is strongly connected.
	 */
	public static boolean isStronglyConnected(EdgeWeightedDigraph g) {
			
		// reset graph so that no vertex starts marked as visited
		g.reset();
		
		// do breadth-first search from start and make sure all vertices
		// have been visited. If not, return false.
		
		breadthFirstSearch(g, 0);
		int vertices = g.V();
		
		for (int i = 0; i < vertices; i++) {
			if (!g.isVisited(i)) {
				return false;
			}
		}
		
		// now reverse the graph, do another breadth-first search,
		// and make sure all visited again. If not, return false
		
		EdgeWeightedDigraph gRev = graphEdgeReversal(g);
		// reset graph so that no vertex starts marked as visited
		gRev.reset();
		breadthFirstSearch(gRev, 0);
		vertices = gRev.V();
		
		for (int i = 0; i < vertices; i++) {
			if (!gRev.isVisited(i)) {
				return false;
			}
		}
		
		// if all the vertices in the normal and reversed graph have been visited
		// after both the BFS, it must be the case that the graph is strongly 
		// connected
		return true;
	}

	/**
	 * Runs Dijkstra's algorithm on path to calculate the shortest path from
	 * starting vertex to every other vertex of the graph.
	 * 
	 * @param g
	 *            directed edge weighted graph
	 * @param s
	 *            starting vertex
	 * @return a hashmap where a key-value pair <i, path_i> corresponds to the i-th
	 *         vertex and path_i is an arraylist that contains the edges along the
	 *         shortest path from s to i.
	 */
	public static HashMap<Integer, ArrayList<DirectedEdge>> dijkstra(EdgeWeightedDigraph g, int s) {
		// reset graph 
		g.reset();
		
		// creates HashMap that will later be returned containing the shortest path 
		// from starting vertex to every other vertex of the graph
		HashMap<Integer, ArrayList<DirectedEdge>> toReturn = new HashMap<>();
		
		// creates priority queue that will be used or Dijkstra
		IndexMinPQ<Double> pq = new IndexMinPQ<Double>(g.V());
		
		// The following is Dijkstra's algorithm
		
		// sets the distance of each vertex to be infinity and inserts
		// it into the priority queue
		for( int v = 0; v < g.V(); v++ ) {
			g.setDist(v, Double.POSITIVE_INFINITY);
			pq.insert(v, Double.POSITIVE_INFINITY);
		}
		// creates the start edge to itself
		DirectedEdge start = new DirectedEdge(s, s, 0);
		// sets the shortest edge from start to be start
		g.setEdgeTo(start);
		// the distance from start to start is the weight of the edge from start to start
		g.setDist(s, start.weight());
	
		// sets the distance of start
		pq.decreaseKey(s, 0.0);

		// while there are vertices left in the priority queue
		while( !pq.isEmpty() ) {
			// get the smallest distance vertex
			int v = pq.delMin();
			
			// for each of the adjacent edges to this vertex
			for (DirectedEdge e : g.adj(v)) {
				// get the adjacent vertex
				int adj = e.to();
				
				// Relaxing the edge
				if( g.getDist(v) + e.weight() < g.getDist(adj) ) {
					g.setDist(adj, g.getDist(v) + e.weight());
					DirectedEdge temp = new DirectedEdge(v, adj, e.weight());
					g.setEdgeTo(temp);
					pq.decreaseKey(adj, g.getDist(adj));
				}
			}
		}
		// this is end the end Dijkstra's algorithm
		
		// we know add the shortest path to each vertex from the start vertex
		// into the hashmap
		
		// for each vertex
		for ( int i = 0; i < g.V(); i++) {
			// create the path that will contain the edges leading to the shortest path 
			// from the start vertex
			ArrayList<DirectedEdge> pathTo = new ArrayList<DirectedEdge>();
	
			// creates a destination variable for the vertex that will be updated as 
			// the edges in the shortest path get added
			int destination = i;
			
			// while the the vertex on the path is not the starting vertex
			while (destination != s) {
				// add the edge between the vertex that destination is reached from
				// and destination itself, since it is in the shortest path per dijkstra's
				// it is added to the start of the path since we are moving from the end 
				// of the path to the start
				pathTo.add(0, g.getEdgeTo(destination));
				
				// set destination to be the vertex that the edge to destination comes from 
				destination = g.getEdgeTo(destination).from();
			}
			
			// add the shortest path from s for the vertex to the hashmap
			toReturn.put(i, pathTo);
		}
		
			return toReturn;
				
	}

	/**
	 * Computes shortest path from start to end using Dijkstra's algorithm.
	 *
	 * @param g
	 *            directed graph
	 * @param start
	 *            starting node in search for shortest path
	 * @param end
	 *            ending node in search for shortest path
	 * @return a list of edges in that shortest path in correct order
	 */
	public static ArrayList<DirectedEdge> getShortestPath(EdgeWeightedDigraph g, int start, int end) {
		
		// run dijkstra and create a new ArrayList with edges running from start to end.
		ArrayList<DirectedEdge> shortestPath = new ArrayList<DirectedEdge>();
		shortestPath = dijkstra(g, start).get(end);
		return shortestPath;
		
	}

	/**
	 * Using the output from getShortestPath, print the shortest path
	 * between two nodes
	 * 
	 * @param path shortest path from start to end
	 * @param isDistance prints it based on distance (true) or time (false)
	 */
	public static void printShortestPath(ArrayList<DirectedEdge> path, boolean isDistance, List<String> vertices) {
		
		// looking at distance
		if (isDistance) {
			System.out.println(" Begin at " + vertices.get(0));
			// will keep track of distance as we iterate through all the edges
			double distance = 0;
			
			// for each edge
			for (DirectedEdge edge: path) {
				// print out the destination vertex of the edge with proper formatting
				System.out.println(" Continue to " + vertices.get(edge.to()) + " (" +
						(Math.round(edge.weight() * 10.0) / 10.0) + ")");
				// add the weight of the edge to our distance counter
				distance += edge.weight();
			}
			
			// print out the final distance with formatting
			System.out.println("Total Distance: " + (Math.round(distance * 10.0) / 10.0) + " miles");
		}
		
		// looking at time
		else {
			System.out.println(" Begin at " + vertices.get(0));
			// will keep track of time as we iterate through all the edges
			double time = 0;
			
			// for each edge
			for (DirectedEdge edge: path) {
				// print out the destination vertex of the edge with proper formatting
				System.out.println(" Continue to " + vertices.get(edge.to()) + " (" + hoursToHMS(edge.weight()) + ")");
				// add the weight of the edge to our time counter
				time += edge.weight();
			}
			// print out the final time with formatting
			System.out.println("Total time: " + hoursToHMS(time));
		}
		
	}

	/**
	 * Converts hours (in decimal) to hours, minutes, and seconds
	 * 
	 * @param rawhours
	 *            time elapsed
	 * @return Equivalent of rawhours in hours, minutes, and seconds (to nearest
	 *         10th of a second)
	 */
	private static String hoursToHMS(double rawhours) {
		
		//take out whole number of hours and calculate remaining minutes
		int hours = (int) rawhours;
        double rawminutes = (rawhours - hours) * 60;
        
        // take out whole number of minutes and calculate remaining seconds
        int minutes = (int) rawminutes;
        double rawseconds = (rawminutes - minutes) * 60;
        
        //round rawseconds to nearest tenth place
        double seconds = (Math.round(rawseconds * 10)/10.0);
        
        //in case the rounding rounds seconds to 60, transfer to minutes, and set seconds to 0
        if(seconds == 60) {
        	minutes++;
        	seconds = 0;
        }
        
        //in case the transferred seconds make the minutes 60, transfer to hours and set remaining minutes
        if(minutes > 59) {
        	hours++;
        	minutes = minutes - 60;
        }
        
        //check for 0s
        if (hours == 0 && minutes == 0) {
        	return seconds + " secs";
        } else if (hours == 0) {
        	return minutes + " mins " + seconds + " secs";
        } else{
        	return hours +  " hours " + minutes + " mins " + seconds + " secs";
        }
        
    	
	}
	
	// test method
	public static void main(String [] args) {
		
		//test of hoursToHMS
		System.out.println(hoursToHMS(0.005678532432));
		System.out.println(hoursToHMS(0.6666666));
		System.out.println(hoursToHMS(1.5));
		System.out.println(hoursToHMS(4.9999999)); //test edge cases for rounding errors
		
		System.out.println();
		
		EdgeWeightedDigraph testGraph = new EdgeWeightedDigraph(4);
		List<String> vertices = new ArrayList<String>();
		vertices.add("Irvine");
		vertices.add("Costa Mesa");
		vertices.add("Chino Hills");
		vertices.add("Claremont");
		testGraph.addEdge(new DirectedEdge(0, 1, 1.0));
		testGraph.addEdge(new DirectedEdge(0, 2, 1.0));
		testGraph.addEdge(new DirectedEdge(1, 3, 3.0));
		testGraph.addEdge(new DirectedEdge(2, 3, 2.0));
		testGraph.addEdge(new DirectedEdge(3,0, 3.0));
		//ArrayList<DirectedEdge> result = GraphAlgorithms.getShortestPath(testGraph, 0, 3);
		System.out.println();
		GraphAlgorithms.dijkstra(testGraph, 0);
		
	//	for (DirectedEdge edge: result) {
	//		System.out.println(edge);
	//	}
		
		Bag<DirectedEdge> adj = (Bag<DirectedEdge>) testGraph.edges();
		int nextTo = 0;
		DirectedEdge shortest = null;
		for (DirectedEdge adjEdge: adj) {
			int start = adjEdge.from();
			int end = adjEdge.to();
			double weight = -1;
			System.out.println(start + " " + end);
			System.out.println("nextTo is " + nextTo + " and i is " + "1");
			System.out.println(adjEdge.weight());
			if (start == nextTo && end == 1) {
				System.out.println("this is happening");
				weight = adjEdge.weight();
				
			}
			shortest = new DirectedEdge(start, end, weight);
		}
		
	}
	
}
