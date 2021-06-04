package TrafficGraph;
/**
 * Class to read in and parse the input data that can then be used to
 * build the graph used in finding shortest paths
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser {
	// list of all vertices & edges in graph being build
	private List<String> vertices = new ArrayList<String>();
	private List<Segment> segments = new ArrayList<Segment>();
	
	// List of all trips that should be calculated
	private List<TripRequest> trips = new ArrayList<TripRequest>();

	/**
	 * Parse input to obtain lists of vertices, edges, and trip requests.
	 * @param fileName
	 * 		file containing information on road network
	 */
	public FileParser(String fileName) {
		try {
			// file with input data on transportation network
			BufferedReader input = new BufferedReader(new FileReader(fileName));

			// get intersections
			String line = getDataLine(input);
			int numLocations = Integer.parseInt(line);

			for (int count = 0; count < numLocations; count++) {
				line = getDataLine(input);
				vertices.add(line.trim());
			}

			// get road segments
			line = getDataLine(input);
			int numSegments = Integer.parseInt(line);

			for (int count = 0; count < numSegments; count++) {
				line = getDataLine(input);
				segments.add(new Segment(line));
			}
			
			// Make sure all segments are legal, i.e., startIndex
			// and endIndex are legal for vertices.
			for (Segment segment: segments) {
				if (segment.getStart() < 0 || segment.getEnd() >= vertices.size() || 
						segment.getEnd() < 0 || segment.getStart() >= vertices.size()) {
					System.out.println("Segments are not legal");
					System.exit(0);
				}
			}

			// get trip requests
			line = getDataLine(input);
			int numTrips = Integer.parseInt(line);

			for (int count = 0; count < numTrips; count++) {
				line = getDataLine(input);
				trips.add(new TripRequest(line, vertices));
			}

			input.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	/**
	 * 
	 * @param input
	 *            file from which data is read
	 * @return next valid line of input, skips blank lines and lines starting
	 *         with #
	 * @throws IOException
	 */
	private String getDataLine(BufferedReader input) throws IOException {
		String line = input.readLine();
		while (line.trim().equals("") || line.charAt(0) == ('#')) {
			line = input.readLine();
		}

		return line;
	}

	/**
	 * 
	 * @return list of vertices (locations) from the input
	 */
	public List<String> getVertices() {
		return vertices;
	}

	/**
	 * 
	 * @return list of segments (edges) from the input
	 */
	public List<Segment> getSegments() {
		return segments;
	}

	/**
	 * 
	 * @return list of trip requests from input.
	 */
	public List<TripRequest> getTrips() {
		return trips;
	}

	/**
	 * Builds graph from input file
	 * @param isDistance Whether to make graph with edges for distance or for time.
	 * @return  Graph representing file read in
	 */
	public EdgeWeightedDigraph makeGraph(boolean isDistance) {
		
		// Create a new weighted digraph and populate it with edges based on the segments
		EdgeWeightedDigraph graph = new EdgeWeightedDigraph(vertices.size());
		
		for (Segment segment: segments) {
			// sets start and end vertices
			int start = segment.getStart();
			int end = segment.getEnd();
			double weight;
			// if it is for distance
			if (isDistance) {
				// the weight is the distance
				weight = segment.getDistance();
			} else {
				// the weight is the time, where time = distance/speed of the segment
				weight = segment.getDistance()/segment.getSpeed();
			}
			// create the edge to be added with the correct from and to vertices and weight
			DirectedEdge edge = new DirectedEdge(start, end, weight);
			graph.addEdge(edge);
		}

		return graph;

		
	}


	// testing code for file parser
	public static void main(String[] args) {
		FileParser fp = new FileParser("data/sample.txt");
		
		EdgeWeightedDigraph roadNetworkDistance = fp.makeGraph(true);
		EdgeWeightedDigraph roadNetworkTime = fp.makeGraph(false);

		System.out.println("Distance graph:\n" + roadNetworkDistance);
		System.out.println("Time graph:\n" + roadNetworkTime);
	}
}
