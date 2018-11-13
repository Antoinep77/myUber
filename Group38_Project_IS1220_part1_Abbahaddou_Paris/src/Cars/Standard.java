package Cars;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;

public class Standard extends Car{
	static int StandardNumber = 0;
	private GPScoordinates carPosition;
	private int NumberFreeSeats = 4;
	// no carID as attribut because we know that the carID is StandardN
	public Standard(GPScoordinates carPosition, int numberFreeSeats) {
		super(carPosition, numberFreeSeats, "Standard" + Integer.toString(StandardNumber+1));
		StandardNumber++;
		
	}
	
	
}
