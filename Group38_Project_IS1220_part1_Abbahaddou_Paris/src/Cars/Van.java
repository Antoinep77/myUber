package Cars;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;

public class Van extends Car{
	static int VanNumber = 0;
	private GPScoordinates carPosition;
	private final int numberFreeSeats = 6;
	// no carID as attribut because we know that the carID is VanN
	
	/**
	 * @param carPosition Constructor of Van car
	 */
	public Van(GPScoordinates carPosition) {
		super(carPosition, "Van" + Integer.toString(VanNumber+1));
		VanNumber++;
		
	}
	
	
	}
