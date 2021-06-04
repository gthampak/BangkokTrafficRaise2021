package TrafficGraph;
/**
 * Class representing a trip request with start, end, and whether it
 * is optimizing distance or time.
 */
import java.util.List;

public class TripRequest {
	
	// Starting point of trip
	private int start;
	
	// Ending point of trip
	private int end;
	
	// Whether trip should optimize distance (instead of time)
	private boolean isDistance;
	
	/**
	 * Create trip request
	 * @param req 
	 *    String representing a textual request formatted with
	 *    the number of the start and end nodes, and a "D" if
	 *    we are to optimize the trip by distance, "T" if by time
	 * @param vertices
	 * 	A list of vertices of the graph so can look up label of nodes.
	 */
	public TripRequest(String req, List<String> vertices) {
		
		String[] reqPieces = req.split(" ");
		
		// Check input to make sure it has requisite number of
		// and tag letter is "D" or "T"
		// request gets skipped when input does not have enough 
		// pieces or tag letter is not "D" or "T"
		if (reqPieces.length != 3 || !(reqPieces[2].equals("D") || reqPieces[2].equals("T"))) {
			System.out.println("The input for this request is invalid");
			return;
		}
		
		// index of start node
		start = Integer.parseInt(reqPieces[0]);
		
		// index of end node
		end = Integer.parseInt(reqPieces[1]);
		
		// checks to see if indices are legal. If not, request gets skipped
		if (start < 0 || end >= vertices.size() || end < 0 || start >= vertices.size()) {
			System.out.println("The input for this request is invalid");
			return;
		}
		
		// true iff optimize by distance
		isDistance = reqPieces[2].equals("D");
	}
	
	/**
	 * @return index of starting node for trip
	 */
	public int getStart() {
		return start;
	}
	
	/** 
	 * @return index of ending node for trip
	 */
	public int getEnd() {
		return end;
	}

	/**
	 * @return whether to optimize by distance (instead of time)
	 */
	public boolean isDistance() {
		return isDistance;
	}
	
	/**
	 * @return representation of trip as a string
	 */
	public String toString() {
		return "Request going from "+start+" to "+end+" by "
				+(isDistance ?"distance":"time");
	}
}
