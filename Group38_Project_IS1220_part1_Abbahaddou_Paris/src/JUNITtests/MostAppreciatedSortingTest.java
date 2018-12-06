package JUNITtests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Cars.Berlin;
import Cars.Car;
import Cars.Van;
import GPS.GPScoordinates;
import customersAndDrivers.Driver;
import sorting.MostAppreciatedSorting;

class MostAppreciatedSortingTest {

	@Test
	void testintTheOrderOfTheSortedList() {
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

}
