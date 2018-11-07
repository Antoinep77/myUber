package Cars;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;

public class Berlin extends Car{
	static int BerlinNumber = 0;
	private Driver driver;
	private GPScoordinates carPosition;
	private int NumberFreeSeats = 4;
	// no carID as attribut because we know that the carID is BerlinN
	public Berlin(Driver driver, GPScoordinates carPosition, int numberFreeSeats) {
		super(driver, carPosition, numberFreeSeats, "Berlin" + Integer.toString(BerlinNumber+1));
		BerlinNumber++;
		
	}
}
