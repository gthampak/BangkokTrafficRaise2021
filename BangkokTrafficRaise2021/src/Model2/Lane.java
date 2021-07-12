package Model2;

import java.util.ArrayList;

public class Lane {
	
	private static final double DEFAULT_SPEED_LIMIT = 20.0;
	
	private final int from;
	private final int to;
	private final double length; //length of lane in meters
	
	private Road road;
	private int laneNumber;
	
	private ArrayList<Car> cars;
	
	private double speedLimit;
	private char trafficLight;
	private ArrayList<Lane> toLanes; //possible next lanes at intersection
	private ArrayList<Road> toRoads;
	
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
		this.trafficLight = 'R';
		
		this.cars = new ArrayList<>();
		this.toLanes = new ArrayList<>();
		
		this.speedLimit = DEFAULT_SPEED_LIMIT;
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
    
    public double speedLimit() {
    	return speedLimit;
    }
    
    public void setSpeedLimit(double speedLimit) {
    	this.speedLimit = speedLimit;
    }
    
    /**
     * Returns Traffic light of lane.
     * @return Traffic light char of lane
     */
    public char trafficLight() {
    	return trafficLight;
    }

    public void setTrafficLight(char tl) {
    	this.trafficLight = tl;
    }
    
    /**
     * Returns possible next lanes
     * @return toLane ArrayList
     */
    public ArrayList<Lane> toLanes() {
    	return toLanes;
    }
    
    public Road road() {
    	return road;
    }
    
    public int laneNumber() {
    	return laneNumber;
    }
    
    public void addToLanes(Lane l) {
    	toLanes.add(l);
    }
    
    public void addToRoads(Road r) {
    	toRoads.add(r);
    }
    
    public void setRoad(Road r) {
    	this.road = r;
    }
    
