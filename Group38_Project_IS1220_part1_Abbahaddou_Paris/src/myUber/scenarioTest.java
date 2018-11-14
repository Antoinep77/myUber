package myUber;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;

import org.junit.jupiter.api.Test;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.RideFactory;
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
		RideFactory rideReq = myUber.requireRide(cust,
				new GPScoordinates(10,8),new Time(8,27,00));
		try {
		Ride ride = rideReq.require(myUber,"UberX");
		
		
		Driver driver1 = ride.getDriver();
		myUber.unconfirm(driver1,ride);
		
		Driver driver2 = ride.getDriver();
		myUber.confirm(driver2,ride);
		
		driver2.start(ride);
		
		cust.cancel(ride);
		
		driver2.finish(ride);
		
		cust.mark(ride,4);
		}
		catch(Exception e) {
			
		}
		
	}

}
