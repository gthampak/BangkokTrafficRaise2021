package FirstFlowModel;

import java.util.ArrayList;

public class RoadNetwork {

	private static final String NEWLINE = System.getProperty("line.separator");
	
    private int V; // number of vertices in this digraph
    private int R; // number of roads in this digraph
    private ArrayList<Road>[] roads; // roads matrix
    private int[] indegree; // indegree[v] = indegree of vertex v
    
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
    	
    	this.roads = (ArrayList<Road>[]) new ArrayList[V];
    	for (int v = 0; v < V; v++) {
            roads[v] = new ArrayList<Road>();
    	}
    	
    	this.indegree = new int[V];
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
     * Returns the number of edges in this edge-weighted digraph.
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int R() {
        return R;
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
    public void addRoad(Road r) {
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
        roads[from].add(new Road(from, to, length));
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
    public ArrayList<Road> roadsFrom(int from) {
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
    public Iterable<Road> allRoads() {
    	ArrayList<Road> list = new ArrayList<Road>();
        for (int v = 0; v < V; v++) {
            for (Road r : roadsFrom(v)) {
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
            for (Road r : roads[v]) {
                s.append(r + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
    
    public String toString() {
    	String toReturn = "";
    	
    	toReturn = roadsList();
    	
    	for (int v = 0; v < V; v++) {
            for (Road r : roads[v]) {
            	toReturn += r.printRoad();
            	toReturn += "\n";
            }
        }
    	
    	return toReturn;
    }
    
    public void iterate1() {
    	for (int v = 0; v < V; v++) { //for all intersections
            for (Road r : roadsFrom(v)) { //for all roads starting from intersection
            	
            	for(int i = r.cars().length - 1; i >= 0; i--) { //for all car slots on the road
            		if(i == r.cars().length - 1) { //if car is at the end of the road
            			
            			if(r.cars()[i] != null && r.cars()[i].moving && outdegree(r.to()) != 0) {
            				double decideHelp = 1.0/(outdegree(r.to()));
                			int toRoad = (int) (Math.random() / decideHelp);
                			int toRoadLength = roadsFrom(i).get(toRoad).cars().length;
                			roadsFrom(i).get(toRoad).setCar(toRoadLength - 1, r.cars()[i]);
                			r.setCar(i, null);
            			}
            			
            		} else { //car not at the end of the road
            			
            			if(r.cars()[i] != null && r.cars()[i].moving && r.cars()[i+1] == null) {
            				r.setCar(i+1, r.cars()[i]);
                			r.setCar(i, null);
            			}
            		}
            		
            	}
            }
        }
    }
    
    /**
     * Unit tests the {@code EdgeWeightedDigraph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
//        RoadNetwork RN = new RoadNetwork(10);
//        RN.addRoad(new Road(0, 1, 1.5));
//        RN.addRoad(new Road(3, 4, 2.5));
//        RN.addRoad(new Road(1, 5, 5));
//        RN.addRoad(5, 1, 10);
//        RN.addRoad(0, 9, 1.5);
//        RN.printRoadsList();
    	
    	RoadNetwork RN1 = new RoadNetwork(2);
    	RN1.addRoad(0, 1, 60);
    	RN1.roads[0].get(0).setCar(0, new Car());
    	System.out.println(RN1.roadsList());
    	
    	System.out.println("");
    	
    	System.out.println(RN1);
    	RN1.iterate1();
    	System.out.println(RN1);
    	RN1.roads[0].get(0).setCar(2, new Car());
    	System.out.println(RN1);
    	RN1.iterate1();
    	System.out.println(RN1);
    }
    
}
