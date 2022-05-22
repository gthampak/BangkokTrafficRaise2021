package Model2;

import java.util.ArrayList;

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
	//private boolean decelerated = false;
	
	private ArrayList<Road> path;
	private int laneChange;
	
	private int nextLane;
	private boolean incomingLC = false; //whether another car needs to/wants to signal lane change to position in front
	
	private char carCode; //for testing purposes
	

	/**
	 * default constructor
	 */
	public Car() {
		this.iterations = 0;
		
		this.tailPos = 0.0;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
		
		this.nextLane = -1;
	}
	
	/**
	 * 
	 * 
	 * @param iterations
	 */
	public Car(int iterations) {
		this.iterations = iterations;
		
		this.tailPos = 0.0;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
		
		this.nextLane = -1;
		
		this.laneChange = 0;
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
		
		this.laneChange = 0;
	}
	
	public Car(double tailPos, int iterations) {
		this.iterations = iterations;
		
		this.tailPos = tailPos;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
		
		this.carCode = carCode;
		
		this.nextLane = -1;
		
		this.laneChange = 0;
	}
	
	public Car(double tailPos, char carCode) {
		this.iterations = 0;
		
		this.tailPos = tailPos;
		this.headPos = tailPos + testLength;
		
		this.speed = testSpeed; 
		this.acceleration = testAcceleration;
		
		this.length = testLength;
		
		this.carCode = carCode;
		
		this.nextLane = -1;
		
		this.laneChange = 0;
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
	
//	public boolean decelerated() {
//		return decelerated;
//	}
	
	public double length() {
		return length;
	}
	
	public int nextLane() {
		return nextLane;
	}
	
	public int laneChange() {
		return laneChange;
	}
	//testing git
	public void setLaneChange(int lc) {
		laneChange = lc;
	}
	
	public void laneChanged() {
		if(laneChange > 0) {
			laneChange--;
		} else if(laneChange < 0) {
			laneChange++;
		}
	}
	
	public void setIncomingLC(boolean ilc) {
		incomingLC = ilc;
	}
	
	public boolean incomingLC() {
		return incomingLC;
	}
	
	public void setNextLane(int nextLane) {
		this.nextLane = nextLane;
	}
	
	public char carCode() {
		return carCode;
	}
	
	public void accelerate(Lane l) {
		if(speed != l.speedLimit()) {
			speed++;
		}
	}
	
	public void accelerate2(Lane l) {
		if(speed != l.speedLimit()) {
			speed+=2;
		}
	}
	
	public void decelerate() {
		if(speed == 0) {
			//do nothing
		} else {
			speed--;
		}
		
		//decelerated = true;
	}
	
	public void decelerate2() {
		if(speed == 1) {
			speed--;
		} else if(speed == 0){
			
		} else {
			speed -= 2;
		}
		
		//decelerated = true;
	}
	
	public void decelerate3() {
		if(speed == 1) {
			speed--;
		} else if(speed == 2) {
			speed-=2;
		} else if(speed == 0){
			
		} else {
			speed -= 3;
		}
		
		//decelerated = true;
	}
	
	//in use
	public int db(Car inFront) {
		
		double distBetween = inFront.tailPos() - headPos;
		int db = (int) (distBetween/inFront.speed());
		
		return db;
		
	}
	
	//in use
	public double maxDecelDist(double spd, Car inFront) {
		
		double maxDecelDist = 0;
		double speedDiff = spd - inFront.speed();
		
		if(speedDiff > 0) {
			maxDecelDist = speedDiff*spd + 1;
		}
		
		return maxDecelDist;

	}

	
	//in use
	public double maxDecel2Dist(Car inFront) {
		
		double maxDecelDist = 0;
		double speedDiff = speed - inFront.speed();
		
		if(speedDiff > 0) {

			for(double i = speed; i > 0; i -= 2) {
				maxDecelDist += speedDiff;
			}
			
		}
		
		return maxDecelDist + 1;

	}
	
	//in use
	public double maxDecel3Dist(Car inFront) {
		
		double maxDecelDist = 0;
		double speedDiff = speed - inFront.speed();
		
		if(speedDiff > 0) {

			for(double i = speed; i > 0; i -= 3) {
				maxDecelDist += speedDiff;
			}
			
		}
		
		return maxDecelDist + 1;
		
	}
	
	//in use
	public boolean checkAccelerate2(double spd, Car inFront) {
		
		boolean toReturn = false;
		
		double distBetween = inFront.tailPos() - headPos;
		double newDist = distBetween - spd;
		
		if(newDist > maxDecel3Dist(inFront)) {
			toReturn = true;
		}
		
		return toReturn;
	}
	
	//in use
	public void updateSpeed(Car inFront, Lane l) {
		
		//add update speed under car trying to lane change
		
		if(inFront.speed() == 0) {
			updateSpeedRed(inFront.tailPos() - 1, l);
		} else {
			
			double distBetween = inFront.tailPos() - headPos - inFront.speed();
			int db = (int) (distBetween/inFront.speed());
			double maxDecelDist1 = maxDecelDist(speed+1, inFront);
			double maxDecelDist2 = maxDecelDist(speed, inFront);
			double maxDecelDist3 = maxDecel2Dist(inFront);
			double maxDecelDist4 = maxDecel3Dist(inFront);
			
			if(speed > inFront.speed()) {
				if(distBetween > maxDecelDist1) {
					accelerate(l);
				} else if(distBetween > maxDecelDist2){
					//do nothing
				} else if(distBetween > maxDecelDist3){
					decelerate();
				} else if(distBetween > maxDecelDist4) {
					decelerate2();
				} else {
					decelerate3();
				}
			} else {
				if(db > 2) {
					accelerate(l);
				} else if(db == 2) {

					if(inFront.speed() > speed) {
						accelerate(l);
					}
					
				} else {
					decelerate();
				}
			}
			
		}

		
	}

	public void updateSpeedRed2(double roadLength, Lane l) {
		
		double distBetween = roadLength - headPos;
		
		double decelDist;
		
		if(speed%2 == 0) {
			decelDist = (speed + 1) * (speed/2);
		} else {
			decelDist = (speed + 1) * (int) (speed/2) + (int) (speed/2) + 1;
		}
		
		if(distBetween > decelDist + speed + 1) {
				accelerate(l);
				//decelerated = false;
		} else if(distBetween >= decelDist) {
			//do nothing
			//decelerated = false;
		} else if(distBetween < decelDist) {
			decelerate();
		}
		
	}
	
	//in use for updateSpeedRed
	public double decelDistToStop(double speed) {
		
		double decelDist;
		
		if(speed%4 == 0) {
			decelDist = (speed+2)*(speed/4);
		} else if( (speed+1)%4 == 0 ) {
			decelDist = (speed+1)*((speed+1)/4);
		} else if( (speed-1)%4 == 0 ) {
			decelDist = (speed-1)*((speed-1)/4) + speed;
		} else {
			decelDist = speed*((speed+2)/4);
		}
		
		return decelDist;
		
	}
	
	//in use
	public void updateSpeedRed(double roadLength, Lane l) {
		
		double distBetween = roadLength - headPos;
		
		double decelDist = decelDistToStop(speed);
		double decelDist2 = decelDistToStop(speed+1);
		
		if(distBetween == 1 && speed == 0) {
			accelerate(l);
		} else if(distBetween > decelDist2) {
				accelerate(l);
				//decelerated = false;
		} else if(distBetween >= decelDist) {
			//do nothing
			//decelerated = false;
		} else if(distBetween < decelDist) {
			decelerate2();
		} 
		
	}
	
	public void updateSpeed2(Car inFront, Lane l) {
		
		double marker = inFront.tailPos() - 2*inFront.speed();
				
		updateSpeedRed(marker - 2, l);
		
//		if(inFront.speed() == 0) {
//			updateSpeedRed(inFront.tailPos() - 1);
//		} else {
//			
//			double distBetween = inFront.tailPos() - headPos - inFront.speed();
//			int db = (int) (distBetween/speed);
//			
//			double speedDiff = speed - inFront.speed();
//			
//			if(db < 2) {
//				decelerate();
//				
//				System.out.println("1 happened");
//				
//			} else if(db == 2) {
//				
//				if(speedDiff < 0) {
//					accelerate();
//					
//					System.out.println("2 happened");
//					
//				} else if(speedDiff == 0) {
//					//do nothing
//					
//					System.out.println("3 happened");
//					
//				} else if(speedDiff > 0) {
//					decelerate();
//					System.out.println("4 happened");
//				}
//				
//			} else if(db > 2) {
//				
//				if(speedDiff <= 0) {
//					accelerate();
//					System.out.println("accelerated");
//				} else {
//					if(distBetween > (2*inFront.speed() + distGain(inFront) + 2*(speed + 1))) {
//						accelerate();
//						System.out.println("5 happened");
//					} else if(distBetween > (2*inFront.speed() + distGain(inFront) + speed + 1)) {
//						//do nothing
//						System.out.println("6 happened");
//					} else if(distBetween > (2*inFront.speed() + distGain(inFront))) {
//						decelerate();
//						System.out.println("7 happened");
//					} else {
//						decelerate();
//						System.out.println("8 happened");
//					}
//				}
//				
//			} // end if-else 2
//			
//		} // end if-else 1
		
	}
	

	
	public String toString() {
		
		String toReturn = "";
		
		toReturn += ("Car's headPos is " + headPos + "\n");
		toReturn += ("Car's tailPos is " + tailPos + "\n");
		
		return toReturn;
		
	}
	
	
	public static void main(String[] args) {
		
		Car c = new Car();
		Car c2 = new Car();
		c2.speed = 11;
		
		System.out.println(c2.maxDecel3Dist(c));
		System.out.println(c2.maxDecel2Dist(c));
		System.out.println(c2.maxDecelDist(c2.speed, c));
		
	}
}
