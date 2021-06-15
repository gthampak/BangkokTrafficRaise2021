package Model2;

public class Car {

	private boolean moving;
	private boolean iterated;
	private int interations;
	
	public Car() {
		moving = true;
		iterated = false;
		interations = 0;
	}
	
	public boolean moving() {
		return moving;
	}
	
	public boolean iterated() {
		return iterated;
	}
	
	public void setIterated(boolean iterated) {
		this.iterated = iterated;
	}
	
	public int iterations() {
		return interations;
	}
	
	public void iterate() {
		interations++;
	}
	
}
