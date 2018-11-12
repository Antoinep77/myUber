package Rides;

//Element interface
public interface Ride {
	
	public void accept(CostVisitor costVisitor);
}
