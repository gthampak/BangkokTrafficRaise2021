package FirstFlowModel;

import java.util.ArrayList;

import TrafficGraph.Bag;
import TrafficGraph.DirectedEdge;

public class RoadNetwork {

    private int V; // number of vertices in this digraph
    private int R; // number of roads in this digraph
    private Bag<Road>[] roads; // roads matrix
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
    	
    	this.roads = (Bag<Road>[]) new Bag[V];
    	for (int v = 0; v < V; v++) {
            roads[v] = new Bag<Road>();
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
    public Iterable<Road> roadsFrom(int from) {
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
        Bag<Road> list = new Bag<Road>();
        for (int v = 0; v < V; v++) {
            for (Road r : roadsFrom(v)) {
                list.add(r);
            }
        }
        return list;
    } 
    
    
    
}
