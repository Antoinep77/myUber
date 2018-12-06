package PoolObseerver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.RideStatus;
import customersAndDrivers.Driver;
import customersAndDrivers.DriverState;
import myUber.MyUber;

public class ConcreteObservable implements Observable{
	private ArrayList<ObserverPool> observers = new ArrayList<ObserverPool>();
	
	public ConcreteObservable(ArrayList<ObserverPool> observers) {
		super();
		this.observers = observers;
		
	}
	
	public ArrayList<ObserverPool> getObservers() {
		return observers;
	}

	@Override
	public void registerObserver(ObserverPool observer) {
	
	observers.add(observer);
	if (observer instanceof ConcreteObserver) {
		((ConcreteObserver) observer).getRide().getCustomer().addMessageToBox("Welcome to this Pool car where you can share this this ride with others customers");
	}
	notifyObservers();
	
	
	}
	@Override
	public void removeObserver(ObserverPool observer) {
	observers.remove(observer);
	
	}
	@Override
	public void notifyObservers() {
		
		observers.get(0).update(this);
		
	}
	
	public ArrayList<Ride> listOfRides(){
		ArrayList<Ride> r = new ArrayList<Ride>();
		for (ObserverPool observer : observers) {
			if(observer instanceof ConcreteObserver) {
				r.add(((ConcreteObserver) observer).getRide());
			}
		}
		return r;
	}
	
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
	
	public ArrayList<Ride> startingPoolOrder(Driver driver){
		ArrayList<Ride> listRide = new ArrayList<Ride>(this.listOfRides());
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
	
	public ArrayList<Ride> endingPoolOrder(Driver driver){
		ArrayList<Ride> listRide = new ArrayList<Ride>(this.listOfRides());
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
	
	public Driver getDriver(ArrayList<Driver> list) {
		ArrayList<Driver> listDriverPool = new ArrayList<Driver>(list);
		/*
		for (Driver driver : listDriverPool) {
			if(driver.getDriverState() != DriverState.ONDUTY) {
				listDriverPool.remove(driver);
			}
		}*/
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
	
	public void startPoolRide(Driver driver, MyUber myUber) {
		ArrayList<Ride> poolListStart = new ArrayList<Ride>(this.startingPoolOrder(driver));
		String s = new String("the order of starting points is  ---> ");
		for (Ride ride3 : poolListStart) {
			s = s + ride3.getCustomer().getCustomerName();
			s = s + " ---> ";
			myUber.start(ride3);
		}
		System.out.println(s);
	}
	
	
	public void finishPoolRide(Driver driver, MyUber myUber) {
		ArrayList<Ride> poolListEnd = new ArrayList<Ride>(this.endingPoolOrder(driver));
		int n = this.listOfRides().size();
		String s = new String("the order of destination points is  ---> ");
		for (int i = 0; i < n; i++) {
			s = s + poolListEnd.get(i).getCustomer().getCustomerName();
			s = s + " ---> ";
			poolListEnd.get(i).setStatus(RideStatus.ONGOING);
			//System.out.println(poolListEnd.get(0).getCustomer().getCustomerName());
			myUber.finish(poolListEnd.get(i));
			poolListEnd.get(i).setStatus(RideStatus.COMPLETED);
			if(i !=( n-1)) {
				driver.changeStateTo(DriverState.ONARIDE, poolListEnd.get(i).getArrivalDate());
			}
			int randomNum = ThreadLocalRandom.current().nextInt(0, 6);
			myUber.mark(poolListEnd.get(i),randomNum);
		}
		System.out.println(s);
	}
}
