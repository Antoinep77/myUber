package Rides;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import GPS.GPScoordinates;
import customersAndDrivers.Customer;
import myUber.MyUber;

//this class is inspired of a factory pattern but isn't one
public class RideFactory {
	
	List<Ride> rides; // list of all possible rides that a customer can order
					 // in this list the rides are already constructed with Cost, Customer, Coordinates and Time
	
	public RideFactory(List<Ride> rides) {
		this.rides =rides;
	}

	//Return an ArrayList with all type of rides available
	//The parameters are the parameters passes to the constructors
	public static ArrayList<Ride> createAllRides(Customer cust, GPScoordinates startingPoint,
			GPScoordinates endingPoint,Time time){
		ArrayList<Ride> listOfRides = new ArrayList<Ride>();
		listOfRides.add(new UberX(cust,startingPoint,endingPoint,time));
		listOfRides.add(new UberPool(cust,startingPoint,endingPoint,time));
		listOfRides.add(new UberBlack(cust,startingPoint,endingPoint,time));
		listOfRides.add(new UberVan(cust,startingPoint,endingPoint,time));
		return listOfRides;
	}
	
	
	public Ride require(MyUber myUber,String nameOfRide) throws Exception{
		if (nameOfRide.equals("uberX")) {
			//returning first (and only one) element of the list of type UberX
			return this.rides.stream().filter(ride -> (ride instanceof UberX))
									.findFirst()
									.get();
		}
		if (nameOfRide.equals("uberPool")) {
			return this.rides.stream().filter(ride -> (ride instanceof UberPool))
					.findFirst()
					.get();
		}
		if (nameOfRide.equals("uberBlack")) {
			return this.rides.stream().filter(ride -> (ride instanceof UberBlack))
					.findFirst()
					.get();
		}
		if (nameOfRide.equals("uberVan")) {
			return this.rides.stream().filter(ride -> (ride instanceof UberVan))
					.findFirst()
					.get();
		}
		throw new Exception("Invalid Type of Ride");
		
	}

}
