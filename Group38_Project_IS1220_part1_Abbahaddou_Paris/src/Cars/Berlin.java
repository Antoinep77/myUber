package Cars;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.UberBlack;
import Rides.UberPool;
import Rides.UberVan;
import Rides.UberX;
import customersAndDrivers.Driver;

public class Berlin extends Car{
	/**
	 * the number of berlin cars
	 */
	static int BerlinNumber = 0;
	/**
	 * the gps coordinates of the car
	 */
	private GPScoordinates carPosition;
	/**
	 * the number of seats of the car
	 */
	private final int numberFreeSeats = 4;
	// no carID as attribut because we know that the carID is BerlinN
	
	/**
	 * @param carPosition Constuctor 
	 */
	/**
	 * THe ID of the car
	 */
	private String carID;
	/**
	 * the number of seats of the car
	 */
	private final int numberOfSeats = 4;
	// carID as attribut because we know that the carID is BerlinN
	/**
	 * Constructor of a berlin car
	 * @param carPosition the position of the car
	 */
	public Berlin(GPScoordinates carPosition) {
		super(carPosition, "Berlin" + Integer.toString(BerlinNumber+1));
		carID = "Berlin" + Integer.toString(BerlinNumber+1);
		BerlinNumber++;
	}
	
	// check if the ride and the car are compatible 
	@Override
	public boolean isCompatibleWithTheRide(Ride ride) {
		return 	ride instanceof UberBlack;
	}
}
