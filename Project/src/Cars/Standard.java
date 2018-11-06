package Cars;

import GPS.GPScoordinates;
import Pacjage1.Driver;

public class Standard extends Car{
	static int StandardNumber = 0;
	private Driver driver;
	private GPScoordinates carPosition;
	private int NumberFreeSeats = 4;
	// no carID as attribut because we know that the carID is StandardN
	public Standard(Driver driver, GPScoordinates carPosition, int numberFreeSeats) {
		super(driver, carPosition, numberFreeSeats, "Standard" + Integer.toString(StandardNumber+1));
		StandardNumber++;
		
	}
	
	
}
