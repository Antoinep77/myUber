package FindDriverStrategy;

import java.util.ArrayList;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.UberPoolRide;
import UberPoolRide.UberPoolConcreteObservable;
import UberPoolRide.UberPoolConcreteObserver;
import customersAndDrivers.Driver;

public class FindDriverUberPool {
	public double cost(Driver d, UberPoolConcreteObservable observable) {
		ArrayList<UberPoolConcreteObserver> listOfObserver = new observable.getListOfObservers();
		
		return 0;
	}
	
	public static Driver findDriverForUberPool(UberPoolConcreteObservable concreteObserver) {
		return null;
		
	}
	public static ArrayList<Ride> OrderofStarting(ArrayList<UberPoolConcreteObserver> listObservers){
		return null;
	}
	public static ArrayList<Ride> OrderofEnding(ArrayList<UberPoolConcreteObserver> listObservers){
		return null;
	}
	/*
	ArrayList<UberPoolConcreteObserver> listOfObserver = new ArrayList<UberPoolConcreteObserver>(this.listOfPoolCustomer);
	listOfObserver.sort((UberPoolConcreteObserver o1, UberPoolConcreteObserver o2){
		if(GPS.GPScoordinates.distance(point1, point2)) {
			return -1;
		}if() {
			return 1;
		}else {
			return 0;
			
		}
			
	});*/

}
