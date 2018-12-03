package Rides;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import GPS.GPScoordinates;
import customersAndDrivers.Customer;
import myUber.MyUber;

//this class is inspired of a factory pattern but isn't one
public class RideFactory {
	
	/**
	 * list of all possible rides that a customer can order
	 */
	List<Ride> rides; // list of all possible rides that a customer can order
					 // in this list the rides are already constructed with Cost, Customer, Coordinates and Time
	
	/**
	 * Constructor
	 * @param rides
	 */
	public RideFactory(List<Ride> rides) {
		this.rides =rides;
	}

	//Return an ArrayList with all type of rides available
	//The parameters are the parameters passes to the constructors
	//The same traffic condition for the 4 rides is also determined in this method
	/**
	 * method to create all types of ride with the same cusomer, the same sarting point and the same destination	 * @param cust
	 * @param startingPoint
	 * @param endingPoint
	 * @param date
	 * @return ArrayList with all existing type of rides 
	 */
	public static ArrayList<Ride> createAllRides(Customer cust, GPScoordinates startingPoint,
			GPScoordinates endingPoint,Date date){
		TrafficCondition traffic = TrafficCondition.setTrafficCondition(date);
		ArrayList<Ride> listOfRides = new ArrayList<Ride>();
		listOfRides.add(new UberX(cust,startingPoint,endingPoint,date,traffic));
		listOfRides.add(new UberPool(cust,startingPoint,endingPoint,date,traffic));
		listOfRides.add(new UberBlack(cust,startingPoint,endingPoint,date,traffic));
		listOfRides.add(new UberVan(cust,startingPoint,endingPoint,date,traffic));
		return listOfRides;
	}
	
	
	/**
	 * Constructor for creating a new ride and adding it in the myUber system
	 * @param myUber
	 * @param nameOfRide the type of ride to create ("uberBlack","uberVan","uberX","uberPool")
	 * @return a new ride 
	 * @throws Exception if the string doesn't much with the strings "UberBlack","UberVan","UberX","UberPool"
	 */
	public Ride require(MyUber myUber,String nameOfRide) throws Exception{
		if (nameOfRide.equals("uberX")) {
			//returning first (and only one) element of the list of type UberX
			Ride ride = this.rides.stream().filter(r -> (r instanceof UberX))
									.findFirst()
									.get();
			ride.getCustomer().addMessageToBox("You have selected an UberX ride.");
			myUber.register(ride);
			return ride;
		}
		if (nameOfRide.equals("uberPool")) {
			Ride ride = this.rides.stream().filter(r -> (r instanceof UberPool))
					.findFirst()
					.get();
			ride.getCustomer().addMessageToBox("You have selected an UberPool ride.");
			myUber.register(ride);
			return ride;
		}
		if (nameOfRide.equals("uberBlack")) {
			Ride ride = this.rides.stream().filter(r -> (r instanceof UberBlack))
					.findFirst()
					.get();
			ride.getCustomer().addMessageToBox("You have selected an UberBlack ride.");
			myUber.register(ride);
			return ride;
		}
		if (nameOfRide.equals("uberVan")) {
			Ride ride = this.rides.stream().filter(r -> (r instanceof UberVan))
					.findFirst()
					.get();
			ride.getCustomer().addMessageToBox("You have selected an UberVan ride.");
			myUber.register(ride);
			return ride;
		}
		throw new Exception("Invalid Type of Ride");
		
	}
	

}
