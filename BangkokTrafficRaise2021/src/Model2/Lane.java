package Model2;

import java.util.ArrayList;

public class Lane {
	
	private final int from;
	private final int to;
	private final double length; //length of lane in meters
	private int laneNumber;
	
	private ArrayList<Car> cars;
	
	private char trafficLight;
	private ArrayList<Lane> toLanes; //possible next lanes at intersection
	
	//private final int numLanes;

	    /**
	     * Initializes a directed edge from vertex {@code v} to vertex {@code w} with
	     * the given {@code weight}.
	     * @param from the tail vertex
	     * @param to the head vertex
	     * @param length length of the lane
	     * @throws IllegalArgumentException if either {@code v} or {@code w}
	     *    is a negative integer
	     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
	     */
	public Lane(int from, int to, double length, int laneNumber) {
		this.from = from;
		this.to = to;
		this.length = length;
		
		this.laneNumber = laneNumber;
		
		this.cars = new ArrayList<>();
		this.toLanes = new ArrayList<>();
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
     * Returns the length of the lane.
     * @return the length of the lane
     */
    public double length() {
        return length;
    }
    
    /**
     * Returns ArrrayList of cars in the lane.
     * @return array of cars in lane
     */
    public ArrayList<Car> cars() {
    	return cars;
    }
    
    /**
     * Returns Traffic light of lane.
     * @return Traffic light char of lane
     */
    public char trafficLight() {
    	return trafficLight;
    }

    /**
     * Returns possible next lanes
     * @return toLane ArrayList
     */
    public ArrayList<Lane> toLanes() {
    	return toLanes;
    }
    
    /**
     * Set car array
     * @param index which index of cars car[] to set
     * @param car car to put at that index
     */
    public boolean insertCar(Car car) {
    	
    	double headPos = car.headPos();
    	double tailPos = car.tailPos();
    	
    	int insertIndex = -1;
    	
    	if(cars.isEmpty()){
    		insertIndex = 0;
    	} else if(headPos < cars.get(0).tailPos()){
    		insertIndex = 0;
    	} else if(tailPos > cars.get(cars.size() - 1).headPos()) {
    		insertIndex = cars.size();
    	} else {
    		
    		//find appropriate insertIndex of new car in ArrayList of cars
    		for(int i = 1; i < cars.size() && insertIndex == -1; i++) {
    			if(tailPos > cars.get(i - 1).headPos() && headPos < cars.get(i).tailPos()) {
    				insertIndex = i;
    			}
    		}
    		
    	}
    	
    	if(insertIndex != -1) {
    		cars.add(insertIndex, car);
    		return true;
    	} else {
    		System.out.println("Car not inserted in lane " + laneNumber + " of road from " + from + " to " + to);
    		return false;
    	}
    	
    	
    }
    
    public String printLane() {
    	String toReturn = from + "";
    	
    	for(int i = 0; i < cars.size(); i++) {
    		
    		if(i != cars.size()-1) {
    			double carDist = cars.get(i+1).tailPos() - cars.get(i).tailPos();
        		int interval = (int) carDist/10 - 1;
        		
        		toReturn += "x";
        		
        		for(int j = 0; j < interval; j++) {
        			toReturn += "-";
        		}
        		
    		} else {
    			double carDist = length - cars.get(i).tailPos();
        		int interval = (int) carDist/10;
        		
        		toReturn += "x";
        		
        		for(int j = 0; j < interval; j++) {
        			toReturn += "-";
        		}
    		}
    		
    	}
    	
    	toReturn += to;
    	
    	//add toLanes ArrayList
    	
    	return toReturn;
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
    	//simple Lane class test
    	
    	Lane testLane = new Lane(0, 1, 100, 0);
    	testLane.insertCar(new Car());
    	testLane.insertCar(new Car(50.0));
    	
    	System.out.println(testLane.cars().size());
    	
    	System.out.println(testLane.printLane());
    }
}