package JUNITtests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import Cars.Berlin;
import Cars.Car;
import GPS.GPScoordinates;
import Rides.TrafficCondition;
import Rides.UberBlack;
import customersAndDrivers.Customer;

class UberBlackTest {

	@Test
	void testingBasicRates() {
		Customer cust = new Customer("name", "SurName", new GPScoordinates(4, 8),12358);
		UberBlack u = new UberBlack(cust, new GPScoordinates(5, 2), new GPScoordinates(5, 2), new Date(2018,12,6,13,22,0), TrafficCondition.LOW);
	
		assertEquals(u.basicRates(), 6.2);
		
	}
	@Test
	void testingBasicRates2() {
		Customer cust = new Customer("name", "SurName", new GPScoordinates(4, 8),12358);
		UberBlack u = new UberBlack(cust, new GPScoordinates(5, 8), new GPScoordinates(17, 6), new Date(2018,12,6,13,22,0), TrafficCondition.LOW);

		assertEquals(u.basicRates(), 3.25);
		
	}
	@Test
	void testingBasicRates3() {
		Customer cust = new Customer("name", "SurName", new GPScoordinates(4, 8),12358);
		UberBlack u = new UberBlack(cust, new GPScoordinates(1, 2), new GPScoordinates(1.5, 2), new Date(2018,12,6,13,22,0), TrafficCondition.LOW);
	
		assertEquals(u.basicRates(), 6.2);
		
	}

}
