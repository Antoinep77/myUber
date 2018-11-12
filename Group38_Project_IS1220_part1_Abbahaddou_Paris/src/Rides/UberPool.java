package Rides;

import java.sql.Time;

import Cars.Car;
import GPS.GPScoordinates;
import customersAndDrivers.Customer;

//concrete element
public class UberPool implements Ride{
	private Car car;
	private Customer customer;
	private GPScoordinates startingPoint;
	private GPScoordinates destinationPoint;
	private Time time;
	
	public UberPool(Customer customer, GPScoordinates startingPoint, GPScoordinates destinationPoint,
			Time time) {
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
			return 2.4;
		}else if(length >=5 && length <10) {
			return 3;
		}else if (length >=10 && length <20) {
			return 1.3;
		}else {
			return 1.1;
		}
	}

	@Override
	public void accept(CostVisitor costVisitor) {
		costVisitor.visit(this);
	}
}