    /**
     * Set car array
     * @param index which index of cars car[] to set
     * @param car car to put at that index
     */
    public boolean insertCar(Car car) {
    	
    	//next lane assignment
    	
    	if(toLanes.size() != 0) {
    		double decideHelp = 1.0/(toLanes().size());
       		car.setNextLane((int) (Math.random() / decideHelp));
    	} else {
    		car.setNextLane(-1); //car goes off grid - no nextLane
    	}
    	
    	
    	//insert car starts
    	double headPos = car.headPos();
    	double tailPos = car.tailPos();
    	
    	int insertIndex = -1;
    	
    	if(headPos < 0) {
    		//insertIndex stays at -1;
    	} else {
    		
    		if(cars.isEmpty()){
        		insertIndex = 0;
        	} else if(tailPos > cars.get(0).headPos()){
        		insertIndex = 0;
        	} else if(headPos < cars.get(cars.size() - 1).tailPos()) {
        		insertIndex = cars.size();
        	} else {
        		
        		//find appropriate insertIndex of new car in ArrayList of cars
        		for(int i = 1; i < cars.size() && insertIndex == -1; i++) {
        			if(tailPos > cars.get(i + 1).headPos() && headPos < cars.get(i).tailPos()) {
        				insertIndex = i + 1;
        			}
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
    
    
    
    
    public boolean insertLaneChange(Car c) {
    	
    	double headPos = c.headPos();
    	double tailPos = c.tailPos();
    	
    	int insertIndex = -1;
    	
    	if(headPos < 0) {
    		//insertIndex stays at -1;
    	} else {
    		
    		if(cars.isEmpty()){
        		insertIndex = 0;
        		
        		cars.add(insertIndex, c);
        		return true;
        		
        	} else if(tailPos > cars.get(0).headPos()){
        		insertIndex = 0;
        		
        		boolean laneChange;
        		
        		ArrayList<Car> cars = toLanes().get(c.nextLane()).cars();
				if(!cars.isEmpty()) {
					Car front = cars.get(cars.size() - 1);
					
					//laneChange = c.DistGainLaneChange(front) > 
					
				}
        		
        		Car behind = cars.get(0);
        		
        		cars.add(insertIndex, c);
        		return true;
        		
        	} else if(headPos < cars.get(cars.size() - 1).tailPos()) {
        		insertIndex = cars.size();
        		
        		Car front = cars.get(cars.size() - 1);
        		
        		if(c.speed() < front.speed()) {
        			
        			cars.add(insertIndex, c);
            		return true;
        			
        		} else { //not done
        			
        		}

        	} else {
        		
        		//find appropriate insertIndex of new car in ArrayList of cars
        		for(int i = 1; i < cars.size() && insertIndex == -1; i++) {
        			if(tailPos > cars.get(i + 1).headPos() && headPos < cars.get(i).tailPos()) {
        				insertIndex = i + 1;
        			}
        			
        		}
        		
        	}
    		
    	}
    	
    	Car front = cars.get(insertIndex - 1);
    	Car back = cars.get(insertIndex);
    	
    	double speedDiffFront = c.speed() - front.speed();
    	double speedDiffBack = c.speed() - back.speed();
    	
    	if(insertIndex != -1 && c.oneSecAhead(back) && c.oneSecBehind(front)) {
    		
    		if(c.speedPlusMinusTwo(front)) {
    			
    		}
    		
    		cars.add(insertIndex, c);
    		return true;
    	} else {
    		System.out.println("Lane change to " + laneNumber + " of road from " + from + " to " + to + " failed.");
    		return false;
    	}
    	
    }
    
    
    /**
     * Returns String representation of Lane
     * @return String representation of Lane
     */
    public String printLane() {
    	String toReturn = from + "";
    	
    	if(cars.size() != 0) { //print empty lane leading up to last car
    		for(int i = 0; i < cars.get(cars.size() - 1).tailPos()/10; i ++) {
    			toReturn += "-";
    		}
    	} else { //no cars in lane
    		for(int i = 0; i < length/10; i ++) {
    			toReturn += "-";
    		}
    	}
    	
    	for(int i = cars.size() - 1; i >= 0; i--) {
    		
    		if(i != 0) {
    			double carDist = cars.get(i-1).tailPos() - cars.get(i).tailPos();
        		int interval = (int) carDist/10 - 1;
        		
        		toReturn += "x";
        		
        		for(int j = 0; j < interval; j++) {
        			toReturn += "-";
        		}
        		
    		} else {
    			double carDist = length - cars.get(i).tailPos();
        		int interval = (int) carDist/10 - 1;
        		
        		toReturn += "x";
        		
        		for(int j = 0; j < interval; j++) {
        			toReturn += "-";
        		}
    		}
    		
    	}
    	
    	
    	toReturn += to;
    	toReturn += trafficLight;
    	
    	
    	//add toLanes ArrayList
    	
    	return toReturn;
    }
    
    /**
     * Returns String representation of list of cars
     * @return
     */
    public String printCars() {
    	String toReturn = "";
    	
    	toReturn += "Cars list for lane " + laneNumber + " of Road from vertex " + from + " to vertex " + to + "\n";
    	
    	for(int i = 0; i < cars.size(); i++) {
    		toReturn += "Car at index " + i + cars.get(i).carCode() + "\n";
    		toReturn += cars.get(i) + "\n";
    	}
    	
    	return toReturn;
    }
    
    /**
     * Returns a string representation of the directed edge.
     * @return a string representation of the directed edge
     */
    public String toString() {
        return "Lane " + laneNumber + " of road: " + from + "->" + to + " " + String.format("%5.5f", length);
    }

    /**
     * Unit tests the {@code DirectedEdge} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
    	//simple Lane class test
    	
//    	Lane testLane = new Lane(0, 1, 100, 0);
//    	testLane.insertCar(new Car());
//    	testLane.insertCar(new Car(50.0));
//    	
//    	System.out.println(testLane.cars().size());
//    	
//    	System.out.println(testLane.printLane());
    }
}
