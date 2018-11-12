package Rides;

import java.sql.Time;

import Cars.Car;
import GPS.GPScoordinates;
import customersAndDrivers.Customer;

// concrete element
public class UberX implements Ride{
	private Car car;
	private Customer customer;
	private GPScoordinates startingPoint;
	private GPScoordinates destinationPoint;
	private Time time;
	
	public UberX(Customer customer, GPScoordinates startingPoint,
			GPScoordinates destinationPoint, Time time) {
		this.customer = customer;
		this.startingPoint = startingPoint;
		this.destinationPoint = destinationPoint;
		this.time = time;
	}
	
	public Time getTime() {
		return time;
	}

	public double getLentgh() {
		return GPScoordinates.distance(startingPoint, destinationPoint);
	}
	public double basicRates() {
		double length = GPScoordinates.distance(startingPoint, destinationPoint);
		if(length < 5) {
			return 3.3;
		}else if(length >=5 && length <10) {
			return 4.2;
		}else if (length >=10 && length <20) {
			return 1.91;
		}else {
			return 1.5;
		}
	}

	@Override
	public void accept(CostVisitor costVisitor) {
		costVisitor.visit(this);
	}

}
