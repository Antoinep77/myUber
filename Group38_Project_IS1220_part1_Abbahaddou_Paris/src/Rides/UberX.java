package Rides;

import java.sql.Time;

import Cars.Car;
import GPS.GPScoordinates;
import customersAndDrivers.Customer;

// concrete element
public class UberX extends Ride{
	
	public UberX(Customer customer, GPScoordinates startingPoint,
			GPScoordinates destinationPoint, Time time) {
		super(customer,startingPoint,destinationPoint,time);
	}
	
	@Override
	public double basicRates() {
		double length = GPScoordinates.distance(getStartingPoint(), getDestinationPoint());
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