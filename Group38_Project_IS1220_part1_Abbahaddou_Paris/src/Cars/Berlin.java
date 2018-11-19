package Cars;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.UberBlack;
import Rides.UberPool;
import Rides.UberVan;
import Rides.UberX;
import customersAndDrivers.Driver;

public class Berlin extends Car{
	static int BerlinNumber = 0;

	private GPScoordinates carPosition;
	private final int numberFreeSeats = 4;
	// no carID as attribut because we know that the carID is BerlinN
	
	/**
	 * @param carPosition Constuctor 
	 */

	private String carID;
	private final int numberOfSeats = 4;
	// carID as attribut because we know that the carID is BerlinN

	public Berlin(GPScoordinates carPosition) {
		super(carPosition);
		carID = "Berlin" + Integer.toString(BerlinNumber+1);
		BerlinNumber++;
	}
	
	// check if the ride and the car are compatible 
	@Override
	public boolean isCompatibleWithTheRide(Ride ride) {
		return 	ride instanceof UberBlack;
	}
}
