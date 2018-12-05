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

/**
 * @author Antoine
 *
 */
public class MyUber {

	private List<Customer> customerList = new ArrayList<Customer>();
	private List<Driver> driverList = new ArrayList<Driver>();
	private List<Car> carList = new ArrayList<Car>();
	private List<Ride> rideList = new ArrayList<Ride>();
	private ArrayList<Pool> listPool = new ArrayList<Pool>();

	/**
	 * MyUber constructor to create an instance with some cars and drivers to test
	 * different methods
	 * 
	 * @param test is useless, this parameter just to get a different signature from
	 *             the no parameter constructor
	 */
	public MyUber(String test) {
		try {
			Car car1 = createCar("standard", new GPScoordinates(9, 6));
			Car car2 = createCar("berline", new GPScoordinates(3, 2));
			createDriver(car1, "D1", "NOMD1");
			createDriver(car2, "D2", "NOMD2");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * Constructor to create an empty myUber instance
	 */
	public MyUber() {
	}

	public MyUber(int nStandardCars, int nBerlinCars, int nVanCars, int nCustomers) {
		for (int i = 0; i < nCustomers; i++) {
			this.createCustomer("customer" + i + "Name", "customer" + i + "Surname",
					new GPScoordinates((Math.random() - 0.5) * 100, (Math.random() - 0.5) * 100), 0);
		}
		try {
			for (int i = 0; i < nStandardCars; i++) {

				Car car = this.createCar("standard",
						new GPScoordinates((Math.random() - 0.5) * 10, (Math.random() - 0.5) * 100));
				Driver d = this.createDriver(car, "driver" + i , "Standard");
				d.changeStateTo(DriverState.OFFDUTY, new Date());
			}
			for (int i = 0; i < nBerlinCars; i++) {
				Car car = this.createCar("berline",
						new GPScoordinates((Math.random() - 0.5) * 10, (Math.random() - 0.5) * 100));
				Driver d = this.createDriver(car, "driver" + i , "Berline");
				d.changeStateTo(DriverState.OFFDUTY, new Date());
			}
			for (int i = 0; i < nVanCars; i++) {
				Car car = this.createCar("van",
						new GPScoordinates((Math.random() - 0.5) * 10, (Math.random() - 0.5) * 100));
				Driver d = this.createDriver(car, "driver" + i , "Van");
				d.changeStateTo(DriverState.OFFDUTY, new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public String toString() {
		return "The customer list : " + customerList + "\n The driver list : " + driverList + " \nThe car list : " + carList;
	}

	/**
	 * @return the list of all customers of this instance of myUber
	 */
	public List<Customer> getCustomerList() {
		return customerList;
	}

	/**
	 * @return the list of all drivers of this instance of myUber
	 */
	public List<Driver> getDriverList() {
		return driverList;
	}

	/**
	 * @return the list of all cars of this instance of myUber
	 */
	public List<Car> getCarList() {
		return carList;
	}

	/**
	 * @return the list of all rides of this instance of myUber
	 */
	public List<Ride> getRideList() {
		return rideList;
	}

	/**
	 * @return the list of all pools of this instance of myUber
	 */
	public ArrayList<Pool> getListPool() {
		return listPool;
	}

	/**
	 * Create a car and add it to the list of car of the MyUber instance
	 * 
	 * @param type        the String that match with the type of car in the
	 *                    CarFactory class
	 * @param carPosition the position of the car
	 * @return the new car instance
	 * @throws Exception if the type parameter does not match any type of car
	 */
	public Car createCar(String type, GPScoordinates carPosition) throws Exception {
		Car car = CarFactory.create(type, carPosition);
		this.carList.add(car);
		return car;
	}

	/**
	 * Create a pool and add it to the list of pool of the MyUber instance
	 * 
	 * @param listPoolRide the list of UberPool rides to construct the pool
	 * @return the new pool instance
	 */
	public Pool createPool(ArrayList<UberPool> listPoolRide) {
		Pool pool = new Pool(listPoolRide);
		listPool.add(pool);
		return pool;
	}

	/**
	 * Create a customer and add it to the list of customer of the MyUber instance
	 * 
	 * @param customerName     the name of the customer
	 * @param customerSurName  the surname of the customer
	 * @param customerPosition the initial position of the customer
	 * @param creditCardNumber the credit card number of the customer
	 * @return the new customer instance
	 */
	public Customer createCustomer(String customerName, String customerSurName, GPScoordinates customerPosition,
			int creditCardNumber) {
		Customer cust = new Customer(customerName, customerSurName, customerPosition, creditCardNumber);
		this.customerList.add(cust);
		return cust;
	}

	/**
	 * Create a driver and add it to the list of driver of the MyUber instance
	 * 
	 * @param car           the car of the driver to be created
	 * @param driverName    the name of the driver to be created
	 * @param driverSurName the surname of the driver to be created
	 * @return the new driver instance
	 */
	public Driver createDriver(Car car, String driverName, String driverSurName) {
		Driver driver = new Driver(car, driverName, driverSurName);
		this.driverList.add(driver);
		return driver;
	}

	// return null if the id doesn't correspond with a customer in customerList

	/**
	 * Get a car with his id
	 * 
	 * @param id the id of the car to be found
	 * @return the car if it has been found
	 * @throws Exception if car is not found
	 */
	public Car getCarWithId(String id) throws Exception {
		for (Car car : carList) {
			if (car.getCarID().equals(id)) {
				return car;
			}
		}
		throw new Exception();
	}
	
	/**
	 * Get a customer with his id
	 * 
	 * @param id the id of the customer to be found
	 * @return the customer if it has been found
	 * @throws Exception if customer can not be found
	 */
	public Customer getCustomerWithId(int id) throws Exception {
		for (Customer cust : customerList) {
			if (cust.getCustomerID() == id) {
				return cust;
			}
		}
		throw new Exception();
	}
	
	
	/**
	 * Find a driver with his name and surname
	 * @param name
	 * @param surname
	 * @return the driver
	 * @throws Exception if no driver can be found
	 */
	public Driver getDriverWithNames(String name, String surname) throws Exception {
		for (Driver driver : driverList) {
			if (driver.getDriverName().equals(name) && driver.getDriverSurName().equals(surname)) {
				return driver;
			}
		}
		throw new Exception();
	}

	/**
	 * Compute the prices of all types of rides in function of the parameter and add
	 * messages to the box of the customer specified in parameter to give him the
	 * prices
	 * 
	 * @param cust        the customer who wants to require a ride
	 * @param endingPoint the destination point were the customer wants
	 * @param date        the date of the requirement
	 * @return an instance of RideFactory constructed with the list of the different
	 *         types of rides. Use the require method on this instance to choose the
	 *         type of ride to require
	 */
	public RideFactory requireRide(Customer cust, GPScoordinates endingPoint, Date date) {
		GPScoordinates startingPoint = cust.getCustomerPosition();
		CostVisitor visitor = new ConcreteCostVisitor();
		ArrayList<Ride> listOfRides = RideFactory.createAllRides(cust, startingPoint, endingPoint, date);

		for (Ride ride : listOfRides) {
			// accept send a message to cust but also edit also edit ride.cost
			ride.accept(visitor);
		}

		return new RideFactory(listOfRides);
	}

	/**
	 * Add this ride to the list of rides of this MyUber instance and allocate a
	 * driver to the ride
	 * 
	 * @param ride - ride to register
	 */
	public void register(Ride ride) {
		this.rideList.add(ride);
		if (ride instanceof UberPool) {
			// TODO
			/*
			 * Pool pool = new Pool(new ArrayList<Ride>()); ArrayList<Driver> listDriv = new
			 * ArrayList<Driver>(this.driverList) ; Driver driver =
			 * pool.getDriver(listDriv); ride.setDriver(driver); if (driver == null) {
			 * ride.getCustomer().
			 * addMessageToBox("No available driver found your ride has been canceled");
			 * ride.setStatus(RideStatus.CANCELED); }
			 */
		} else {
			Driver driver = this.findClosestAvailableDriver(ride);
			ride.setDriver(driver);
			if (driver == null) {
				ride.getCustomer().addMessageToBox("No available driver found your ride has been canceled");
				ride.setStatus(RideStatus.CANCELED);
			}
		}
	}

	/**
	 * Get the list of the driver which status is on-duty
	 * 
	 * @return the list of driver on-duty
	 */
	private ArrayList<Driver> onDutyDrive() {

		ArrayList<Driver> onDutyList = new ArrayList<Driver>();
		for (Driver d : driverList) {
			if (d.getDriverState() == DriverState.ONDUTY) {
				onDutyList.add(d);
			}
		}
		return onDutyList;
	}

	/**
	 * Find the closest on-duty driver to the customer of the ride
	 * 
	 * @param ride - the ride which need to find a driver
	 * @return the nearest driver if there is at least one driver, else the method
	 *         returns null
	 */
	private Driver findClosestAvailableDriver(Ride ride) {
		GPScoordinates startingPoint = ride.getStartingPoint();
		ArrayList<Driver> onDutyList = onDutyDrive();
		List<Driver> filteredList = onDutyList.stream()
				.filter(d -> d.getCar().isCompatibleWithTheRide(ride) && !ride.getRefusingDriver().contains(d))
				.collect(Collectors.toList());
		if (filteredList.size() == 0) {
			return null;
		}
		Driver closestOnDutyDriver = filteredList.get(0);
		Double minDistance = GPScoordinates.distance(closestOnDutyDriver.getCar().getCarPosition(), startingPoint);
		for (Driver d : filteredList) {
			Double minDistance2 = GPScoordinates.distance(d.getCar().getCarPosition(), startingPoint);
			if (minDistance2 < minDistance) {
				closestOnDutyDriver = d;
				minDistance = GPScoordinates.distance(d.getCar().getCarPosition(), startingPoint);

			}
		}
		return closestOnDutyDriver;
	}

	// only works if the ride is unconfirmed

	/**
	 * Use this method to make a driver refuse a ride. The system will then try to
	 * find an other driver for the ride. This method only works if the status of
	 * the ride is unconfirmed.
	 * 
	 * @param ride - the ride to be refused
	 */
	public void refuse(Ride ride) {
		Driver driver1 = ride.getDriver();
		if (ride.getStatus() == RideStatus.UNCONFIRMED) {
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

	// only works if the ride is unconfirmed
	/**
	 * Use this method to make a driver accept a ride This method only works if the
	 * status of the ride is unconfirmed.
	 * 
	 * @param ride - the ride to be accepted
	 */
	public Boolean confirm(Ride ride) {
		Driver driver = ride.getDriver();
		// Change the status of the driver so that he can't receive new rides
		if (ride.getStatus() == RideStatus.UNCONFIRMED && driver != null
				&& driver.changeStateTo(DriverState.ONARIDE, ride.getStartingDate())) {
			ride.setStatus(RideStatus.CONFIRMED);
			ride.getCustomer().addMessageToBox("Your ride has been confirmed. Your driver is arriving soon.");
		return true;
		}
		return false;
	}

	/**
	 * Give a mark to the driver of one of their ride. This method only works if the
	 * ride is completed and the ride hasn't already be marked
	 * 
	 * @param ride    - the ride which should get the mark
	 * @param newmark - the given mark
	 */
	public void mark(Ride ride, int newmark) {
		Customer cust = ride.getCustomer();
		if (ride.getStatus() == RideStatus.COMPLETED && !ride.isMarked() && newmark <= 5 && newmark >= 0) {
			ride.getDriver().addOneMark(newmark);
			cust.addMessageToBox("You rated your Driver with a " + newmark + " star mark.");
		} else {
			cust.addMessageToBox("Invalid mark, your mark must be beetween 0 and 5");
		}
	}

	/**
	 * Cancel the ride. Only works if the ride is confirmed or unconfirmed, the
	 * customer cannot cancel ongoing rides.
	 * 
	 * @param ride- the ride to be canceled
	 */
	public void cancel(Ride ride) {
		Customer cust = ride.getCustomer();
		if (ride.getStatus() == RideStatus.CONFIRMED || ride.getStatus() == RideStatus.UNCONFIRMED) {
			ride.getDriver().changeStateTo(DriverState.ONDUTY, ride.getStartingDate());
			ride.setStatus(RideStatus.CANCELED);
			cust.addMessageToBox("Your ride has been canceled");
		} else {
			cust.addMessageToBox("You can not cancel this ride");
		}
	}

	/**
	 * Notify the system that a ride has started and credit the driver for the ride.
	 * The method only works if the ride is confirmed.
	 * 
	 * @param ride - the ride which has started
	 */
	public void start(Ride ride) {
		Driver driver = ride.getDriver();
		if (ride.getStatus() == RideStatus.CONFIRMED) {
			driver.addOneRide();
			ride.getCustomer().spendAmount(ride.getCost());
			driver.addAmount(ride.getCost());
			ride.setStatus(RideStatus.ONGOING);
			ride.getCustomer().addMessageToBox("Your ride has started, you have been charged for the ride");
		}
	}

	/**
	 * Notify the system that a ride has finished. The method only works if the ride
	 * is ongoing. The method also set the car and the customer positions to the
	 * destination point of the ride
	 * 
	 * @param ride - the ride which finished
	 */
	public void finish(Ride ride) {
		Driver driver = ride.getDriver();
		if (ride.getStatus() == RideStatus.ONGOING && driver.changeStateTo(DriverState.ONDUTY, ride.getArrivalDate())) {
			ride.setStatus(RideStatus.COMPLETED);
			driver.getCar().setCarPosition(ride.getDestinationPoint());
			ride.getCustomer().setCustomerPosition(ride.getDestinationPoint());
			ride.getCustomer().addMessageToBox("Your ride is finished, you can now rate your driver.");
		}
	}

}
