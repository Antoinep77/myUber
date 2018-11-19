package Rides;

import java.util.ArrayList;
import java.util.stream.Collectors;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;
import customersAndDrivers.DriverState;
import myUber.MyUber;

public class Pool {
	/**
	 * The list of shared rides
	 */
	private ArrayList<UberPool> listPoolRide = new ArrayList<UberPool>();

	/**
	 * Constuctor
	 * @param listPoolRide The list of shared rides
	 */
	public Pool(ArrayList<UberPool> listPoolRide) {
		super();
		this.listPoolRide = listPoolRide;
	}
	/**
	 * method to add a uber pool ride to the list of shared rides
	 * @param ride add a UberPool ride to the list of shared rides
	 */
	public void addOneRide(UberPool ride) {
		this.listPoolRide.add(ride);
	}
	/**
	 * method that determine which ride has the nearest starting point to a position
	 * @param position 
	 * @param allRides 
	 * @return the ride has the nearest starting point to a position
	 */
	public Ride ClosestRideInStarting(GPScoordinates position, ArrayList<Ride> allRides) {
		ArrayList<Ride> listRide = new ArrayList<Ride>(allRides);
		listRide.sort((Ride r1, Ride r2) -> {
			double d1 = GPScoordinates.distance(position, r1.getStartingPoint());
			double d2 = GPScoordinates.distance(position, r2.getStartingPoint());
			if(d1 < d2) {
				return -1;
			}else if(d1 > d2) {
				return 1;
			}else {
				return 0;
			}
		});
		return listRide.get(0);
	}
	/**
	 * method that determine which ride has the nearest destination point to a position
	 * @param position
	 * @param allRides
	 * @return the ride has the nearest destination point to a position
	 */
	public Ride ClosestRideInEnding(GPScoordinates position, ArrayList<Ride> allRides) {
		ArrayList<Ride> listRide = new ArrayList<Ride>(allRides);
		listRide.sort((Ride r1, Ride r2) -> {
			double d1 = GPScoordinates.distance(position, r1.getDestinationPoint());
			double d2 = GPScoordinates.distance(position, r2.getDestinationPoint());
			if(d1 < d2) {
				return -1;
			}else if(d1 > d2) {
				return 1;
			}else {
				return 0;
			}
		});
		return listRide.get(0);
	}
	/**
	 * method that gives the right order for taking the customers from their starting point 
	 * @param driver
	 * @return an array list of rides the right order for taking the customers from their starting point 
	 */
	public ArrayList<Ride> startingPoolOrder(Driver driver){
		ArrayList<Ride> listRide = new ArrayList<Ride>(listPoolRide);
		ArrayList<Ride> listRideInOrder = new ArrayList<Ride>();
		GPScoordinates position = driver.getCar().getCarPosition();
		int n = listRide.size();
		for (int i = 0; i < n; i++) {
			Ride ride1 = ClosestRideInStarting(position, listRide);
			listRide.remove(ride1);
			position = ride1.getStartingPoint();
			listRideInOrder.add(ride1);
		}
		return listRideInOrder;
		
	}
	/**
	 * method that gives the right order for dropping off the customers in their destination
	 * @param driver 
	 * @return an array list of rides the right order for dropping off the customers from their destination
	 */
	public ArrayList<Ride> endingPoolOrder(Driver driver){
		ArrayList<Ride> listRide = new ArrayList<Ride>(listPoolRide);
		ArrayList<Ride> listRideInOrder = new ArrayList<Ride>();
		GPScoordinates position = startingPoolOrder(driver).get( startingPoolOrder(driver).size() - 1).getStartingPoint();  // last element in the starting order list
		int n = listRide.size();
		for (int i = 0; i < n; i++) {
			Ride ride1 = ClosestRideInEnding(position, listRide);
			listRide.remove(ride1);
			position = ride1.getStartingPoint();
			listRideInOrder.add(ride1);
		}
		return listRideInOrder;
		
	}
	/**
	 * @param driver
	 * @return the minimum cost form all possible odrers
	 */
	public double minCost(Driver driver) {		
		double cost = 0;
		ArrayList<Ride> liststart = new ArrayList<Ride>(startingPoolOrder(driver));
		ArrayList<Ride> listend = new ArrayList<Ride>(endingPoolOrder(driver));
		int size1 = liststart.size();
		int size2 = listend.size();
		cost  = cost  + GPScoordinates.distance(driver.getCar().getCarPosition(), liststart.get(0).getStartingPoint());
		for (int i = 0; i < size1 - 1; i++) {
			cost = cost +  GPScoordinates.distance(liststart.get(i).getStartingPoint(), liststart.get(i+1).getStartingPoint());
		}
		cost = cost + GPScoordinates.distance(liststart.get(size1 - 1).getStartingPoint(), listend.get(0).getDestinationPoint());
		for (int i = 0; i < size2 - 1; i++) {
			cost = cost +  GPScoordinates.distance(listend.get(i).getStartingPoint(), listend.get(i+1).getStartingPoint());
		}
		return cost;
	}
	/**
	 * method to determine the driver with the minimum cost
	 * @param listDrivers a list of drivers
	 * @return the driver with the minimum cost
	 */
	public Driver getDriver(ArrayList<Driver> listDrivers) {
		ArrayList<Driver> listDriverPool = new ArrayList<Driver>(listDrivers);
		for (Driver driver : listDriverPool) {
			if(driver.getDriverState() != DriverState.ONDUTY) {
				listDriverPool.remove(driver);
			}
		}
		listDriverPool.sort((Driver d1 , Driver d2) -> {
			if(minCost(d1) < minCost(d2)) {
				return -1;
			}else if(minCost(d1) > minCost(d2)) {
				return 1;
			}else {
				return 0;
			}
				
		});
		return listDriverPool.get(0);	
	}

}
