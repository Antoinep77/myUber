package Rides;

import java.sql.Time;
import java.util.ArrayList;

import Cars.Berlin;
import Cars.Car;
import Cars.Standard;
import Cars.Van;
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
	private Time arrivalTime;
	private RideStatus status = RideStatus.UNCONFIRMED;
	private ArrayList<Driver> refusingDriver = new ArrayList<Driver>();
	private boolean marked = false;
	private double cost;
	
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
	
	public ArrayList<Driver> getRefusingDriver() {
		return refusingDriver;
	}
	
	public void addRefusingDriver(Driver driver) {
		refusingDriver.add(driver);
	}

	// check if the ride and the car are compatible 
	public static boolean isCompatibleWithTheRide(Ride ride, Car car) {
		if(ride instanceof UberBlack && car instanceof Standard) {
			return true;
		}else if(ride instanceof UberX && car instanceof Berlin) {
			return true;
		}else if(ride instanceof UberVan && car instanceof Van) {
			return true;
		}else if(ride instanceof UberPool && car instanceof Standard) {
			return true;
		}else {
			return false;
		}
				
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
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean isMarked() {
		return this.marked;
	}
	
	public abstract void accept(CostVisitor costVisitor);
	
	public abstract double basicRates();

	public Time getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Time arrivalTime) {
		this.arrivalTime = arrivalTime;
	}


}
