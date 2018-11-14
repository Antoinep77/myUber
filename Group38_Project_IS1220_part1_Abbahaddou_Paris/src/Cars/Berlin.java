package Cars;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;

public class Berlin extends Car{
	static int BerlinNumber = 0;
	private GPScoordinates carPosition;
	private final int numberFreeSeats = 4;
	// no carID as attribut because we know that the carID is BerlinN
	public Berlin(GPScoordinates carPosition) {
		super(carPosition, "Berlin" + Integer.toString(BerlinNumber+1));
		BerlinNumber++;
		
	}
}
