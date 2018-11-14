package Rides;

import java.sql.Time;
import java.util.ArrayList;

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
	private RideStatus status = RideStatus.UNCONFIRMED;
	private ArrayList<Driver> refusingDriver = new ArrayList<Driver>();
	private boolean marked = false;
	
	public Ride(Customer customer, GPScoordinates startingPoint,
			GPScoordinates destinationPoint,Time time) {
		this.customer = customer;
		this.startingPoint = startingPoint;
		this.destinationPoint = destinationPoint;
		this.time = time;
	}
	
	public Time getTime() {
		return time;
	}

	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
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
	
	public RideStatus getStatus() {
		return status;
	}
	public void setStatus(RideStatus status) {
		this.status = status;
	}
	// getLength return the the length of the ride
	public double getLentgh() {
		return GPScoordinates.distance(startingPoint, destinationPoint);
	}
	
	public boolean isMarked() {
		return this.marked;
	}
	
	public abstract void accept(CostVisitor costVisitor);
	
	public abstract double basicRates();


}
