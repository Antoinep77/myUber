package JUNITtests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import Cars.Berlin;
import Cars.Car;
import Cars.CarState;
import Cars.Standard;
import Cars.Van;
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
	void testing() {
		Car car1 = new Van(new GPScoordinates(5, 1));
		assertEquals(car1.getCarState(),CarState.AVAILABLE);

	}
	
	@Test
	void test3() {
		Car carStandar = new Standard(new GPScoordinates(6, 7));
		Customer customer = new Customer("name", "surname", new GPScoordinates(2, 1), 123547);
		Date date =new Date(2018, 12, 3, 19,5,0);
		Ride rideX = new UberX(customer, new GPScoordinates(4, 5), new GPScoordinates(4, 11), date, TrafficCondition.LOW);
		assertTrue(carStandar.isCompatibleWithTheRide(rideX));
	}
	
	@Test
	void test33() {
		
		Car carVan = new Van(new GPScoordinates(6, 7));
		Customer customer = new Customer("name", "surname", new GPScoordinates(2, 1), 123547);
		Date date =new Date(2018, 12, 3, 19,5,0);
		Ride rideVan = new UberVan(customer, new GPScoordinates(9, 1), new GPScoordinates(7, 2), date, TrafficCondition.HEAVY);
		assertTrue(carVan.isCompatibleWithTheRide(rideVan));
		
	}
	@Test
	void test333() {
		Car carBerlin = new Berlin(new GPScoordinates(6, 7));
		Customer customer = new Customer("name", "surname", new GPScoordinates(2, 1), 123547);
		Date date =new Date(2018, 12, 3, 19,5,0);
		Ride rideBlack = new UberBlack(customer, new GPScoordinates(1, 1), new GPScoordinates(2, 6), date, TrafficCondition.HEAVY);
		assertTrue(carBerlin.isCompatibleWithTheRide(rideBlack ));
		
	}
	@Test
	void test3333() {
		
		Car carStandar = new Standard(new GPScoordinates(6, 7));
		Customer customer = new Customer("name", "surname", new GPScoordinates(2, 1), 123547);
		Date date =new Date(2018, 12, 3, 19,5,0);
		Ride ridePool = new UberPool(customer, new GPScoordinates(2, 3), new GPScoordinates(4, 1), date, TrafficCondition.MEDIUM);
		assertTrue(carStandar.isCompatibleWithTheRide(ridePool));
	}
	

}
