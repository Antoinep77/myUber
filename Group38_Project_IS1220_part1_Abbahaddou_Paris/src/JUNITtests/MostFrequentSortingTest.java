package JUNITtests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import GPS.GPScoordinates;
import customersAndDrivers.Customer;
import sorting.MostFrequentSorting;

class MostFrequentSortingTest {

	@Test
	void testingTheSortedList() {
		Customer c1 = new Customer("name1", "surname1", new GPScoordinates(1, 2), 123456);
		Customer c2 = new Customer("name2", "surname2", new GPScoordinates(1, 2), 789101);
		
		ArrayList<Customer> listCustomers = new ArrayList<Customer>();
		listCustomers.add(c1);
		listCustomers.add(c2);

		for(int i=0; i<5 ; i++) {
			c1.addOneRide();
		}
		for(int i=0; i<7 ; i++) {
			c2.addOneRide();
		}
		
	
		MostFrequentSorting mostFrequentSortingTest = new MostFrequentSorting();
		ArrayList<Customer> listCustomersSorted = mostFrequentSortingTest.sortCustomers(listCustomers);   
		assertTrue(listCustomersSorted.get(0)== c2);
		assertTrue(listCustomersSorted.get(1)== c1);
	}

}
