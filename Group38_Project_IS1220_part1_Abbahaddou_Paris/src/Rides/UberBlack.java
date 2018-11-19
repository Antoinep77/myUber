package Rides;

import java.sql.Time;
import java.util.Date;

import Cars.Car;
import GPS.GPScoordinates;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;

// concrete element
public class UberBlack extends Ride{
	
	/**
	 * @param customer the customer booking the ride
	 * @param startingPoint the starting point of the ride
	 * @param destinationPoint the destination of the ride
	 * @param date the date of booking
	 * @param traffic the traffic condition 
	 */
	public UberBlack(Customer customer, GPScoordinates startingPoint, GPScoordinates destinationPoint,
			Date date,TrafficCondition traffic) {
		super(customer,startingPoint,destinationPoint,date,traffic);
	}
	
	//basicRates returns the the basic rate in Euro/Km
	@Override
	public double basicRates() {
		double length = GPScoordinates.distance(getStartingPoint(), getDestinationPoint());
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
		this.setCost( costVisitor.visit(this));
	}
}
