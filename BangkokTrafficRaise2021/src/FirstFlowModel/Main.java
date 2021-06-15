package FirstFlowModel;

import java.util.Scanner;

public class Main {

	Scanner sc = new Scanner(System.in);
	
	public Main() {
		
	}
	
	public static void pause() {
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
	}
	
	public void example1() {
		RoadNetwork RN1 = new RoadNetwork(2);
    	RN1.addRoad(0, 1, 60);
    	RN1.roads()[0].get(0).setCar(0, new Car());
    	System.out.println(RN1);
	}
	
	public void userInteract1(RoadNetwork RN) {
		
		System.out.println("How many times do you want to iterate the system? (Enter integer)");
		String input = sc.nextLine();
		
		while(!input.equals("exit")) {
			int numIterate = Integer.valueOf(input);
			for(int i = 0; i < numIterate; i++) {
				RN.iterate1();
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
		RN.roads()[0].get(0).setCar(0, new Car());
		System.out.println(RN);
		
		userInteract1(RN);
	}
	
	public void userTest2() {
		RoadNetwork RN = new RoadNetwork(4);
		RN.addRoad(0, 1, 60);
		RN.addRoad(1, 2, 60);
		RN.addRoad(2, 3, 60);
		RN.addRoad(3, 0, 60);
		RN.roads()[0].get(0).setCar(0, new Car());
		System.out.println(RN);
		
		userInteract1(RN);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.userTest2();
	}
	
}
