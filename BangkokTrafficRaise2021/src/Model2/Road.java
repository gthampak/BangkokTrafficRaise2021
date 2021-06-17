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
	
	public String printRoad() {
		
	}
	
}
