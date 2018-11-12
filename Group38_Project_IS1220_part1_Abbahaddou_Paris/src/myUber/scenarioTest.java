package myUber;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Driver;
import java.sql.Time;

import org.junit.jupiter.api.Test;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.RideFactory;
import Rides.RideSearching;
import customersAndDrivers.Customer;

class scenarioTest {
	
	@Test
	void settingUpMyUber() {
		int nc, nd, nu;
		MyUber myUber = new MyUber(nc,nd,nu);
	}
	
	@Test
	void getARide() {
		int nc, nd, nu;
		MyUber myUber = new MyUber(nc,nd,nu);

		Customer cust = myUber.getUserWithId(0);
		
		//requireRide send a message in the user messageBox with all prices
		// the function should also return a RideSearching object
		@SuppressWarnings("deprecation")
		RideFactory rideReq = myUber.requireRide(cust, new GPScoordinates(3,7),
				new GPScoordinates(10,8),new Time(8,27,00));
		try {
		Ride ride = rideReq.require(myUber,"UberX");
		
		Driver driver1 = myUber.getNotifiedDriver(ride);
		myUber.unconfirm(driver1,ride);
		
		Driver driver2 = myUber.getNotifiedDriver(ride);
		myUber.confirm(driver2,ride);
		
		driver2.start(ride);
		driver2.finished(ride);
		
		cust.mark(driver,4);
		
		}
		catch(Exception e) {
			
		}
		
	}

}
