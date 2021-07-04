package Model2;

import java.util.ArrayList;

public class Road {

	//instance variables
	
	private final int from;
	private final int to;
	
	private int numLanes;
	private double length;
	
	private Lane[] lanes;
	
	
	/**
	 * constructor
	 * 
	 * @param from
	 * @param to
	 * @param length
	 * @param numLanes
	 */
	public Road(int from, int to, double length, int numLanes) {
		if (from < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
		if (to < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
		if (length < 0) throw new IllegalArgumentException("Road lengths must be nonnegative integers");
		this.from = from;
		this.to = to;
		this.length = length;
		
		this.numLanes = numLanes;
		
		lanes = new Lane[numLanes];
		for(int i = 0; i < numLanes; i++) {
			lanes[i] = new Lane(this.from, this.to, this.length, i);
		}
		
	}
	
	public int from() {
		return from;
	}
	
	public int to() {
		return to;
	}
	
	public double length() {
		return length;
	}
	
	public int numLanes() {
		return numLanes;
	}
	
	public Lane[] lanes() {
		return lanes;
	}
	
	public int numCars() {
		int numCars = 0;
		
		for(int i = 0; i < numLanes; i++) {
			numCars += lanes[i].cars().size();
		}
		
		return numCars;
	}
	
public String printRoad() {
		
		String toReturn = "";
		
		toReturn += "This road goes from vertex " + from + " to vertex " + to + "\n"; 
		
		int rl = (int) length/10 + 3;
		
		String roadEdge = "";
		for(int i = 0; i < rl; i++) {
			roadEdge += "_";
		}
		
		toReturn += roadEdge + "\n";
		
		for(Lane l: lanes) {
			toReturn += l.printLane() + "\n";
		}
		
		toReturn += roadEdge + "\n";
		
		return toReturn;
	}
	
	public String printRoad1() {
		
		String toReturn = "";
		
//		toReturn += "This road goes from vertex " + from + " to vertex " + to + "\n";
		
		for(Lane l: lanes) {
			toReturn += l.printLane() + "\n";
		}
		
		return toReturn;
	}
	
    public String toString() {
        return from + "->" + to + " " + String.format("%5.5f", length);
    }
	
	public static void main(String[] args) {
		//simple Road class test
		
//		Road r = new Road(0, 1, 100, 4);
//		
//		for(Lane l : r.lanes) {
//			l.insertCar(new Car());
//			l.insertCar(new Car(Math.random()*100));
//		}
//		
//		System.out.println(r.printRoad());
		
	}
	
}
