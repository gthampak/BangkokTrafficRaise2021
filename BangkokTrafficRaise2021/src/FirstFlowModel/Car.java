package FirstFlowModel;

public class Car {

	private boolean moving;
	private boolean iterated;
	
	public Car() {
		moving = true;
		iterated = false;
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
	
}
