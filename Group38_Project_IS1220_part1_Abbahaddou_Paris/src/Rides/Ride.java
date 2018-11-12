package Rides;

import GPS.GPScoordinates;

//Element interface
public interface Ride {
	
	public void accept(CostVisitor costVisitor);
	
}
