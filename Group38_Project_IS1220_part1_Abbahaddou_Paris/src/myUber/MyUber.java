package myUber;

import java.sql.Time;
import java.util.List;

import Cars.Car;
import GPS.GPScoordinates;
import Rides.ConcreteCostVisitor;
import Rides.CostVisitor;
import Rides.Ride;
import Rides.RideFactory;
import Rides.UberX;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;

public class MyUber {
	
	private static List<Customer> customerList;
	private static List<Driver> driverList;
	private static List<Car> carList;

	public MyUber(int nc, int nd, int nu) {
		// TODO Auto-generated constructor stub
	}
	
	//Generate a myUber instance with a text file as scenario
	public MyUber(String filepath) {
		
	}

	
	//return null if the id doesn't correspond with a customer in customerList
	public Customer getUserWithId(int i) {
		for(Customer cust: customerList) {
			if (cust.getCustomerID() == i) {
				return cust;
			}
		}
		return null;
	}
	
	public RideFactory requireRide(Customer cust, GPScoordinates startingPoint,
			GPScoordinates endingPoint, Time t ) {
		CostVisitor visitor = new ConcreteCostVisitor();
		Ride uberX = new UberX(cust, startingPoint, endingPoint,t);
		uberX.accept(visitor);
		
		
		return null;
	}
	
	

}
