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
	 * Test: for Road Network toString
	 */
	public void simpleTest1() {
		RoadNetwork RN = new RoadNetwork(2);
    	RN.addRoad(0, 1, 60);
    	RN.roads()[0].get(0).insertCar(new Car());
    	System.out.println(RN);
	}
	
	/**
	 * Test: for iterate1() in RoadNetwork Class
	 */
	public void simpleTest2() {
		RoadNetwork RN = new RoadNetwork(2);
    	RN.addRoad(0, 1, 18);
    	RN.addRoad(1, 0, 18);
    	RN.roads()[0].get(0).insertCar(new Car());
    	System.out.println(RN);
    	
    	userInteract1(RN);
	}
	
	/**
	 * Test: Checking for road order in adjacency list
	 */
	public void simpleTest3() {
		RoadNetwork RN = new RoadNetwork(6);
    	RN.addRoad(0, 1, 18);
    	RN.addRoad(0, 2, 18);
    	RN.addRoad(0, 5, 18);
    	RN.addRoad(0, 3, 18);
    	RN.addRoad(0, 4, 18);
    	System.out.println(RN);
	}
	
	public void userInteract1(RoadNetwork RN) {
		
		System.out.println("How many times do you want to iterate the system? (Enter integer)");
		String input = sc.nextLine();
		
		while(!input.equals("exit")) {
			int numIterate = Integer.valueOf(input);
			for(int i = 0; i < numIterate; i++) {
				RN.iterate();
				pause();
				System.out.println(RN);
			}
			
			System.out.println("How many times do you want to iterate the system? (Enter integer)");
			input = sc.nextLine();
			
		}
	}
	
	public void userTest1() {
		RoadNetwork RN = new RoadNetwork(4);
		RN.addRoad(0, 1, 60);
		RN.addRoad(1, 2, 60);
		RN.addRoad(2, 3, 60);
		RN.addRoad(3, 0, 60);
		RN.roads()[0].get(0).insertCar(new Car());
		System.out.println(RN);
		
		userInteract1(RN);
	}
	
	public void userTest2() {
		RoadNetwork RN = new RoadNetwork(4);
		RN.addRoad(0, 1, 60);
		RN.addRoad(1, 2, 60);
		RN.addRoad(2, 3, 60);
		RN.addRoad(3, 0, 60);
		RN.roads()[0].get(0).insertCar(new Car());
		System.out.println(RN);
		
		userInteract1(RN);
	}
	
	public void userTest3() {
		RoadNetwork RN = new RoadNetwork(4);
		RN.addRoad(0, 1, 60);
		RN.addRoad(1, 2, 60);
		RN.addRoad(2, 3, 60);
		RN.addRoad(3, 0, 60);
		RN.addRoad(1, 0, 60);
		RN.addRoad(2, 1, 60);
		RN.addRoad(3, 2, 60);
		RN.addRoad(0, 3, 60);
		RN.roads()[0].get(0).insertCar(new Car());
		System.out.println(RN);
		
		userInteract1(RN);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.userTest3();
	}
	
}
