package Rides;

import java.sql.Time;
import java.util.ArrayList;

import GPS.GPScoordinates;
import customersAndDrivers.Customer;

//kind of Ride Factory but with more functionality (
public class RideFactory {
	GPScoordinates startingPoint;
	GPScoordinates endingPoint;
	Time time;
	Customer cust;
	
	//Return an ArrayList with all type of rides available
	//The parameters are the parameters passes to the constructors
	static ArrayList<Ride> createAllRides(Customer cust, GPScoordinates startingPoint,
			GPScoordinates endingPoint,Time time){
		ArrayList<Ride> listOfRides = new ArrayList<Ride>();
		listOfRides.add(new UberX(cust,startingPoint,endingPoint,time));
		listOfRides.add(new UberPool(cust,startingPoint,endingPoint,time));
		listOfRides.add(new UberBlack(cust,startingPoint,endingPoint,time));
		listOfRides.add(new UberVan(cust,startingPoint,endingPoint,time));
		return listOfRides;
	}
	
	public Ride create(String nameOfRide) {
		if (nameOfRide.equals("uberX")) {
			return new UberX(cust, startingPoint, endingPoint, time);
		}
		if (nameOfRide.equals("uberPool")) {
			return new UberPool(cust, startingPoint, endingPoint, time);
		}
		if (nameOfRide.equals("uberBlack")) {
			return new UberBlack(cust, startingPoint, endingPoint, time);
		}
		if (nameOfRide.equals("uberVan")) {
			return new UberVan(cust, startingPoint, endingPoint, time);
		}
		return null;
		
	}

}
