package Cars;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.UberBlack;
import Rides.UberVan;
import customersAndDrivers.Driver;

public class Van extends Car{
	static int VanNumber = 0;
	private String carID;
	private final int numberFreeSeats = 6;
	// no carID as attribut because we know that the carID is VanN
	public Van(GPScoordinates carPosition) {
		super(carPosition);
		carID = "Van" + Integer.toString(VanNumber+1);
		VanNumber++;
		
	}
	
	// check if the ride and the car are compatible 
	@Override
	public boolean isCompatibleWithTheRide(Ride ride) {
		return 	ride instanceof UberVan;
	}
	
	}
