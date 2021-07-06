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
	
	public String indent() {
		 return "	";
	}
	
	public void skipLine() {
		System.out.println("");
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
		
		boolean exit = false;
		
		System.out.println(RN);
		
		System.out.println("Main Menu: Enter one of the following integers:");
		System.out.println("(0) Exit Program");
		System.out.println("(1) Iterate");
		System.out.println("(2) View RoadNetwork Information");
		System.out.println("(3) Alter RoadNetwork");
		System.out.println("(4) Add Car");
		
		skipLine();
		
		System.out.print("Enter integer here: ");
		
		int input = sc.nextInt();
		
		if(input == 0) {
			exit = true;
		}
		
		skipLine();
		
		while(exit == false) {
			if(input == 0) {
				exit = true;
			} else if(input == 1) {
				
				System.out.println(RN);
				
				skipLine();
				
				System.out.println("How many times would you like to iterate the RoadNetwork?");
				System.out.print("Enter integer here: ");
				
				int numIterate = sc.nextInt();
				
				skipLine();
				
				for(int i = 0; i < numIterate; i++) {
					RN.iterate();
					pause();
					System.out.println(RN);
				}
				
			} else if(input == 2) {
				System.out.println("RoadNetwork Information: Enter one of the following integers:");
				System.out.println("(0) Exit Program");
				System.out.println("(1) Print RoadNetwork");
				System.out.println("(2) Display Number of Intersections (Vertices)");
				System.out.println("(3) Display Number of Roads");
				System.out.println("(4) Display Number of Iterations Ran");
				System.out.println("(5) View Road Information");
				System.out.println("(6) Back to Main Menu");
				
				skipLine();
				
				System.out.print("Enter integer here: ");
				
				input = sc.nextInt();
				skipLine();
				
				if(input == 0) {
					exit = true;
				} else if(input == 1) { //Print RoadNetwork
					System.out.println(RN);
				} else if(input == 2) {
					System.out.println("Number of intersections in RoadNetwork is " + RN.V());
				} else if(input == 3) {
					System.out.println("Number of roads in RoadNetwork is " + RN.R());
				} else if(input == 4) {
					System.out.println("RoadNetwork has gone through " + RN.iterations() + " iterations");
				} else if(input == 5) {
					//Road Information
					
					System.out.println("Which road would you like to access? (Enter its starting vertex, number from 0 to " + (RN.V()-1) + ")");
					System.out.print("Enter integer here: ");
					
					int roadFrom = sc.nextInt();
					skipLine();
					
					System.out.println("There are " + RN.roadsFrom(roadFrom).size() + " road(s) from vertex " + roadFrom);
					System.out.println("Which road would you like to access? (Enter integer)");
					
					skipLine();
					
					for(int i = 0; i < RN.roadsFrom(roadFrom).size(); i ++) {
						System.out.println("(" + i + ") " + RN.roadsFrom(roadFrom).get(i));
					}
					
					skipLine();
					
					System.out.print("Enter integer here: ");
					
					int roadNum = sc.nextInt();
					skipLine();
					
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
					
					skipLine();
					
					System.out.print("Enter integer here: ");
					
					input = sc.nextInt();
					skipLine();
					
					if(input == 0) {
						exit = true;
					} else if(input == 1) {
						System.out.println(r + "'s starting vertex is " + r.from());
					} else if(input == 2) {
						System.out.println(r + "'s destination vertex is " + r.to());
					} else if(input == 3) {
						System.out.println(r + " is " + r.length() + "meters long");
					} else if(input == 4) {
						System.out.println(r + " has " + r.numLanes() + " lanes");
					} else if(input == 5) {
						System.out.println(r + " has " + r.numCars() + " cars on it");
					} else if(input == 6) {
						System.out.println("Selected road has " + r.numLanes() + " lanes.");
						System.out.println("Which lane would you like to access? (Enter integer)");
						
						skipLine();
						
						System.out.print("Enter integer here: ");
						
						int laneNum = sc.nextInt();
						skipLine();
						
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
						
						skipLine();
						
						System.out.print("Enter integer here: ");
						
						input = sc.nextInt();
						skipLine();
						
						if(input == 0) {
							exit = true;
						} else if(input == 1) {
							System.out.println(l + "'s starting vertex is " + l.from());
						} else if(input == 2) {
							System.out.println(l + "'s destination vertex is " + l.from());
						} else if(input == 3) {
							System.out.println(l + " is " + l.length() + "meters long");
						} else if(input == 4) {
							System.out.println(l + " has " + l.cars().size() + " cars in it");
						} else if(input == 5) {
							
							System.out.println("Cars from this lane can go to the following lanes, after crossing intersection/vertex " +l.from()) ;
							
							skipLine();
							
							for(int i = 0; i < l.toLanes().size(); i++) {
								System.out.println(l.toLanes().get(i));
							}
						} else if(input == 6) {
							if(l.trafficLight() == 'G') {
								System.out.println("Light on " + l + " is Green");
							} else if(l.trafficLight() == 'R') {
								System.out.println("Light on " + l + " is Red");
							}
						} else if(input == 7) {
							System.out.println(l.printCars());
						} else if(input == 8) {
							System.out.println(l.printCars());
							System.out.println("There are " + l.cars().size() + " cars in this lane.");
							System.out.println("Which car would you like to access? (Enter index/integer)");
							
							skipLine();
							
							System.out.print("Enter integer here: ");
							
							int carIndex = sc.nextInt();
							skipLine();
							
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
							
							skipLine();
							
							System.out.print("Enter integer here: ");
							
							input = sc.nextInt();
							skipLine();
							
							if(input == 0) {
								exit = true;
							} else if(input == 1) {
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
				
			} else if(input == 3) { //Alter RoadNetwork
				System.out.println("(0) Exit Program");
				System.out.println("(1) Add Road");
				System.out.println("(2) Change Traffic Light");
				System.out.println("(3) ");
				
				skipLine();
				
				System.out.print("Enter integer here: ");
				
				input = sc.nextInt();
				skipLine();
				
				if(input == 0) {
					exit = true;
				} else if(input == 1) {
					//add road
				} else if(input == 2) {
					System.out.println("Which road's traffic light would you like to change? (Enter its starting vertex, number from 0 to " + (RN.V()-1) + ")");
					System.out.print("Enter integer here: ");
					
					int roadFrom = sc.nextInt();
					skipLine();
					
					System.out.println("There are " + RN.roadsFrom(roadFrom).size() + " road(s) from vertex " + roadFrom);
					System.out.println("Which road's traffic light would you like to change? (Enter integer)");
					
					skipLine();
					
					for(int i = 0; i < RN.roadsFrom(roadFrom).size(); i ++) {
						System.out.println("(" + i + ") " + RN.roadsFrom(roadFrom).get(i));
					}
					
					skipLine();
					
					System.out.print("Enter integer here: ");
					
					int roadNum = sc.nextInt();
					skipLine();
					
					Road r = RN.roadsFrom(roadFrom).get(roadNum);
					
					System.out.println("This road has " + r.lanes().length + " lanes below. Which lane's traffic light would you like to change? (Enter Integer)");
					
					skipLine();
					
					for(int i = 0; i < r.lanes().length; i ++) {
						System.out.println("(" + i + ") " + r.lanes()[i]);
					}
					
					skipLine();
					
					System.out.print("Enter integer here: ");
					
					int laneNum = sc.nextInt();
					skipLine();
					
					Lane l = r.lanes()[laneNum];
					String signal;
					if(l.trafficLight() == 'G') {
						signal = "Green";
					} else {
						signal = "Red";
					}
					
					System.out.println("Traffic Light on this lane is now: " + signal);
					System.out.println("What would you like to change it into? Type G for Green and R for Red.");
					
					System.out.print("Enter character here (in caps): ");
					
					String light = sc.nextLine();
					char newLight = light.charAt(0);
					
					l.setTrafficLight(newLight);
					
					System.out.println("Selected lane's new light changed to: " + l.trafficLight());
					
				}
				
			} else if(input == 4) { //Add Car
				//Add car
			}
		
			skipLine(); 
			skipLine();
			
			System.out.println("Main Menu: Enter one of the following integers:");
			System.out.println("(0) Exit Program");
			System.out.println("(1) Iterate");
			System.out.println("(2) View RoadNetwork Information");
			System.out.println("(3) Alter RoadNetwork");
			System.out.println("(4) Add Car");
			
			skipLine();
			
			System.out.print("Enter integer here: ");
			
			input = sc.nextInt();
			skipLine();
			
		} //end while loop
		
	} //end method
	
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
	
	public void userInteract2Test() {
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
		
		userInteract2(RN);
	}
	
	public RoadNetwork caseStudy1() {
		RoadNetwork RN = new RoadNetwork(16);
		
		//road start at 0
		
		Road zero1 = new Road(0, 1, 200, 4); RN.addRoad(zero1);
		Road zero2 = new Road(0, 2, 200, 4); RN.addRoad(zero2);
		Road zero6 = new Road(0, 6, 200, 4); RN.addRoad(zero6);
		Road zero8 = new Road(0, 8, 200, 4); RN.addRoad(zero8);
		
		Road six0 = new Road(6, 0, 200, 4); RN.addRoad(six0);
		Road eight0 = new Road(8, 0, 200, 4); RN.addRoad(eight0);
		
		Road one0 = new Road(1, 0, 200, 4); RN.addRoad(one0);
		Road one3 = new Road(1, 3, 200, 4); RN.addRoad(one3);
		Road one7 = new Road(1, 7, 200, 4); RN.addRoad(one7);
		Road one9 = new Road(1, 9, 200, 4); RN.addRoad(one9);
		
		Road seven1 = new Road(7, 1, 200, 4); RN.addRoad(seven1);
		Road nine1 = new Road(9, 1, 200, 4); RN.addRoad(nine1);
		
		Road two0 = new Road(2, 0, 200, 4); RN.addRoad(two0);
		Road two3 = new Road(2, 3, 200, 4); RN.addRoad(two3);
		Road two4 = new Road(2, 4, 200, 4); RN.addRoad(two4);
		Road two10 = new Road(2, 10, 200, 4); RN.addRoad(two10);
		
		Road ten2 = new Road(10, 2, 200, 4); RN.addRoad(ten2);
		
		Road three1 = new Road(3, 1, 200, 4); RN.addRoad(three1);
		Road three2 = new Road(3, 2, 200, 4); RN.addRoad(three2);
		Road three5 = new Road(3, 5, 200, 4); RN.addRoad(three5);
		Road three11 = new Road(3, 11, 200, 4); RN.addRoad(three11);
		
		Road eleven3 = new Road(11, 3, 200, 4); RN.addRoad(eleven3);
		
		Road four2 = new Road(4, 2, 200, 4); RN.addRoad(four2);
		Road four5 = new Road(4, 5, 200, 4); RN.addRoad(four5);
		Road four12 = new Road(4, 12, 200, 4); RN.addRoad(four12);
		Road four14 = new Road(4, 14, 200, 4); RN.addRoad(four14);
		
		Road twelve4 = new Road(12, 4, 200, 4); RN.addRoad(twelve4);
		Road fourteen4 = new Road(14, 4, 200, 4); RN.addRoad(fourteen4);
		
		Road five3 = new Road(5, 3, 200, 4); RN.addRoad(five3);
		Road five4 = new Road(5, 4, 200, 4); RN.addRoad(five4);
		Road five13 = new Road(5, 13, 200, 4); RN.addRoad(five13);
		Road five15 = new Road(5, 15, 200, 4); RN.addRoad(five15);
		
		Road thirteen5 = new Road(13, 5, 200, 4); RN.addRoad(thirteen5);
		Road fifteen5 = new Road(15, 5, 200, 4); RN.addRoad(fifteen5);
		
		
		zero1.lanes()[0].addToLanes(one7.lanes()[0]);
		zero1.lanes()[1].addToLanes(one9.lanes()[1]);
		zero1.lanes()[2].addToLanes(one9.lanes()[2]);
		zero1.lanes()[3].addToLanes(one3.lanes()[3]);
		zero2.lanes()[0].addToLanes(two3.lanes()[0]);
		zero2.lanes()[1].addToLanes(two4.lanes()[1]);
		zero2.lanes()[2].addToLanes(two4.lanes()[2]);
		zero2.lanes()[3].addToLanes(two10.lanes()[3]);
		//zero6 end
		six0.lanes()[0].addToLanes(zero1.lanes()[0]);
		six0.lanes()[1].addToLanes(zero2.lanes()[1]);
		six0.lanes()[2].addToLanes(zero2.lanes()[2]);
		six0.lanes()[3].addToLanes(zero8.lanes()[3]);
		//zero8 end
		eight0.lanes()[0].addToLanes(zero6.lanes()[0]);
		eight0.lanes()[1].addToLanes(zero1.lanes()[1]);
		eight0.lanes()[2].addToLanes(zero1.lanes()[2]);
		eight0.lanes()[3].addToLanes(zero2.lanes()[3]);
		
		one0.lanes()[0].addToLanes(zero2.lanes()[0]);
		one0.lanes()[1].addToLanes(zero8.lanes()[1]);
		one0.lanes()[2].addToLanes(zero8.lanes()[2]);
		one0.lanes()[3].addToLanes(zero6.lanes()[3]);
		one3.lanes()[0].addToLanes(three11.lanes()[0]);
		one3.lanes()[1].addToLanes(three5.lanes()[1]);
		one3.lanes()[2].addToLanes(three5.lanes()[2]);
		one3.lanes()[3].addToLanes(three2.lanes()[3]);
		//one7 end
		seven1.lanes()[0].addToLanes(one9.lanes()[0]);
		seven1.lanes()[1].addToLanes(one3.lanes()[1]);
		seven1.lanes()[2].addToLanes(one3.lanes()[2]);
		seven1.lanes()[3].addToLanes(one0.lanes()[3]);
		//one9 end
		nine1.lanes()[0].addToLanes(one3.lanes()[0]);
		nine1.lanes()[1].addToLanes(one0.lanes()[1]);
		nine1.lanes()[2].addToLanes(one0.lanes()[2]);
		nine1.lanes()[3].addToLanes(one7.lanes()[3]);
		
		two0.lanes()[0].addToLanes(zero8.lanes()[0]);
		two0.lanes()[1].addToLanes(zero6.lanes()[1]);
		two0.lanes()[2].addToLanes(zero6.lanes()[2]);
		two0.lanes()[3].addToLanes(zero1.lanes()[3]);
		two3.lanes()[0].addToLanes(three1.lanes()[0]);
		two3.lanes()[1].addToLanes(three11.lanes()[1]);
		two3.lanes()[2].addToLanes(three11.lanes()[2]);
		two3.lanes()[3].addToLanes(three5.lanes()[3]);
		two4.lanes()[0].addToLanes(four5.lanes()[0]);
		two4.lanes()[1].addToLanes(four14.lanes()[1]);
		two4.lanes()[2].addToLanes(four14.lanes()[2]);
		two4.lanes()[3].addToLanes(four12.lanes()[3]);
		//two10 end
		ten2.lanes()[0].addToLanes(two0.lanes()[0]);
		ten2.lanes()[1].addToLanes(two3.lanes()[1]);
		ten2.lanes()[2].addToLanes(two3.lanes()[2]);
		ten2.lanes()[3].addToLanes(two4.lanes()[3]);
		
		three1.lanes()[0].addToLanes(one0.lanes()[0]);
		three1.lanes()[1].addToLanes(one7.lanes()[1]);
		three1.lanes()[2].addToLanes(one7.lanes()[2]);
		three1.lanes()[3].addToLanes(one9.lanes()[3]);
		three2.lanes()[0].addToLanes(two4.lanes()[0]);
		three2.lanes()[1].addToLanes(two10.lanes()[1]);
		three2.lanes()[2].addToLanes(two10.lanes()[2]);
		three2.lanes()[3].addToLanes(two0.lanes()[3]);
		three5.lanes()[0].addToLanes(five13.lanes()[0]);
		three5.lanes()[1].addToLanes(five15.lanes()[1]);
		three5.lanes()[2].addToLanes(five15.lanes()[2]);
		three5.lanes()[3].addToLanes(five4.lanes()[3]);
		//three11 end
		eleven3.lanes()[0].addToLanes(three5.lanes()[0]);
		eleven3.lanes()[1].addToLanes(three2.lanes()[1]);
		eleven3.lanes()[2].addToLanes(three2.lanes()[2]);
		eleven3.lanes()[3].addToLanes(three1.lanes()[3]);
		
		four2.lanes()[0].addToLanes(two10.lanes()[0]);
		four2.lanes()[1].addToLanes(two0.lanes()[1]);
		four2.lanes()[2].addToLanes(two0.lanes()[2]);
		four2.lanes()[3].addToLanes(two3.lanes()[3]);
		four5.lanes()[0].addToLanes(five3.lanes()[0]);
		four5.lanes()[1].addToLanes(five13.lanes()[1]);
		four5.lanes()[2].addToLanes(five13.lanes()[2]);
		four5.lanes()[3].addToLanes(five15.lanes()[3]);
		//four12 end
		//four14 end
		twelve4.lanes()[0].addToLanes(four2.lanes()[0]);
		twelve4.lanes()[1].addToLanes(four5.lanes()[1]);
		twelve4.lanes()[2].addToLanes(four5.lanes()[2]);
		twelve4.lanes()[3].addToLanes(four14.lanes()[3]);
		fourteen4.lanes()[0].addToLanes(four12.lanes()[0]);
		fourteen4.lanes()[1].addToLanes(four2.lanes()[1]);
		fourteen4.lanes()[2].addToLanes(four2.lanes()[2]);
		fourteen4.lanes()[3].addToLanes(four5.lanes()[3]);
		
		five3.lanes()[0].addToLanes(three2.lanes()[0]);
		five3.lanes()[1].addToLanes(three1.lanes()[1]);
		five3.lanes()[2].addToLanes(three1.lanes()[2]);
		five3.lanes()[3].addToLanes(three11.lanes()[3]);
		five4.lanes()[0].addToLanes(four14.lanes()[0]);
		five4.lanes()[1].addToLanes(four12.lanes()[1]);
		five4.lanes()[2].addToLanes(four12.lanes()[2]);
		five4.lanes()[3].addToLanes(four2.lanes()[3]);
		//five13 end
		//five15 end
		thirteen5.lanes()[0].addToLanes(five15.lanes()[0]);
		thirteen5.lanes()[1].addToLanes(five4.lanes()[1]);
		thirteen5.lanes()[2].addToLanes(five4.lanes()[2]);
		thirteen5.lanes()[3].addToLanes(five3.lanes()[3]);
		fifteen5.lanes()[0].addToLanes(five4.lanes()[0]);
		fifteen5.lanes()[1].addToLanes(five3.lanes()[1]);
		fifteen5.lanes()[2].addToLanes(five3.lanes()[2]);
		fifteen5.lanes()[3].addToLanes(five13.lanes()[3]);
		
		return RN;
	}
	
	public void userInteractSpeedTest(RoadNetwork RN) {
		
		System.out.println(RN);
		
		System.out.println("How many times do you want to iterate the system? (Enter integer)");
		String input = sc.nextLine();
		
		while(!input.equals("exit")) {
			int numIterate = Integer.valueOf(input);
			
			for(int i = 0; i < numIterate; i++) {
				RN.iterateWSpeed();
				pause();
				System.out.println(RN);
				
//				System.out.println(RN.roads()[0].get(0).lanes()[0].printCars());
//				System.out.println(RN.roads()[1].get(0).lanes()[0].printCars());
			}
			
			System.out.println("How many times do you want to iterate the system? (Enter integer)");
			input = sc.nextLine();
			
		} //end while loop
	}
	
	public void speedTest1() { //passed //one car interact with red light
		RoadNetwork RN = new RoadNetwork(2);
    	
    	Road r = new Road(0, 1, 431, 1);
    	
    	RN.addRoad(r);
    	
    	r.lanes()[0].insertCar(new Car());
    	
    	userInteractSpeedTest(RN);
	}
	
	public void speedTest2() { //passed //two cars interact with red light
		RoadNetwork RN = new RoadNetwork(2);
    	
    	Road r = new Road(0, 1, 200, 1);
    	
    	RN.addRoad(r);
    	
    	r.lanes()[0].insertCar(new Car(30));
    	r.lanes()[0].insertCar(new Car());
    	
    	userInteractSpeedTest(RN);
	}
	
	public static void main(String[] args) {
//		Main main = new Main();
//		System.out.println(main.caseStudy1());
		
		Main main = new Main();
		main.speedTest2();
	}
	
}
