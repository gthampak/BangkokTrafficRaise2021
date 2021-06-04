package TrafficGraph;

import java.util.ArrayList;
import java.util.List;

/**
 * Class whose main method reads in description of graph and trip requests,
 * and then returns shortest paths (according to distance or time) from one
 * given vertex to another.  The input file is given by a command line argument.
 * @author eshaan
 * @author guy
 * @date 05/06/2021
 * @version 1.0
 */

public class Optimizer {
	public static void main(String[] args) {
	
		// parses file from command line
		FileParser fp = new FileParser(args[0]); 
		
		// creates graph for time and network
		EdgeWeightedDigraph roadNetworkDistance = fp.makeGraph(true);
		EdgeWeightedDigraph roadNetworkTime = fp.makeGraph(false);
		
		// if graph is strongly connected
		if (GraphAlgorithms.isStronglyConnected(roadNetworkTime)) {
			// Contains the shortest path depending on the distance or time
			ArrayList<DirectedEdge> shortestPath;
			// will later help initialize the appropriate graph depending on whether we need it for distance or time
			boolean isDistance;
			// for each trip request
			for (TripRequest trip: fp.getTrips()) {
				isDistance = trip.isDistance();
				// for distance
				if (isDistance) {
					// prints headline, i.e., what the path for the distance from the start vertex to end end 
					// as per the request. 
					System.out.println("Shortest distance from " + fp.getVertices().get(trip.getStart()) + " to " + 
				fp.getVertices().get(trip.getEnd()));
					// updates the shortestPath for distance
					shortestPath = GraphAlgorithms.getShortestPath(roadNetworkDistance, trip.getStart(), trip.getEnd());
					
				// for time	
				} else {
					// prints headline, i.e., what the path for the time from the start vertex to end end 
					// as per the request. 
					System.out.println("Shortest driving time from " + fp.getVertices().get(trip.getStart()) + " to " + 
				fp.getVertices().get(trip.getEnd()));
					// updates the shortestPath for time
					shortestPath = GraphAlgorithms.getShortestPath(roadNetworkTime, trip.getStart(), trip.getEnd());
				}
				
				// prints out the shortest path
				GraphAlgorithms.printShortestPath(shortestPath, isDistance, fp.getVertices());
			}
		} else {
			System.out.println("Graph is disconnected");
		}

	}
}
