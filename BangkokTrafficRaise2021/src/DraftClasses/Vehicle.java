package DraftClasses;

public class Vehicle {

	//for linked-list implementation
	//private double
	
	private double speed;
	private double headPos;
	private double tailPos;
	private double length;
	
	public Vehicle(Road road) {
		speed = 0.0;
		headPos = road.length() - length;
		tailPos = headPos + length;
	}
	
	public boolean ifStationary() {
		return speed == 0.0;
	}
	
	public void accelerate1() {
		speed += 5.0;
	}
	
	public void decelerate2() {
		speed -= 5.0;
	}
	
}
