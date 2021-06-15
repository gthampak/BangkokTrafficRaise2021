package Model2;

import java.util.ArrayList;

public class RoadNetwork {

	private static final String NEWLINE = System.getProperty("line.separator");
	
    private int V; // number of vertices in this digraph
    private int R; // number of roads in this digraph
    private ArrayList<Lane>[] roads; // roads matrix
    private int[] indegree; // indegree[v] = indegree of vertex v
    private int iterations;
    
    /**
     * Initializes a road network with {@code V} vertices and 0 edges.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public RoadNetwork(int V) {
    	if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
    	this.V = V;
    	this.R = 0;
    	
    	this.roads = (ArrayList<Lane>[]) new ArrayList[V];
    	for (int v = 0; v < V; v++) {
            roads[v] = new ArrayList<Lane>();
    	}
    	
    	this.indegree = new int[V];
    	this.iterations = 0;
    }
    
    /**
     * Returns the number of vertices in the road network.
     *
     * @return the number of vertices in this edge-weighted digraph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of roads in the road network.
     *
     * @return the number of roads in the road network
     */
    public int R() {
        return R;
    }
    
    /**
     * Returns ArrayList of Roads in Road Network
     *
     * @return the ArrayList of Roads in Road Network
     */
    public ArrayList<Lane>[] roads() {
        return roads;
    }
    
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    
    /**
     * Adds a road {@code r} to this road network.
     *
     * @param  r the edge
     * @throws IllegalArgumentException unless endpoints of road are between {@code 0}
     *         and {@code V-1}
     */
    public void addRoad(Lane r) {
        int v = r.from();
        int w = r.to();
        validateVertex(v);
        validateVertex(w);
        
        roads[v].add(r);
        indegree[w]++;
        R++;  
    }
    
    /**
     * Adds a road {@code r} to this road network.
     *
     * @param  from vertex from
     * @param to vertex to
     * @param length road length
     * @throws IllegalArgumentException unless endpoints of road are between {@code 0}
     *         and {@code V-1}
     */
    public void addRoad(int from, int to, double length) {
        validateVertex(from);
        validateVertex(to);
        roads[from].add(new Lane(from, to, length));
        indegree[to]++;
        R++;
    }
    
    /**
     * Returns array of roads incident from vertex {@code from}.
     *
     * @param  from the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public ArrayList<Lane> roadsFrom(int from) {
        validateVertex(from);
        return roads[from];
    }
    
    /**
     * Returns the number of roads incident from vertex {@code v}.
     * (outdegree of vertex {@code v}).
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(int from) {
        validateVertex(from);
        return roads[from].size();
    }
    
    /**
     * Returns the number of roads incident to vertex {@code v}.
     * (indegree of vertex {@code v}).
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(int v) {
        validateVertex(v);
        return indegree[v];
    }
    
    /**
     * Returns all roads in this edge-weighted digraph.
     * To iterate over the edges in this edge-weighted digraph, use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<Lane> allRoads() {
    	ArrayList<Lane> list = new ArrayList<Lane>();
        for (int v = 0; v < V; v++) {
            for (Lane r : roadsFrom(v)) {
                list.add(r);
            }
        }
        return list;
    } 
    
    /**
     * Prints out list of roads of this Road Network.
     *
     * @return the number of vertices <em>V</em>, followed by the number of road <em>R</em>,
     *         followed by the <em>V</em> adjacency lists of road
     */
    public String roadsList() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + R + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (Lane r : roads[v]) {
                s.append(r + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    
    /**
     * toString method: prints out Road Network by showing adjacency list and road representations
     *
     * @return String to print
     */
    public String toString() {
    	String toReturn = "";
    	
    	toReturn = roadsList();
    	
    	for (int v = 0; v < V; v++) {
            for (Lane r : roads[v]) {
            	toReturn += r.printRoad();
            	toReturn += "\n";
            }
        }
    	
    	return toReturn;
    }
    
    public void iterate() {
    	
    	for (int v = 0; v < V; v++) { //for all intersections
            for (Lane r : roadsFrom(v)) { //for all roads starting from intersection
            	for(int i = r.cars().length - 1; i >= 0; i--) { //for all car slots on the road
                	
            		if(r.cars()[i] != null && iterations == r.cars()[i].iterations()) { //car and same network-car iterations
            				
            			if(i == r.cars().length - 1) { //if car is at the end of the road
                    		
            				//car + car is moving
            				if(r.cars()[i] != null && r.cars()[i].moving() && outdegree(r.to()) != 0) {
                    			
                   				double decideHelp = 1.0/(outdegree(r.to()));
                       			int toRoad = (int) (Math.random() / decideHelp);
                       			
                       			Lane roadTo = roadsFrom(r.to()).get(toRoad);
                       			
                       			r.cars()[i].iterate();
                       			
                       			roadTo.setCar(0, r.cars()[i]);
                       			r.setCar(i, null);
                       			
                   			}
                   			
                   		} // end of car is at the end of the road
            			
            			else { //car not at the end of the road
                    			
                   			if(r.cars()[i].moving() && r.cars()[i+1] == null) {
                   				r.cars()[i].iterate();
                   				
                   				r.setCar(i+1, r.cars()[i]);
                       			r.setCar(i, null);
                   			}
                   		}
            				
           			} //end of car and not iterated
            		
            		// no car => do nothing
            		// car in front => do nothing
            		// car not moving => do nothing
            		// car-network iterations don't match
            			
               	} //for loop close
            } //for loop close
        } //for loop close
    	
    	iterations++;
    	
    }
    
    /**
     * Unit tests the {@code RoadNetwork} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

    	//moved Unit tests to main class
    	
    }
    
}
