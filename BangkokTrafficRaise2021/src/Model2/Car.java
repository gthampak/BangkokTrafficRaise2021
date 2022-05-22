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
	private boolean decelerated = false; //boolean for whether car decelerated in the previous iteration (emulating brake lights)
	
	private ArrayList<Road> path; //used to create path of cars
	private int laneChange; //how many lanes car needs to change to be in correct lane
	
	private int nextLane;
	private boolean incomingLC = false; //whether another car needs to/wants to signal lane change to position in front
	
	private char carCode; //for testing purposes
	

	/**
	 * default constructor, car placed at the start of road at the start of simulation
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
	 * default constructor for adding cars in the middle of test simulations
	 * cars only move if iteration values are the same as iteration number
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
	
	/**
	 * constructor for adding cars at different positions on the road.
	 * 
	 * @param tailPos
	 */
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
	
	/**
	 * contructor for adding cars at different part of the road mid-sim (non-zero iteration)
	 * 
	 * @param tailPos
	 * @param iterations
	 */
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
	
	/**
	 * constructor to add car code for better visualization of where specific cars are
	 * (used to test that cars don't unrealistically overtake each other)
	 * 
	 * @param tailPos
	 * @param carCode
	 */
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
	
	/**
	 * accessor method for iterations variable
	 * 
	 * @return
	 */
	public int iterations() {
		return iterations;
	}
	
	/**
	 * once a car moves, need to iterate so it doesn't move again for that iteration
	 * (used for when cars lane-change and shouldn't move again when cars in different lane are iterated through)
	 */
	public void iterate() {
		iterations++;
	}
	
	/**
	 * accessor method for headPos variable
	 * @return
	 */
	public double headPos() {
		return headPos;
	}
	
	/**
	 * mutator method for headPos variable
	 * @param headPos
	 */
	public void setHeadPos(double headPos) {
		this.headPos = headPos;
	}
	
	/**
	 * accessor method for tailPos variable
	 * @return
	 */
	public double tailPos() {
		return tailPos;
	}
	
	/**
	 * mutator method for tailPos variable
	 * @param tailPos
	 */
	public void setTailPos(double tailPos) {
		this.tailPos = tailPos;
	}
	
	/**
	 * accessor method for speed variable
	 * @return
	 */
	public double speed() {
		return speed;
	}
	
	/**
	 * accessor method for acceleration variable
	 * @return
	 */
	public double acceleration() {
		return acceleration;
	}

	/**
	 * accessor method for decelerated variable
	 * @return
	 */
	public boolean decelerated() {
		return decelerated;
	}
	
	/**
	 * accessor method for length variable
	 * @return
	 */
	public double length() {
		return length;
	}
	
	/**
	 * accessor method for nextLane variable
	 * @return
	 */
	public int nextLane() {
		return nextLane;
	}
	
	/**
	 * accessor method for laneChange variable
	 * @return
	 */
	public int laneChange() {
		return laneChange;
	}

	/**
	 * mutator method for setLaneChange variable
	 * 
	 * @param lc
	 */
	public void setLaneChange(int lc) {
		laneChange = lc;
	}
	
	/**
	 * method to indicate a lane change
	 * if moving towards greater lane number, update laneChange required 1 less than before (towards 0)
	 * if moving towards lesser lane number, update lanChange required to 1 greater than before (towards 0)
	 */
	public void laneChanged() {
		if(laneChange > 0) {
			laneChange--;
		} else if(laneChange < 0) {
			laneChange++;
		}
	}
	
	/**
	 * mutator method for incomingLC variable
	 * @param ilc
	 */
	public void setIncomingLC(boolean ilc) {
		incomingLC = ilc;
	}
	
	/**
	 * accessor method for incomingLC variable
	 * @return
	 */
	public boolean incomingLC() {
		return incomingLC;
	}
	
	/**
	 * mutator method for nextLane variable
	 * @param nextLane
	 */
	public void setNextLane(int nextLane) {
		this.nextLane = nextLane;
	}
	
	/**
	 * accessor method for carCode variable
	 * @return
	 */
	public char carCode() {
		return carCode;
	}
	
	/**
	 * increase speed by 1 if the speed doesn't increase past speed limit
	 * emulate pressing on accelerator
	 * @param l
	 */
	public void accelerate(Lane l) {
		if(speed != l.speedLimit()) {
			speed++;
		}
	}
	
	/**
	 * increase speed by 2 if the speed doesn't icnrease past speed limit
	 * @param l
	 */
	public void accelerate2(Lane l) {
		if(speed != l.speedLimit()) {
			speed+=2;
		}
	}
	
	/**
	 * decrease speed by 1 unless car is already stationary
	 * emulate brakes
	 */
	public void decelerate() {
		if(speed == 0) {
			//do nothing
		} else {
			speed--;
		}
		
		//decelerated = true;
	}
	
	/**
	 * decrease speed by 2 or until car is stationary.
	 */
	public void decelerate2() {
		if(speed == 1) {
			speed--;
		} else if(speed == 0){
			
		} else {
			speed -= 2;
		}
		
		//decelerated = true;
	}
	
	/**
	 * decrease speed by 3 or until car is stationary
	 */
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
	
	/**
	 * calculates the time taken in seconds for car to reach car in front.
	 * 
	 * @param inFront, the car considered in front of that car
	 * car inFront could be car directly in front in the same lane,
	 * car with blinkers on in another lane, or car on next road that current car is heading to next.
	 * 
	 * @return calculated time taken
	 */
	public int db(Car inFront) {
		
		double distBetween = inFront.tailPos() - headPos;
		int db = (int) (distBetween/inFront.speed());
		
		return db;
		
	}
	
	/**
	 * calculates the maximum distance required to decelerate and not crash into car in front
	 * while decelerating 1 speed at a time
	 * 
	 * @param spd
	 * @param inFront
	 * @return the distance
	 */
	public double maxDecelDist(double spd, Car inFront) {
		
		double maxDecelDist = 0;
		double speedDiff = spd - inFront.speed();
		
		if(speedDiff > 0) {
			maxDecelDist = speedDiff*spd + 1;
		}
		
		return maxDecelDist;

	}

	
	/**
	 * calculates the maximum distance required to decelerate and not crash into car in front
	 * while decelerating 2 speed at a time
	 * 
	 * @param inFront
	 * @return
	 */
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
