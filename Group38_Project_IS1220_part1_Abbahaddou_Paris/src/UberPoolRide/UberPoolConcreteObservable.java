package UberPoolRide;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import FindDriverStrategy.FindDriverUberPool;
import GPS.GPScoordinates;
import Rides.Ride;
import Rides.UberPoolRide;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;

public class UberPoolConcreteObservable implements UberPoolObeservable{
	private ArrayList<Ride> listOfObservers;
	private UberPoolRideState state = UberPoolRideState.NOTSTARTED; 
		
	private static int startingOrder = 0;
	private static int endingOrder = 1;
	
	public ArrayList<UberPoolConcreteObserver> getListOfObservers() {
		return listOfObservers;
	}
	

	public UberPoolConcreteObservable(ArrayList<UberPoolConcreteObserver> listOfPoolRide) {
		super();
		this.listOfObservers = listOfObservers;
	}
	
	public UberPoolRideState getState() {
		return state;
	}

	public void setState(UberPoolRideState state) {
		this.state = state;
	}
	
	public void addObserver(UberPoolConcreteObserver observ) {
		if(this.listOfObservers.size() < 3  && this.state == UberPoolRideState.NOTSTARTED) {
			this.listOfObservers.add(observ);
			
		}
	}
	public void removeObserver(UberPoolConcreteObserver observ) {
		this.listOfObservers.remove(observ);
				
	}
	public Driver cost(Driver d) {
		ArrayList<UberPoolConcreteObserver> listObs = new ArrayList<UberPoolConcreteObserver>();
		listObs.sort((UberPoolConcreteObserver o1, UberPoolConcreteObserver o2) -> {
			double d1 = GPScoordinates.distance(o1.getRide().getStartingPoint(), o1.getRide().getDriver().getCar().getCarPosition());
			double d2 = GPScoordinates.distance(o1.getRide().getStartingPoint(), o2.getRide().getDriver().getCar().getCarPosition());
			if(d1 < d2) {
				return 1;
			}
			if(d1 > d2) {
				return -1;
			}
			else {
				return 0;
			}
				
		});
		return listObs.get(0).getRide().getDriver();
	}
	
	
	public 
	public Ride startOneRide() {
		ArrayList<Ride> OrderofStart = FindDriverUberPool.OrderofStarting(this.listOfObservers);
		int n = new Integer(startingOrder);
		startingOrder ++;
		return OrderofStart.get(n);
	}
	public Ride finishOneRide() {
		ArrayList<Ride> OrderofEnd = FindDriverUberPool.OrderofEnding(this.listOfObservers);
		int n = new Integer(endingOrder);
		endingOrder++;
		return OrderofEnd.get(n);
	}
	
	
	
	
	public void notifyObserver() {/*
		for (UberPoolConcreteObserver uberPoolConcreteObserver : listOfPoolCustomer) {
			uberPoolConcreteObserver.update(this);
		}*/
	}
}
