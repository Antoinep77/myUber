package myUber;

import java.util.List;

import Cars.Car;
import GPS.GPScoordinates;
import Rides.ConcreteCostVisitor;
import Rides.CostVisitor;
import Rides.RideSearching;
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
	
	public RideSearching requireRide(Customer cust, GPScoordinates coords, Time t ) {
		CostVisitor visitor = new ConcreteCostVisitor();
		
		
	}
	
	

}
