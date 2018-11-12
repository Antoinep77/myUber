package Rides;

import java.sql.Time;

import GPS.GPScoordinates;
import customersAndDrivers.Customer;

//kind of Ride Factory but with more functionality (
public class RideFactory {
	GPScoordinates startingPoint;
	GPScoordinates endingPoint;
	Time time;
	Customer cust;
	
	public Ride create(String nameOfRide) {
		if (nameOfRide.equals("uberX")) {
			return new UberX(null, cust, startingPoint, endingPoint, time)
		}
		
		return null;
		
	}

}
