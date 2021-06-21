package Model2;

import java.util.Scanner;

public class Main {

	Scanner sc = new Scanner(System.in);
	
	/**
	 * constructor
	 */
	public Main() {
		
	}
	
	/**
	 * 0.5 second pause
	 */
	public static void pause() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
	}
	
	/**
	 * Simple user interaction method
	 * Asks user how many iterations of RoadNetwork to run each time
	 * 
	 * @param A RoadNetwork for user to interact with
	 */
	public void userInteract1(RoadNetwork RN) {
		
		System.out.println(RN);
		
		System.out.println("How many times do you want to iterate the system? (Enter integer)");
		String input = sc.nextLine();
		
		while(!input.equals("exit")) {
			int numIterate = Integer.valueOf(input);
			
			for(int i = 0; i < numIterate; i++) {
				RN.iterate();
				pause();
				System.out.println(RN);
				
//				System.out.println(RN.roads()[0].get(0).lanes()[0].printCars());
//				System.out.println(RN.roads()[1].get(0).lanes()[0].printCars());
			}
			
			System.out.println("How many times do you want to iterate the system? (Enter integer)");
			input = sc.nextLine();
			
		} //end while loop
	}

	public void userInteract2(RoadNetwork RN) {
		
		//String input = sc.nextLine();
		
		System.out.println(RN);
		
		System.out.println("Main Menu: Enter one of the following integers:");
		System.out.println("(0) Exit Program");
		System.out.println("(1) View RoadNetwork Information");
		System.out.println("(2) Alter RoadNetwork");
		System.out.println("(3) Add Car");
		
		System.out.print("Enter integer here: ");
		
		int input = sc.nextInt();
		
		while(input != 0) {
			if(input == 1) {
				System.out.println("RoadNetwork Information: Enter one of the following integers:");
				System.out.println("(0) Exit Program");
				System.out.println("(1) Print RoadNetwork");
				System.out.println("(2) Display Number of Intersections (Vertices)");
				System.out.println("(3) Display Number of Roads");
				System.out.println("(4) Display Number of Iterations Ran");
				System.out.println("(5) View Road Information");
				System.out.println("(6) Back to Main Menu");
				
				System.out.print("Enter integer here: ");
				
				input = sc.nextInt();
				if(input == 1) { //Print RoadNetwork
					System.out.println(RN);
				} else if(input == 2) { //RoadNetwork Information
					System.out.println(RN.V());
				} else if(input == 3) {
					System.out.println(RN.R());
				} else if(input == 4) {
					System.out.println(RN.iterations());
				} else if(input == 5) {
					//Road Information
					
					System.out.println("Which road would you like to access? (Enter starting vertex, number from 0 to " + RN.V());
					System.out.print("Enter integer here: ");
					
					int roadFrom = sc.nextInt();
					
					System.out.println("There are " + RN.roadsFrom(roadFrom).size() + " from vertex " + roadFrom);
					System.out.println("Which road would you like to access? (Enter integer)");
					
					for(int i = 0; i < RN.roadsFrom(roadFrom).size(); i ++) {
						System.out.println("(" + i + ") " + RN.roadsFrom(roadFrom).get(i));
					}
					
					System.out.print("Enter integer here: ");
					
					int roadNum = sc.nextInt();
					Road r = RN.roadsFrom(roadFrom).get(roadNum);
					
					System.out.println(r + " Road Information: Enter one of the following integers:");
					System.out.println("(0) Exit Program");
					System.out.println("(1) Display Starting Vertex");
					System.out.println("(2) Display Destination Vertex");
					System.out.println("(3) Display Length of Road");
					System.out.println("(4) Display Number of Lanes");
					System.out.println("(5) Display Number of Cars");
					System.out.println("(6) Display Lane Information");
					System.out.println("(7) Display Road");
					System.out.println("(8) Back to Main Menu");
					
					System.out.print("Enter integer here: ");
					
					input = sc.nextInt();
					
					if(input == 1) {
						System.out.println(r.from());
					} else if(input == 2) {
						System.out.println(r.to());
					} else if(input == 3) {
						System.out.println(r.length());
					} else if(input == 4) {
						System.out.println(r.numLanes());
					} else if(input == 5) {
						System.out.println(r.numCars());
					} else if(input == 6) {
						System.out.println("Selected road has " + r.numLanes() + " lanes.");
						System.out.println("Which lane would you like to access? (Enter integer)");
						System.out.print("Enter integer here: ");
						
						int laneNum = sc.nextInt();
						
						Lane l = r.lanes()[laneNum];
						
						System.out.println(l + " Lane Information: Enter one of the following integers:");
						System.out.println("(0) Exit Program");
						System.out.println("(1) Display Starting Vertex");
						System.out.println("(2) Display Destination Vertex");
						System.out.println("(3) Display Length of Lane");
						System.out.println("(4) Display Number of Cars");
						System.out.println("(5) Display possible next lanes after crossing destination intersection/vertex");
						System.out.println("(6) Display Traffic Light");
						System.out.println("(7) Print Cars List");
						System.out.println("(8) Display Car Information");
						System.out.println("(9) Display Lane");
						System.out.println("(10) Back to Main Menu");
						
						System.out.print("Enter integer here: ");
						
						input = sc.nextInt();
						
						if(input == 1) {
							System.out.println(l.from());
						} else if(input == 2) {
							System.out.println(l.to());
						} else if(input == 3) {
							System.out.println(l.length());
						} else if(input == 4) {
							System.out.println(l.cars().size());
						} else if(input == 5) {
							for(int i = 0; i < l.toLanes().size(); i++) {
								System.out.println(l.toLanes().get(i));
							}
						} else if(input == 6) {
							if(l.trafficLight() == 'G') {
								System.out.println("Green");
							} else if(l.trafficLight() == 'R') {
								System.out.println("Red");
							}
						} else if(input == 7) {
							System.out.println(l.printCars());
						} else if(input == 8) {
							System.out.println(l.printCars());
							System.out.println("There are " + l.cars().size() + " cars in this lane.");
							System.out.println("Which car would you like to access? (Enter index/integer)");
							System.out.print("Enter integer here: ");
							
							int carIndex = sc.nextInt();
							
							Car c = l.cars().get(carIndex);
							
							System.out.println("Car Information: Enter one of the following integers:");
							System.out.println("(0) Exit Program");
							System.out.println("(1) Display Car Length");
							System.out.println("(2) Display Car Head Position");
							System.out.println("(3) Display Car Tail Position");
							System.out.println("(4) Display Car Speed");
							System.out.println("(5) Display Car Acceleration");
							System.out.println("(6) Display All Above");
							System.out.println("(7) Back to Main Menu");
							
							input = sc.nextInt();
							
							if(input == 1) {
								System.out.println("Car length is " + c.length() + " meters");
							} else if(input == 2) {
								System.out.println("Car is " + c.headPos() + " meters from vertex " + l.from() + " on " + l);
							} else if(input == 3) {
								System.out.println("Car is " + c.tailPos() + " meters from vertex " + l.from() + " on " + l);
							} else if(input == 4) {
								System.out.println("Car speed is " + c.speed() + " meters per second");
							} else if(input == 5) {
								System.out.println("Car speed is " + c.acceleration() + " meters per second");
							} else if(input == 6) {
								System.out.println("Car length is " + c.length() + " meters");
								System.out.println("Car is " + c.headPos() + " meters from vertex " + l.from() + " on " + l);
								System.out.println("Car is " + c.tailPos() + " meters from vertex " + l.from() + " on " + l);
								System.out.println("Car speed is " + c.speed() + " meters per second");
								System.out.println("Car speed is " + c.acceleration() + " meters per second");
							}
							
						} else if(input == 9) {
							System.out.println(l.printLane());
						}
						
					} else if(input == 7) {
						System.out.println(r.printRoad());
					}
					
				} else if(input == 6) {
					//Return to Main Menu
				}
				
			} else if(input == 2) { //Alter RoadNetwork
				System.out.println("(0) Exit Program");
				System.out.println("(1) Add Road");
				System.out.println("(2) ");
			} else if(input == 3) { //Add Car
				//Add car
			}
		}
		
	}
	
	public void userTest1() {
		RoadNetwork RN = new RoadNetwork(2);
		
		Road r1 = new Road(0, 1, 40, 2);
		Road r2 = new Road(1, 0, 40, 2);
		
		RN.addRoad(r1);
		RN.addRoad(r2);
		
		r1.lanes()[0].addToLanes(r2.lanes()[0]);
		r1.lanes()[1].addToLanes(r2.lanes()[1]);
		
		r2.lanes()[0].addToLanes(r1.lanes()[0]);
		r2.lanes()[1].addToLanes(r1.lanes()[1]);
		
		r1.lanes()[0].insertCar(new Car());
		r1.lanes()[1].insertCar(new Car());
		
		r1.lanes()[0].setTrafficLight('G');
		
		userInteract1(RN);
		
	}
	
	public void userTest2() {
		
		RoadNetwork RN = new RoadNetwork(2);
		
		Road r1 = new Road(0, 1, 40, 1);
		Road r2 = new Road(1, 0, 40, 1);
		
		RN.addRoad(r1);
		RN.addRoad(r2);
		
		r1.lanes()[0].addToLanes(r2.lanes()[0]);
		r2.lanes()[0].addToLanes(r1.lanes()[0]);
		
		r1.lanes()[0].insertCar(new Car(10, 'S'));
		r1.lanes()[0].insertCar(new Car(30, 'F'));
		
		r1.lanes()[0].setTrafficLight('G');
		
		System.out.println(r1.lanes()[0].printCars());
		
		userInteract1(RN);
		
	}
	
	public void userTest3() {
		RoadNetwork RN = new RoadNetwork(2);
		
		Road r1 = new Road(0, 1, 500, 2);
		Road r2 = new Road(1, 0, 500, 2);
		
		RN.addRoad(r1);
		RN.addRoad(r2);
		
		r1.lanes()[0].addToLanes(r2.lanes()[0]);
		r2.lanes()[0].addToLanes(r1.lanes()[0]);
		r1.lanes()[1].addToLanes(r2.lanes()[1]);
		r2.lanes()[1].addToLanes(r1.lanes()[1]);
		
		for(int i = 0; i < 500; i+=50) {
			r1.lanes()[0].insertCar(new Car(i));
			r1.lanes()[1].insertCar(new Car(i));
			
			r2.lanes()[0].insertCar(new Car(i));
			r2.lanes()[1].insertCar(new Car(i));
		}
		
		r1.lanes()[0].setTrafficLight('G');
		
		System.out.println(r1.lanes()[0].printCars());
		
		userInteract1(RN);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.userTest3();
	}
	
}
