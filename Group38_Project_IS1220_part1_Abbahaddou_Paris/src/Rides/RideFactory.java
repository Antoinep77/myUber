package Rides;

import java.sql.Time;
import java.util.ArrayList;

import GPS.GPScoordinates;
import customersAndDrivers.Customer;
import myUber.MyUber;

//this class looks like a factory pattern to create Rides but isn't actually one
public class RideFactory {
	GPScoordinates startingPoint;
	GPScoordinates endingPoint;
	Time time;
	Customer cust;
	
	public RideFactory(Customer cust2, GPScoordinates startingPoint2, GPScoordinates endingPoint2, Time time2) {
		cust = cust2;
		startingPoint = startingPoint2;
		endingPoint = endingPoint2;
		time = time2;
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
			Ride ride = new UberX(cust, startingPoint, endingPoint, time);
			myUber.register(ride);
			return ride;
		}
		if (nameOfRide.equals("uberPool")) {
			Ride ride = new UberPool(cust, startingPoint, endingPoint, time);
			myUber.register(ride);
			return ride;
		}
		if (nameOfRide.equals("uberBlack")) {
			Ride ride = new UberBlack(cust, startingPoint, endingPoint, time);
			myUber.register(ride);
			return ride;
		}
		if (nameOfRide.equals("uberVan")) {
			Ride ride = new UberVan(cust, startingPoint, endingPoint, time);
			myUber.register(ride);
			return ride;
		}
		cust.addMessageToBox("Invalid type of ride");
		throw new Exception("Invalid Type of Ride");
		
	}

}
