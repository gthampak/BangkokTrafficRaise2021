package Model2;

public class Lane {

	private static final int carLength = 5;
	
	private final int from;
	private final int to;
	private final double length; //length of road in meters
	
	private Car[] cars;
	
	//private final int numLanes;

	    /**
	     * Initializes a directed edge from vertex {@code v} to vertex {@code w} with
	     * the given {@code weight}.
	     * @param from the tail vertex
	     * @param to the head vertex
	     * @param length length of the road
	     * @throws IllegalArgumentException if either {@code v} or {@code w}
	     *    is a negative integer
	     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
	     */
	public Lane(int from, int to, double length) {
		if (from < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
		if (to < 0) throw new IllegalArgumentException("Vertex names must be nonnegative integers");
		if (length < 0) throw new IllegalArgumentException("Road lengths must be nonnegative integers");
		this.from = from;
		this.to = to;
		this.length = length;
		
		int maxNumCars = (int) (length/(carLength + 1));
		this.cars = new Car[maxNumCars];
	    }

    /**
     * Returns the tail vertex of the directed edge.
     * @return the tail vertex of the directed edge
     */
	public int from() {
		return from;
	}
	
	/**
     * Returns the head vertex of the directed edge.
     * @return the head vertex of the directed edge
     */
	public int to() {
		return to;
	}

	/**
     * Returns the length of the road.
     * @return the length of the road
     */
    public double length() {
        return length;
    }
    
    /**
     * Returns car slots on the road.
     * @return array of car slots
     */
    public Car[] cars() {
    	return cars;
    }

    /**
     * Set car array
     * @param index which index of cars car[] to set
     * @param car car to put at that index
     */
    public void setCar(int index, Car car) {
    	if(index < 0 || index >= cars.length) {
    		throw new IllegalArgumentException("Road is too short.");
    	} else {
    		cars[index] = car;
    	}
    }
    
    public String printRoad() {
    	String toReturn = from + "";

    	for(int i = 0; i < cars.length; i++) {
    		if(cars[i] == null) {
    			toReturn += "-";
    		} else {
    			toReturn += "x";;
    		}
    	}
    	
    	toReturn += to;
    	
    	return toReturn;
    }
    
//    public void iterate1() {
//    	
//    	
//    	for(int i = 0; i < cars.length; i++) {
//    		if(i == 0 && cars[i] != null && cars[i].moving) {
//    			
//    		}
//    	}
//    }
    
    /**
     * Returns a string representation of the directed edge.
     * @return a string representation of the directed edge
     */
    public String toString() {
        return from + "->" + to + " " + String.format("%5.5f", length);
    }

    /**
     * Unit tests the {@code DirectedEdge} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Lane r = new Lane(0, 1, 600);
        System.out.println(r);
        r.cars[3] = new Car();
        System.out.println(r.printRoad());
    }
}
