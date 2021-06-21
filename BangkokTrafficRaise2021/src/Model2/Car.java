package Model2;

public class Car {

	//test variables
	private static final double testSpeed = 10.0;
	private static final double testAcceleration = 0.0;
	private static final double testLength = 5.0;
	
	private int iterations; //keeps track of road network iterations
	private double length; //car length (in meters)
	
	private double headPos; //position of car head on road/lane
	private double tailPos; //position of car tail on road/lane
	
	private double speed; //speed in meters per second/iteration
	private double acceleration;
	
	private char carCode; //for testing purposes
	
	public Car() {
		this.iterations = 0;
		
		this.tailPos = 0.0;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
	}
	
	public Car(double tailPos) {
		this.iterations = 0;
		
		this.tailPos = tailPos;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
		
		this.carCode = carCode;
	}
	
	public Car(double tailPos, int iterations) {
		this.iterations = iterations;
		
		this.tailPos = tailPos;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
		
		this.carCode = carCode;
	}
	
	public Car(double tailPos, char carCode) {
		this.iterations = 0;
		
		this.tailPos = tailPos;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
		
		this.carCode = carCode;
	}
	
	public int iterations() {
		return iterations;
	}
	
	public void iterate() {
		iterations++;
	}
	
	public double headPos() {
		return headPos;
	}
	
	public void setHeadPos(double headPos) {
		this.headPos = headPos;
	}
	
	public double tailPos() {
		return tailPos;
	}
	
	public void setTailPos(double tailPos) {
		this.tailPos = tailPos;
	}
	
	public double speed() {
		return speed;
	}
	
	public double acceleration() {
		return acceleration;
	}
	
	public double length() {
		return length;
	}
	
	public char carCode() {
		return carCode;
	}
	
	public String toString() {
		
		String toReturn = "";
		
		toReturn += ("Car's headPos is " + headPos + "\n");
		toReturn += ("Car's tailPos is " + tailPos + "\n");
		
		return toReturn;
		
	}
	
}
