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

	public static void main(String[] args) {
		Main main = new Main();
		//main.userTest();
	}
	
}
