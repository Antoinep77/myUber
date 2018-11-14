package myUber;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Cars.Car;
import Cars.CarFactory;
import GPS.GPScoordinates;
import Rides.ConcreteCostVisitor;
import Rides.CostVisitor;
import Rides.Ride;
import Rides.RideFactory;
import Rides.RideStatus;
import Rides.UberPool;
import Rides.UberX;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;
import customersAndDrivers.DriverState;

public class MyUber {
	
	private List<Customer> customerList;
	private List<Driver> driverList; 
	private List<Car> carList;
	private List<Ride> rideList;
	
	//Constructor with no argument to test the other method with a few customer, driver etc...
	public MyUber() {
		
	}

	public MyUber(int nc, int nd, int nu) {
		// TODO Auto-generated constructor stub
		rideList = new ArrayList<Ride>();
	}
	
	//Generate a myUber instance with a text file as scenario
	public MyUber(String filepath) {
		
	}
	
	public Car createCar(String type,GPScoordinates carPosition) {
		Car car = CarFactory.create(type, carPosition);
		this.carList.add(car);
		return car;
	}
	
	public Customer createCustomer(String customerName, String customerSurName, GPScoordinates customerPosition,
			int creditCardNumber) {
		Customer cust = new Customer(customerName, customerSurName, customerPosition, creditCardNumber);
		this.customerList.add(cust);
		return cust;
	}
	
	public Driver createDriver(Car car, String driverName, String driverSurName) {
		Driver driver = new Driver(car, driverName, driverSurName);
		this.driverList.add(driver);
		return driver;
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
			//accept send a message to cust but also edit also edit ride.cost
			ride.accept(visitor);
		}
		
		return new RideFactory(listOfRides);
	}
	
	public void register(Ride ride) {
		this.rideList.add(ride);
		if(ride instanceof UberPool) {
			// TODO
		}
		else {
			Driver driver = this.findClosestAvailableDriver(ride);
			ride.setDriver(driver);
		}
	}
	
	// list of on-duty rides
	private ArrayList<Driver> onDutyDrive(Ride ride){
		
		ArrayList<Driver> onDutyList = new ArrayList<Driver>();
		for(Driver d :driverList) {
			if(d.getDriverState() == DriverState.ONDUTY) {
				onDutyList.add(d);
			}
		}
		return onDutyList;
	}
	
	
	// method for finding the closest on-duty driver
	private Driver findClosestAvailableDriver(Ride ride) {
		// TODO Auto-generated method stub
		GPScoordinates startingPoint = ride.getStartingPoint();
		ArrayList<Driver> onDutyList = onDutyDrive(ride);
		Driver closestOnDutyDriver = onDutyList.get(0);
		Double minDistance = GPScoordinates.distance(closestOnDutyDriver.getCar().getCarPosition(), startingPoint);
		for(Driver d : onDutyList) {
			Double minDistance2 = GPScoordinates.distance(d.getCar().getCarPosition(), startingPoint);
			if(minDistance2 < minDistance) {
				closestOnDutyDriver = d;
				minDistance = GPScoordinates.distance(d.getCar().getCarPosition(), startingPoint);
				
			}
		}
		return closestOnDutyDriver;
	}
	
	//only works if driver1 is the driver of ride and the ride is unconfirmed
	public void unconfirm(Driver driver1, Ride ride) {
		if(ride.getDriver() == driver1 && ride.getStatus() == RideStatus.UNCONFIRMED) {
			Driver driver2 = findClosestAvailableDriver(ride);
			ride.setDriver(driver2);
		}
	}
	//Change the status of the driver so that he can't receive new rides
	//only works if driver1 is the driver of ride and the ride is unconfirmed
	public void confirm(Driver driver, Ride ride) {
		if(ride.getDriver() == driver && ride.getStatus() == RideStatus.UNCONFIRMED
				&& driver.changeStateTo(DriverState.ONARIDE,ride.getTime())) {
			ride.setStatus(RideStatus.CONFIRMED);
			ride.getCustomer().addMessageToBox("Your ride as been confirmed. Your driver is arriving soon.");
		}
	}
	
	

}
