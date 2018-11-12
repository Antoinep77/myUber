package Rides;

import java.sql.Time;

import GPS.GPScoordinates;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;
import GPS.GPScoordinates;


//Element interface
public abstract class Ride {
	
	private Driver driver;    
	private Customer customer;
	private GPScoordinates startingPoint;
	private GPScoordinates destinationPoint;
	private Time time;
	private RideStatus status;
	
	public Ride(Customer customer, GPScoordinates startingPoint,
			GPScoordinates destinationPoint,Time time) {
		this.customer = customer;
		this.startingPoint = startingPoint;
		this.destinationPoint = destinationPoint;
		this.time = time;
		this.status = RideStatus.UNCONFIRMED;
	}
	public Time getTime() {
		return time;
	}

	public GPScoordinates getStartingPoint() {
		return startingPoint;
	}
	public GPScoordinates getDestinationPoint() {
		return destinationPoint;
	}
	public Customer getCustomer() {
		return customer;
	}
	
	// getLength return the the length of the ride
	public double getLentgh() {
		return GPScoordinates.distance(startingPoint, destinationPoint);
	}
	
	public abstract void accept(CostVisitor costVisitor);
	
	public abstract double basicRates();

}
