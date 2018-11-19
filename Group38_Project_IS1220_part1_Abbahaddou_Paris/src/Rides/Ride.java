package Rides;


import java.util.ArrayList;
import java.util.Date;

import Cars.Berlin;
import Cars.Car;
import Cars.Standard;
import Cars.Van;
import GPS.GPScoordinates;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;
import GPS.GPScoordinates;
import TimeOperation.TimeOperation;


//Element interface
/**
 * @author yassine & antoine
 *
 */
public abstract class Ride {
	
	/**
	 * the driver of the ride
	 */
	private Driver driver;    
	/**
	 * the customer that book the ride
	 */
	private Customer customer;
	/**
	 * the starting point of the ride
	 */
	private GPScoordinates startingPoint;
	/**
	 * the destination point of the ride
	 */
	private GPScoordinates destinationPoint;
	/**
	 * the  when the ride start
	 */
	private Date startingDate;
	/**
	 * the time when the ride finish
	 */
	private Date arrivalDate;
	/**
	 * the status of the ride
	 */
	private RideStatus status = RideStatus.UNCONFIRMED;
	/**
	 * the list of the drivers that refuses this ride
	 */
	private ArrayList<Driver> refusingDriver = new ArrayList<Driver>();
	/**
	 *  booelean : true if the customer marks the driver, and false if not yet
	 */
	private boolean marked = false;
	/**
	 * he cost of the ride
	 */
	private double cost;
	/**
	 * the traffic condition at the starting date
	 */
	private TrafficCondition traffic;
	
	/**
	 * Constructor of Ride
	 * @param customer
	 * @param startingPoint
	 * @param destinationPoint
	 * @param date
	 * @param traffic
	 */
	public Ride(Customer customer, GPScoordinates startingPoint,
			GPScoordinates destinationPoint,Date date, TrafficCondition traffic) {
		this.customer = customer;
		this.startingPoint = startingPoint;
		this.destinationPoint = destinationPoint;
		this.startingDate = date;
		this.traffic = traffic;
		this.arrivalDate = new Date( this.startingDate.getTime()
		+ TimeOperation.rideDurationTime(GPScoordinates.distance(destinationPoint, destinationPoint), this.traffic));
	}
	
	/**
	 * getter of the starting date
	 * @return the starting date
	 */
	public Date getStartingDate() {
		return startingDate;
	}

	/**
	 * @return the driver of the ride
	 */
	public Driver getDriver() {
		return driver;
	}
	/**
	 * setter of Driver
	 * @param driver 
	 */
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	/**
	 * @return the starting point of the ride
	 */
	public GPScoordinates getStartingPoint() {
		return startingPoint;
	}
	/**
	 * @return the destinaion point of the ride
	 */
	public GPScoordinates getDestinationPoint() {
		return destinationPoint;
	}
	/**
	 * @return the customer that books the rides
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * getters
	 * @return the list of the drivers that refuse this ride
	 */
	public ArrayList<Driver> getRefusingDriver() {
		return refusingDriver;
	}
	
	/**
	 * method for adding a driver to the list of the drivers that refuse this ride
	 * @param driver 
	 */
	public void addRefusingDriver(Driver driver) {
		refusingDriver.add(driver);
	}
	
	/**
	 * getters
	 * @return the traffic condition at the starting date
	 */
	public TrafficCondition getTraffic() {
		return traffic;
	}



	/**
	 * getters
	 * @return the current status of the ride
	 */
	public RideStatus getStatus() {
		return status;
	}
	/**
	 * setters
	 * @param status to set a new status
	 */
	public void setStatus(RideStatus status) {
		this.status = status;
	}
	
	// getLength return the the length of the ride
	/**
	 * getters
	 * @return the distance between the starting point and the destination point 
	 */
	public double getLentgh() {
		return GPScoordinates.distance(startingPoint, destinationPoint);
	}
	
	/**
	 * getters
	 * @return the cost the ride 
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * setter
	 * @param cost the cost of the ride
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return true if the customer marks the driver,and false if not yet
	 */
	public boolean isMarked() {
		return this.marked;
	}
	
	/**
	 * the accept method in the strategy pattern, this method invokes the visit() method
	 * @param costVisitor
	 */
	public abstract void accept(CostVisitor costVisitor);
	
	/**
	 * @return the basic rate of the ride
	 */
	public abstract double basicRates();

	/**
	 * @return the arrival date
	 */
	public Date getArrivalDate() {
		return arrivalDate;
	}


}
