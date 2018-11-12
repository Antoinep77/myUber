package myUber;

import java.sql.Time;
import java.util.ArrayList;
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
	
	private List<Customer> customerList;
	private List<Driver> driverList;
	private List<Car> carList;
	private List<Ride> rideList;

	public MyUber(int nc, int nd, int nu) {
		// TODO Auto-generated constructor stub
		rideList = new ArrayList<Ride>();
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
		ArrayList<Ride> listOfRides = RideFactory.createAllRides(cust, startingPoint, endingPoint, t);
		
		for(Ride ride: listOfRides) {
			ride.accept(visitor);
		}
		
		return new RideFactory(cust, startingPoint, endingPoint, t);
	}
	
	public void register(Ride ride) {
		this.rideList.add(ride);
		this.findClosestAvailableDriver(ride);
	}

	private void findClosestAvailableDriver(Ride ride) {
		// TODO Auto-generated method stub
		
	}
	
	

}
