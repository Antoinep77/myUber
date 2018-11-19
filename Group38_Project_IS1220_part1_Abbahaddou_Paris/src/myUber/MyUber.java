package myUber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import Cars.Car;
import Cars.CarFactory;
import GPS.GPScoordinates;
import Rides.ConcreteCostVisitor;
import Rides.CostVisitor;
import Rides.Pool;
import Rides.Ride;
import Rides.RideFactory;
import Rides.RideStatus;
import Rides.UberPool;
import Rides.UberX;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;
import customersAndDrivers.DriverState;

public class MyUber {
	
	private List<Customer> customerList= new ArrayList<Customer>();
	private List<Driver> driverList = new ArrayList<Driver>(); 
	private List<Car> carList = new ArrayList<Car>();
	private List<Ride> rideList = new ArrayList<Ride>();
	
	//Constructor with no argument to test the other method with a few customer, driver etc...
	public MyUber(String test) throws Exception {
		Car car1 = createCar("Standard",new GPScoordinates(9, 6));
		Car car2 = createCar("Berlin",new GPScoordinates(3, 2));
		createDriver(car1,"D1","NOMD1");
		createDriver(car2,"D2","NOMD2");
	}
	
	public MyUber() {}
	
	
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public List<Driver> getDriverList() {
		return driverList;
	}
	public List<Car> getCarList() {
		return carList;
	}
	public List<Ride> getRideList() {
		return rideList;
	}
	public Car createCar(String type,GPScoordinates carPosition) throws Exception {
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
	
	public RideFactory requireRide(Customer cust,GPScoordinates endingPoint, Date d ) {
		GPScoordinates startingPoint = cust.getCustomerPosition();
		CostVisitor visitor = new ConcreteCostVisitor();
		ArrayList<Ride> listOfRides = RideFactory.createAllRides(cust, startingPoint, endingPoint, d);
		
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
			/*
			Pool pool = new Pool(new ArrayList<Ride>());
			ArrayList<Driver> listDriv = new ArrayList<Driver>(this.driverList) ;
			Driver driver = pool.getDriver(listDriv);
			ride.setDriver(driver);
			if (driver == null) {
				ride.getCustomer().addMessageToBox("No available driver found your ride has been canceled");
				ride.setStatus(RideStatus.CANCELED);
			}*/
		}
		else {
			Driver driver = this.findClosestAvailableDriver(ride);
			ride.setDriver(driver);
			if (driver == null) {
				ride.getCustomer().addMessageToBox("No available driver found your ride has been canceled");
				ride.setStatus(RideStatus.CANCELED);
			}
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
		List<Driver> filteredList = onDutyList.stream()
				.filter(d -> d.getCar().isCompatibleWithTheRide(ride) && !ride.getRefusingDriver().contains(d))
				.collect(Collectors.toList());
		if(filteredList.size()==0) {
			return null;
		}
		Driver closestOnDutyDriver = filteredList.get(0);
		Double minDistance = GPScoordinates.distance(closestOnDutyDriver.getCar().getCarPosition(), startingPoint);
		for(Driver d : filteredList) {
			Double minDistance2 = GPScoordinates.distance(d.getCar().getCarPosition(), startingPoint);
			if(minDistance2 < minDistance) {
				closestOnDutyDriver = d;
				minDistance = GPScoordinates.distance(d.getCar().getCarPosition(), startingPoint);
				
			}
		}
		return closestOnDutyDriver;
	}
	
	//only works if the ride is unconfirmed
	public void refuse(Ride ride) {
		Driver driver1 = ride.getDriver();
		if(ride.getStatus() == RideStatus.UNCONFIRMED) {
			ride.addRefusingDriver(driver1);
			ride.getCustomer().addMessageToBox("One driver refused the ride.");
			Driver driver2 = findClosestAvailableDriver(ride);
			ride.setDriver(driver2);
			if (driver2 == null) {
				ride.getCustomer().addMessageToBox("No available driver found your ride has been canceled");
				ride.setStatus(RideStatus.CANCELED);
			}
		}
	}
	//Change the status of the driver so that he can't receive new rides
	//only works if the ride is unconfirmed
	public void confirm(Ride ride) {
		Driver driver = ride.getDriver();
		if(ride.getStatus() == RideStatus.UNCONFIRMED
				&& driver.changeStateTo(DriverState.ONARIDE,ride.getStartingDate())) {
			ride.setStatus(RideStatus.CONFIRMED);
			ride.getCustomer().addMessageToBox("Your ride has been confirmed. Your driver is arriving soon.");
		}
	}
	
	//Give a mark to the driver of one of their ride
	// work only if the ride is completed and the ride hasn't already be marked
	public void mark(Ride ride, int newmark) {
		Customer cust = ride.getCustomer();
		if (ride.getStatus() == RideStatus.COMPLETED && !ride.isMarked() &&
				newmark <= 5 && newmark >= 0) {
			ride.getDriver().addOneMark(newmark);
			cust.addMessageToBox("You rated your Driver with a " + newmark +" star mark.");
		}else {
			cust.addMessageToBox("Invalid mark, your mark must be beetween 0 and 5");
		}
	}
	
	//only works if the ride is confirmed or unconfirmed 
	public void cancel(Ride ride) {
		Customer cust = ride.getCustomer();
		if(ride.getStatus() == RideStatus.CONFIRMED || ride.getStatus() == RideStatus.UNCONFIRMED) {
			ride.getDriver().changeStateTo(DriverState.ONDUTY,ride.getStartingDate());
			ride.setStatus(RideStatus.CANCELED);
			cust.addMessageToBox("Your ride has been canceled");
		}
		else {
			cust.addMessageToBox("You can not cancel this ride");
		}
	}
	
	
	//only works if the ride is confirmed
	//also credit the driver for the ride
	public void start(Ride ride) {
		Driver driver = ride.getDriver();
		if( ride.getStatus() == RideStatus.CONFIRMED) {
			driver.addOneRide();
			ride.getCustomer().spendAmount(ride.getCost());
			driver.addAmount(ride.getCost());
			ride.setStatus(RideStatus.ONGOING);
			ride.getCustomer().addMessageToBox("Your ride has started, you have been charged for the ride");
		}
	}
	//only works if the ride is ongoing
	//Also change the position of the driver to the destination position
	public void finish(Ride ride) {
		Driver driver = ride.getDriver();
		if(ride.getStatus() == RideStatus.ONGOING
				&& driver.changeStateTo(DriverState.ONDUTY,ride.getArrivalDate())) {
		ride.setStatus(RideStatus.COMPLETED);
		driver.getCar().setCarPosition(ride.getDestinationPoint());
		ride.getCustomer().setCustomerPosition(ride.getDestinationPoint());
		ride.getCustomer().addMessageToBox("Your ride is finished, you can now rate your driver.");
		}
	}
	
	

}
