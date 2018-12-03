package Cars;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.TrafficCondition;
import Rides.UberBlack;
import Rides.UberPool;
import Rides.UberVan;
import Rides.UberX;
import customersAndDrivers.Customer;

class CarTest {

	@Test
	void test1() {
		Car car1 = new Van(new GPScoordinates(5, 1));
		assertEquals(car1.getCarState(),CarState.AVAILABLE);
		assertTrue(car1.getCarID().equals("Van1"));

	}
	@Test
	void test2() {
		Car car2 = new Van(new GPScoordinates(5, 1));
		Car car3 = new Van(new GPScoordinates(6, 7));
		Car car4 = new Berlin(new GPScoordinates(6, 7));
		Car car5 = new Standard(new GPScoordinates(6, 7));
		assertEquals(car2.getCarID(),"Van2");
		assertEquals(car3.getCarID(),"Van3");
		assertEquals(car4.getCarID(),"Berlin1");
		assertEquals(car5.getCarID(),"Standard1");
	}
	@Test
	void test3() {
		
		Car carVan = new Van(new GPScoordinates(6, 7));
		Car carBerlin = new Berlin(new GPScoordinates(6, 7));
		Car carStandar = new Standard(new GPScoordinates(6, 7));
		Customer customer = new Customer("name", "surname", new GPScoordinates(2, 1), 123547);
		Date date =new Date(2018, 12, 3, 19,5,0);
		Ride rideBlack = new UberBlack(customer, new GPScoordinates(1, 1), new GPScoordinates(2, 6), date, TrafficCondition.HEAVY);
		Ride ridePool = new UberPool(customer, new GPScoordinates(2, 3), new GPScoordinates(4, 1), date, TrafficCondition.MEDIUM);
		Ride rideVan = new UberVan(customer, new GPScoordinates(9, 1), new GPScoordinates(7, 2), date, TrafficCondition.HEAVY);
		Ride rideX = new UberX(customer, new GPScoordinates(4, 5), new GPScoordinates(4, 11), date, TrafficCondition.LOW);
		assertTrue(carVan.isCompatibleWithTheRide(rideVan));
		assertTrue(carBerlin.isCompatibleWithTheRide(rideBlack ));
		assertTrue(carStandar.isCompatibleWithTheRide(rideX));
		assertTrue(carStandar.isCompatibleWithTheRide(ridePool));
	}
	
	

}
