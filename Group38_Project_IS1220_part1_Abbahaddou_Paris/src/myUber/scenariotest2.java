package myUber;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import Cars.Car;
import GPS.GPScoordinates;
import Rides.Pool;
import Rides.Ride;
import Rides.RideFactory;
import Rides.RideStatus;
import Rides.UberPool;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;
import customersAndDrivers.DriverState;

public class scenariotest2 {
	@Test
	void getARide() throws Exception {

		MyUber myUber = new MyUber();

		Customer cust = myUber.createCustomer("Antoine", "Paris", new GPScoordinates(5, 3), 534);
		Customer cust2 = myUber.createCustomer("Yassine", "Abbahaddou", new GPScoordinates(7, 6), 681);
		Car car1 = myUber.createCar("Standard",new GPScoordinates(11, 6));
		Car car2 = myUber.createCar("Standard",new GPScoordinates(1, 16));
		
		Driver driver1 = myUber.createDriver(car1, "Arnault"  , "Lapitre");
		Driver driver2 = myUber.createDriver(car2, "Paolo", "Ballarini");
		//requireRide send a message in the user messageBox with all prices
		// the function should also return a RideSearching object
		@SuppressWarnings("deprecation")
		RideFactory rideFac = myUber.requireRide(cust,new GPScoordinates(10,8),new Date(2018,8,5,27,00));
		RideFactory rideFac2 = myUber.requireRide(cust2,new GPScoordinates(11,15),new Date(2018,8,5,27,00));
		try {		
		Ride ride = rideFac.require(myUber,"uberPool");
		Ride ride2 = rideFac2.require(myUber,"uberPool");
		ArrayList<Ride> poolList = new ArrayList<Ride>();
		poolList.add(ride);
		poolList.add(ride2);
		Pool pool1 = new Pool(poolList);
		
		//Pool pool = ride.getPool();
		ArrayList<Driver> list1 = new ArrayList<Driver>(myUber.getDriverList());
		Driver driver = pool1.getDriver(list1);
		
		
		
		//myUber.confirm(driver, pool1);
		
		for (Ride ride3 : poolList) {
			ride3.setStatus(RideStatus.CONFIRMED);
			ride3.setDriver(driver);
			myUber.confirm(ride3);
			
		}
		
		ArrayList<Ride> poolListStart = new ArrayList<Ride>(pool1.startingPoolOrder(driver));
		ArrayList<Ride> poolListEnd = new ArrayList<Ride>(pool1.endingPoolOrder(driver));
		//driver.startOne(pool); // start first ride
		//driver.finishOne(pool);
		
		for (Ride ride3 : poolListStart) {
			
			myUber.start(ride3);
		}/*
		for (Ride ride3 : poolListEnd) {
			//System.out.println(ride3.getCustomer().getCreditCardNumber());
			//ride3.setStatus(RideStatus.ONGOING);
			//driver.changeStateTo(DriverState.ONDUTY,ride3.getArrivalDate());
			ride3.setStatus(RideStatus.ONGOING);
			System.out.println(ride3.getCustomer().getCustomerName());
			driver.finish(ride3);
			ride3.setStatus(RideStatus.COMPLETED);
			ride3.getCustomer().mark(ride3,4);
		}*/
		
		
		poolListEnd.get(0).setStatus(RideStatus.ONGOING);
		//System.out.println(poolListEnd.get(0).getCustomer().getCustomerName());
		myUber.finish(poolListEnd.get(0));
		poolListEnd.get(0).setStatus(RideStatus.COMPLETED);
		myUber.mark(poolListEnd.get(0),4);
		
		driver.changeStateTo(DriverState.ONARIDE, poolListEnd.get(0).getArrivalDate());
		
		poolListEnd.get(1).setStatus(RideStatus.ONGOING);
		//System.out.println(poolListEnd.get(1).getCustomer().getCustomerName());
		myUber.finish(poolListEnd.get(1));
		poolListEnd.get(1).setStatus(RideStatus.COMPLETED);
		myUber.mark(poolListEnd.get(1),2);
		
		// add mark  
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}
}
