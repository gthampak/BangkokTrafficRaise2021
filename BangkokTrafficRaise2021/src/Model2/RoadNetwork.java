package Model2;

import java.util.ArrayList;

public class RoadNetwork {

	private static final String NEWLINE = System.getProperty("line.separator");
	
    private int V; // number of vertices in this digraph
    private int R; // number of roads in this digraph
    private ArrayList<Road>[] roads; // roads matrix
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
    	
    	this.roads = (ArrayList<Road>[]) new ArrayList[V];
    	for (int v = 0; v < V; v++) {
            roads[v] = new ArrayList<Road>();
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
    public ArrayList<Road>[] roads() {
        return roads;
    }
    
    /**
     * indegree[v] = indegree of vertex v
     *
     * @return returns array of number of roads going into vertices
     */
    public int[] indegree() {
    	return indegree;
    }
    
    /**
     * returns number of iterations RoadNetwork has run
     * @return iterations
     */
    public int iterations() {
    	return iterations;
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
     * called after constructing RoadNetwork to tell lanes the roads they belong to
     * also populates toRoads ArrayList
     */
    public void assignRoadToLanes() {
    	
    	for(Road r : allRoads()) {
    		for(int i = 0; i < r.lanes().length; i++) {
    			r.lanes()[i].setRoad(r);
    		} //end for loop
    		
    		for(int i = 0; i < r.lanes().length; i++) {
    			
    			for(Lane l : r.lanes()[i].toLanes()) {
    				r.lanes()[i].addToRoads(l.road());
    				
    			}//end for loop
    			
    		} //end for loop
    		
    	} //end for loop
    	
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
    
    /**
     * toString method: prints out Road Network by showing adjacency list and road representations
     *
     * @return String to print
     */
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
    
    
    /* out-dated iterate method
	 */
    /*
     *
     * public void iterate() {
    	
    	for (int v = 0; v < V; v++) { //for all intersections
            for (Road r : roadsFrom(v)) { //for all roads starting from intersection
            	for(Lane l : r.lanes()) {
            		
            		for(int i = 0; i < l.cars().size(); i++) { //for all car slots on the road
            			
            			Car c = l.cars().get(i);
            			
            			if(c.iterations() == iterations) { //avoid doing same car twice in one iteration
            				
            				double headPos = c.headPos() + c.speed();
                			
                			if(i == 0) { //first car
                				
                				if(headPos > l.length() && l.trafficLight() == 'R') {
                    				headPos = l.length();
                    			} else if(headPos > l.length() && l.trafficLight() == 'G') {
                    				
                    				if(c.nextLane() == -1) { //if no coded next lanes (edge roads)
                    					l.cars().remove(i);
                    				} else { //go to new road/lane
                    					
//                    					//*** original next lane assignment start
//                    					 
//                    					double decideHelp = 1.0/(l.toLanes().size());
//                        		   		int toLane = (int) (Math.random() / decideHelp);
//                        				
//                        		   		headPos -= l.length();
//                        		   		
//                        		   		
//                        		   		//need to set headPos and tailPos of car before inserting
//                        		   		//or else car is inserted with wrong headPos and tailPos (problematic)
//                        		   		 
//                        		   		c.setHeadPos(headPos);
//                            			c.setTailPos(c.headPos() - c.length());
//                        		   		
//                        		   		if(l.toLanes().get(toLane).insertCar(c)) {
//                        		   			l.cars().remove(i);
//                        		   			i--;
//                        		   		} else {
//                        		   			headPos = l.length();
//                        		   		}
//
//                    					//*** original next lane assignment end
                    					
                    					
                    					headPos -= l.length();
                        		   		
                        		   		//need to set headPos and tailPos of car before inserting
                        		   		//or else car is inserted with wrong headPos and tailPos (problematic)
                        		   		 
                        		   		c.setHeadPos(headPos);
                            			c.setTailPos(c.headPos() - c.length());
                        		   		
                        		   		if(l.toLanes().get(c.nextLane()).insertCar(c)) {
                        		   			l.cars().remove(i);
                        		   			i--;
                        		   		} else {
                        		   			headPos = l.length();
                        		   		}
                        		   		
                    				}
                    				
                    		   		
                    			} else {
                    				//headPos = c.headPos() + c.speed();
                    			}
                				
                			} else { //not first car in lane
                				
                				Car inFront = l.cars().get(i-1);
                				
                				if(headPos > inFront.tailPos()) {
                					headPos = inFront.tailPos() - 1; // leave 1 meter between cars
                				} else if(headPos > l.length() && l.trafficLight() == 'G') {
                    				
                    				//go to new road/lane
                    				double decideHelp = 1.0/(l.toLanes().size());
                    		   		int toLane = (int) (Math.random() / decideHelp);
                    				
                    		   		headPos = c.speed() - (l.length() - c.headPos());
                    		   		
                    		   		if(l.toLanes().get(toLane).insertCar(c)) {
                    		   			i--;
                    		   		} else {
                    		   			headPos = l.length();
                    		   		}
                    		   		
                    			} else {
                    				//headPos = c.headPos() + c.speed();
                    			}
                				
                			}
            				
                			c.setHeadPos(headPos);
                			c.setTailPos(c.headPos() - c.length());
                			
                			c.iterate();
                			
            			}
            			
            		}//for loop close
            		
               	} //for loop close
            } //for loop close
        } //for loop close
    	
    	iterations++;
    	
    }
     * 
     */

    
    public void iterateWSpeed() {
    	
    	for (int v = 0; v < V; v++) { //for all intersections
            for (Road r : roadsFrom(v)) { //for all roads starting from intersection
            	for(int j = 0; j < r.lanes().length; j++) {
            		
            		Lane l = r.lanes()[j];
            		
            		for(int i = 0; i < l.cars().size(); i++) { //for all car slots on the road
            			
            			Car c = l.cars().get(i);
            			
            			if(c.iterations() == iterations) { //avoid doing same car twice in one iteration
                			
                			if(c.laneChange() != 0) {
                				
                				if(i == 0) {
                					
                					if(l.trafficLight() == 'R') {
                    					
                    					if(c.laneChange() < 0) { //left lane change
                    						
                    						c.updateSpeedRed(l.length(), l);

                    						if(r.lanes()[j-1].insertLaneChange(c)) {
                    							
                    							l.cars().remove(i);
                    							i--;
                    							
                    						} else { //what happens to speed of car after failed lane change?
                    							
                    						}
                    						
                    					} else { //c.laneChange > 0, right lane change
                    						
                    						if(r.lanes()[j-1].insertLaneChange(c)) {
                    							
                    							l.cars().remove(i);
                    							i--;
                    							
                    						} else { //what happens to speed of car after failed lane change?
                    							
                    						}
                    					
                    					}
                    					
                    				} else if(l.trafficLight() == 'G') {
                    					
                    					if(c.laneChange() > 0) {
                    						
                    						if(r.lanes()[j+1].insertLaneChange(c)) {
                    							
                    						}
                    						
                    					} else {
                    						
                    						
                    						
                    					}
                    					
                    				}
                					
                				} //end i == 0 if
                				
                			
                			//end laneChange != 0 if statement
                			} else { // no lane change required
                				//********* start original
                				if(i == 0) { //first car
                    				
                    				if(l.trafficLight() == 'R') {
                        				
                    					c.updateSpeedRed(l.length(), l);
                    					
                        			} else if(l.trafficLight() == 'G') {
                        				
                        				if(c.nextLane() == -1) { //no next lane
                        					
                        					if(c.speed() < l.speedLimit()) {
                        						c.accelerate(l);
                        					}
                        					
                        					if(c.headPos() > r.length()) {
                        						l.cars().remove(0);
                        					}
                        					
                        				} else {
                        					ArrayList<Car> cars = l.toLanes().get(c.nextLane()).cars();
                            				
                            				if(!cars.isEmpty()) {
                            					Car inFront = cars.get(cars.size()-1);
                                				c.updateSpeed(inFront, l);
                            				} else {
                            					c.accelerate(l); //temporary
                            				}
                        				}
                        				
                        			}

                    				
                    			} else { //not first car in lane
                    				
                    				Car inFront = l.cars().get(i - 1);
                    				
                    				c.updateSpeed(inFront, l);
                    				
                    			}
                    			
                				//********* end original
                				
                			} //end lane change else (no lane change)
            				
            				double headPos = c.headPos() + c.speed();
            				
            				if(headPos <= l.length()) {
            					c.setHeadPos(headPos);
                    			c.setTailPos(headPos - c.length());
            				} else {
            					c.setHeadPos(headPos - l.length());
            					c.setTailPos(c.headPos() - c.length());
            					
            					l.toLanes().get(c.nextLane()).insertCar(c);
            					l.cars().remove(i);
            		   			i--;
            				}
            				
                			
                			
                			System.out.println("speed " + c.speed());
            				System.out.println("hp " + c.headPos());
            				System.out.println("tp " + c.tailPos());
                			
                			c.iterate();

                			
            			} //end iterations if
            			
            		}//for loop close
            		
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

    	//simple RoadNetwork class test
    	
//    	RoadNetwork RN = new RoadNetwork(3);
//    	
//    	Road r = new Road(0, 1, 100, 4);
//    	
//    	Road r1 = new Road(0, 1, 500, 4);
//    	Road r2 = new Road(1, 2, 300, 2);
//    	
//    	for(Lane l : r1.lanes()) {
//			l.insertCar(new Car());
//			l.insertCar(new Car(Math.random()*500));
//			l.insertCar(new Car(Math.random()*500));
//			l.insertCar(new Car(Math.random()*500));
//		}
//    	
//    	for(Lane l : r2.lanes()) {
//			l.insertCar(new Car());
//			l.insertCar(new Car(Math.random()*300));
//			l.insertCar(new Car(Math.random()*300));
//		}
//    	
//    	RN.addRoad(r1);
//    	RN.addRoad(r2);
//    	
//    	System.out.println(RN);
    	
    }
    
}
