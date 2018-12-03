package sorting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import Cars.Berlin;
import Cars.Car;
import Cars.Van;
import GPS.GPScoordinates;
import customersAndDrivers.Driver;
import customersAndDrivers.DriverState;

class DriverSortingTest {

	@Test
	void test() {
		Car car1 = new Van(new GPScoordinates(5, 1));
		Car car2 = new Berlin(new GPScoordinates(5, 1));
		Driver driver1 = new Driver(car1, "driverName1", "driverSurName1");
		Driver driver2 = new Driver(car2, "driverName2", "driverSurName2");
		ArrayList<Driver> listDriver = new ArrayList<Driver>();
		listDriver.add(driver1);
		listDriver.add(driver2);
		driver1.addOneMark(4);
		driver1.addOneMark(5);
		driver2.addOneMark(3);
		driver2.addOneMark(1);
		MostAppreciatedSorting mostAppreciatedSorting = new MostAppreciatedSorting ();
		ArrayList<Driver> listDriverSorted =  mostAppreciatedSorting.sortDrivers(listDriver);
		assertTrue(listDriverSorted.get(0) == driver1);
		
	}
	
	@Test
	void test2() {
		Car car1 = new Van(new GPScoordinates(5, 1));
		Car car2 = new Berlin(new GPScoordinates(5, 1));
		Driver driver1 = new Driver(car1, "driverName1", "driverSurName1");
		Driver driver2 = new Driver(car2, "driverName2", "driverSurName2");
		ArrayList<Driver> listDriver = new ArrayList<Driver>();
		listDriver.add(driver1);
		listDriver.add(driver2);
		driver1.changeStateTo(DriverState.ONDUTY, new Date(2018, 12,3,19,41 ,0));
		driver1.changeStateTo(DriverState.ONARIDE, new Date(2018, 12,3,19,51 ,0));
		driver1.changeStateTo(DriverState.OFFLINE, new Date(2018, 12,3,19,59 ,0));
		
		
		driver2.changeStateTo(DriverState.ONDUTY, new Date(2018, 12,3,19,41 ,0));
		driver2.changeStateTo(DriverState.ONARIDE, new Date(2018, 12,3,19,51 ,0));
		driver2.changeStateTo(DriverState.OFFLINE, new Date(2018, 12,3,20,1 ,0));
		
		
		LeastOccupiedSorting leastOccupiedSorting = new LeastOccupiedSorting();
		ArrayList<Driver> listDriverSorted =  leastOccupiedSorting.sortDrivers(listDriver);
		assertEquals(driver1.getOccupationRate(), 1.25);
		assertEquals(driver2.getOccupationRate(), 1);
		assertTrue(listDriverSorted.get(0) == driver2);
		
	}

}
