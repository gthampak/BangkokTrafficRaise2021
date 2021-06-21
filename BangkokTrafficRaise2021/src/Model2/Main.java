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
