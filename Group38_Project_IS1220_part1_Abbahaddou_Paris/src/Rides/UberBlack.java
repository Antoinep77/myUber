package Rides;

import java.sql.Time;

import Cars.Car;
import GPS.GPScoordinates;
import customersAndDrivers.Customer;

// concrete element
public class UberBlack implements Ride{
	private Car car;    
	private Customer customer;
	private GPScoordinates startingPoint;
	private GPScoordinates destinationPoint;
	private Time time;   
	
	public UberBlack(Customer customer, GPScoordinates startingPoint, GPScoordinates destinationPoint,
			Time time) {
		this.customer = customer;
		this.startingPoint = startingPoint;
		this.destinationPoint = destinationPoint;
		this.time = time;
	}
	
	
	public Time getTime() {
		return time;
	}
		// getLength return the the length of the ride
	public double getLentgh() {
		return GPScoordinates.distance(startingPoint, destinationPoint);
	}

	
	//basicRates returns the the basic rate in Euro/Km 
	public double basicRates() {
		double length = GPScoordinates.distance(startingPoint, destinationPoint);
		if(length < 5) {
			return 6.2;
		}else if(length >=5 && length <10) {
			return 5.5;
		}else if (length >=10 && length <20) {
			return 3.25;
		}else {
			return 2.6;
		}
	}
	
	@Override
	public void accept(CostVisitor costVisitor) {
		costVisitor.visit(this);
	}
}
