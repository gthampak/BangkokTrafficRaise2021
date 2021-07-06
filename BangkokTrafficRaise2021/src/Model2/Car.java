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
	
	private int nextLane;
	
	private char carCode; //for testing purposes
	
	public Car() {
		this.iterations = 0;
		
		this.tailPos = 0.0;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
		
		this.nextLane = -1;
	}
	
	public Car(int iterations) {
		this.iterations = iterations;
		
		this.tailPos = 0.0;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
		
		this.nextLane = -1;
	}
	
	public Car(double tailPos) {
		this.iterations = 0;
		
		this.tailPos = tailPos;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
		
		this.carCode = carCode;
		
		this.nextLane = -1;
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
	
	public int nextLane() {
		return nextLane;
	}
	
	public void setNextLane(int nextLane) {
		this.nextLane = nextLane;
	}
	
	public char carCode() {
		return carCode;
	}
	
	public void accelerate() {
		speed++;
	}
	
	public void decelerate() {
		if(speed == 0) {
			//do nothing
		} else {
			speed--;
		}
	}
	
	public int distGain(Car inFront) {
		
		double distBetween= inFront.tailPos() - headPos - speed;
		int db = (int) distBetween;
		
		int speedDiff = (int) (speed - inFront.speed());
		int distGain;
		
		if(speedDiff%2 == 0) {
			distGain = (speedDiff + 1) * (speedDiff/2);
		} else {
			distGain = (speedDiff + 1) * (speedDiff/2) + (speedDiff/2 + 1);
		}
		
		return distGain;
		
	}
	
	public void updateSpeed(Car inFront) {
		
		if(inFront.speed() == 0) {
			updateSpeedRed(inFront.tailPos() - 1);
		} else {
			
			double distBetween = inFront.tailPos() - headPos - inFront.speed();
			int db = (int) (distBetween/speed);
			
			double speedDiff = speed - inFront.speed();
			
			if(db < 2) {
				decelerate();
				
				System.out.println("1 happened");
				
			} else if(db == 2) {
				
				if(speedDiff < 0) {
					accelerate();
					
					System.out.println("2 happened");
					
				} else if(speedDiff == 0) {
					//do nothing
					
					System.out.println("3 happened");
					
				} else if(speedDiff > 0) {
					decelerate();
					System.out.println("4 happened");
				}
				
			} else if(db > 2) {
				
				if(speedDiff <= 0) {
					
				} else {
					if(distBetween > (2*inFront.speed() + distGain(inFront) + 2*(speed + 1))) {
						accelerate();
						System.out.println("5 happened");
					} else if(distBetween > (2*inFront.speed() + distGain(inFront) + speed + 1)) {
						//do nothing
						System.out.println("6 happened");
					} else if(distBetween > (2*inFront.speed() + distGain(inFront))) {
						decelerate();
						System.out.println("7 happened");
					} else {
						decelerate();
						System.out.println("8 happened");
					}
				}
				
			} // end if-else 2
			
		} // end if-else 1
		
	}
	
	public void updateSpeedRed(double roadLength) {
		
		double distBetween = roadLength - headPos;
		
		double decelDist;
		
		if(speed%2 == 0) {
			decelDist = (speed + 1) * (speed/2);
		} else {
			decelDist = (speed + 1) * (int) (speed/2) + (int) (speed/2) + 1;
		}
		
		if(distBetween > decelDist + speed + 1) {
			accelerate();
		} else if(distBetween >= decelDist) {
			//do nothing
		} else if(distBetween < decelDist) {
			decelerate();
		}
		
	}
	
	public String toString() {
		
		String toReturn = "";
		
		toReturn += ("Car's headPos is " + headPos + "\n");
		toReturn += ("Car's tailPos is " + tailPos + "\n");
		
		return toReturn;
		
	}
	
	
	public static void main(String[] args) {
		Car c1 = new Car();
		Car c2 = new Car();
		c2.accelerate();
		c2.accelerate();
		c2.accelerate();
		c2.accelerate();
		c2.accelerate();
	}
}
