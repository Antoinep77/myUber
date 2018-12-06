package Cars;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.UberBlack;
import Rides.UberPool;
import Rides.UberX;
import customersAndDrivers.Driver;

public class Standard extends Car{
	/**
	 * the number of Standar cars
	 */
	static int StandardNumber = 0;
	/**
	 * The ID of the car
	 */
	private String carID;
	/**
	 * the number of free seats
	 */
	private final int numberFreeSeats = 4;
	// no carID as attribut because we know that the carID is StandardN
	
	/**
	 * @param carPosition Constuctor
	 */
	public Standard(GPScoordinates carPosition) {
		super(carPosition, "Standard" + Integer.toString(StandardNumber+1));
		carID =  "Standard" + Integer.toString(StandardNumber+1);
		StandardNumber++;
		
	}
	
	// check if the ride and the car are compatible 
	@Override
	public boolean isCompatibleWithTheRide(Ride ride) {
		return 	ride instanceof UberX || ride instanceof UberPool;
	}
	
	
}
