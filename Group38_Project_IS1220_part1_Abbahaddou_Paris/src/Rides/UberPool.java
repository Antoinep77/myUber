package Rides;

import java.sql.Time;

import Cars.Car;
import GPS.GPScoordinates;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;

//concrete element
public class UberPool extends Ride{
	
	public UberPool(Customer customer, GPScoordinates startingPoint, GPScoordinates destinationPoint,
			Time time) {
		super(customer,startingPoint,destinationPoint,time);
	}
	
	@Override
	public double basicRates() {
		double length = GPScoordinates.distance(getStartingPoint(), getDestinationPoint());
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
		this.setCost( costVisitor.visit(this));
	}
}
