package Cars;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;

public class Standard extends Car{
	static int StandardNumber = 0;
	private GPScoordinates carPosition;
	private final int numberFreeSeats = 4;
	// no carID as attribut because we know that the carID is StandardN
	
	/**
	 * @param carPosition Constuctor
	 */
	public Standard(GPScoordinates carPosition) {
		super(carPosition, "Standard" + Integer.toString(StandardNumber+1));
		StandardNumber++;
		
	}
	
	
}
