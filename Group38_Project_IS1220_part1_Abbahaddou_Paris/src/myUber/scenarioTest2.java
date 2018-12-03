package myUber;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;

import org.junit.jupiter.api.Test;

import Cars.Car;
import GPS.GPScoordinates;
import PoolObseerver.ConcreteObservable;
import PoolObseerver.ConcreteObserver;
import PoolObseerver.*;
import Rides.Ride;
import Rides.RideFactory;
import Rides.RideStatus;
import Rides.UberPool;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;
import customersAndDrivers.DriverState;

public class scenarioTest2 {
	@Test
	void getARide() throws Exception {

		MyUber myUber = new MyUber();

		Customer cust = myUber.createCustomer("Antoine", "Paris", new GPScoordinates(5, 3), 534);
		Customer cust2 = myUber.createCustomer("Yassine", "Abbahaddou", new GPScoordinates(7, 6), 681);
		Car car1 = myUber.createCar("Standard",new GPScoordinates(11, 6));
		Car car2 = myUber.createCar("Standard",new GPScoordinates(1, 16));
		
		Driver driver1 = myUber.createDriver(car1, "Arnault"  , "Lapitre");
		Driver driver2 = myUber.createDriver(car2, "Paolo", "Ballarini");
		ArrayList<Driver> listDriver = new ArrayList<Driver>();
		listDriver.add(driver1);
		listDriver.add(driver2);
		//requireRide send a message in the user messageBox with all prices
		// the function should also return a RideSearching object
		@SuppressWarnings("deprecation")
		RideFactory rideFac = myUber.requireRide(cust,new GPScoordinates(10,8),new Date(2018,8,5,27,00));
		RideFactory rideFac2 = myUber.requireRide(cust2,new GPScoordinates(11,15),new Date(2018,8,5,27,00));
		try {		
		
		UberPool ride = (UberPool) rideFac.require(myUber,"uberPool");
		
		UberPool ride2 = (UberPool)rideFac2.require(myUber,"uberPool");
		
		ConcreteObserver c1 = new ConcreteObserver(ride);
		
		ConcreteObserver c2 = new ConcreteObserver(ride2);
		
		ConcreteObservable concreteObservable = new ConcreteObservable(new ArrayList<PoolObseerver.Observer>() ); 
		
		
		concreteObservable.registerObserver(c1);
		
		concreteObservable.registerObserver(c2);
		
		Driver driver = concreteObservable.getDriver(listDriver);
		
		for (Ride ride3 : concreteObservable.listOfRides()) {
			ride3.setStatus(RideStatus.CONFIRMED);
			ride3.setDriver(driver);
			myUber.confirm(ride3);

		}
		concreteObservable.startPoolRide(driver, myUber);
		concreteObservable.finishPoolRide(driver, myUber);
		
		
		// add mark  
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
}
