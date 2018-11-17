package myUber;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.util.Date;

import org.junit.jupiter.api.Test;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.RideFactory;
import Rides.UberPool;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;

class scenarioTest {
	

	
	@Test
	void getARide() {
		
		MyUber myUber = new MyUber();

		Customer cust = myUber.createCustomer("Antoine", "Paris", 
				new GPScoordinates(5, 3), 534);
		
		//requireRide send a message in the user messageBox with all prices
		// the function should also return a RideSearching object
		@SuppressWarnings("deprecation")
		RideFactory rideFac = myUber.requireRide(cust,
				new GPScoordinates(10,8),new Date(2018,8,5,27,00));
		try {		
		Ride ride = rideFac.require(myUber,"uberX");
		if (ride instanceof UberPool) {/*
			Pool pool = ride.getPool();
			Driver driver = pool.getDriver();
			myUber.confirm(driver, pool);
			driver.startOne(pool); // start first ride
			driver.finishOne(pool);*/
		}
		else {
		Driver driver1 = ride.getDriver();
		myUber.refuse(driver1,ride);


		Driver driver2 = ride.getDriver();
		myUber.confirm(driver2,ride);
		
		driver2.start(ride);
		
		cust.cancel(ride);
		
		driver2.finish(ride);
		
		cust.mark(ride,4);
		
		System.out.println("");
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
