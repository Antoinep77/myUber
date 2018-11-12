package Rides;

import java.sql.Time;

import Cars.Car;
import GPS.GPScoordinates;
import customersAndDrivers.Customer;

//concrete element
public class UberVan extends Ride{
	
	public UberVan(Customer customer, GPScoordinates startingPoint, GPScoordinates destinationPoint,
			Time time) {
		super(customer,startingPoint,destinationPoint,time);
	}
	
	@Override
	public double basicRates() {
		double length = GPScoordinates.distance(getStartingPoint(), getDestinationPoint());
		if(length < 5) {
			return 6.2;
		}else if(length >=5 && length <10) {
			return 7.7;
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
