package JUNITtests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import GPS.GPScoordinates;
import customersAndDrivers.Customer;
import sorting.MostChargedSorting;

class CustomerSortingTest {

	@Test
	void test() {
		Customer c1 = new Customer("name1", "surname1", new GPScoordinates(1, 2), 123456);
		Customer c2 = new Customer("name2", "surname2", new GPScoordinates(1, 2), 789101);
		
		ArrayList<Customer> listCustomers = new ArrayList<Customer>();
		listCustomers.add(c1);
		listCustomers.add(c2);
		c1.spendAmount(10);
		c1.spendAmount(5);
		c1.spendAmount(5);
		c1.spendAmount(7);
		c1.spendAmount(6);
		
		
		c2.spendAmount(6);
		c2.spendAmount(6);
		c2.spendAmount(2);
		c2.spendAmount(4);
		
		MostChargedSorting mostChargedSorting = new MostChargedSorting();
		ArrayList<Customer> listCustomersSorted = mostChargedSorting.sortCustomers(listCustomers);
		assertEquals(c1.getTotaleAmountPaid(), 33);
		assertEquals(c2.getTotaleAmountPaid(), 18);
		assertTrue(listCustomersSorted.get(1)== c2);
		assertTrue(listCustomersSorted.get(0)== c1);
	}

	@Test
	void test2() {
		Customer c1 = new Customer("name1", "surname1", new GPScoordinates(1, 2), 123456);
		Customer c2 = new Customer("name2", "surname2", new GPScoordinates(1, 2), 789101);
		
		ArrayList<Customer> listCustomers = new ArrayList<Customer>();
		listCustomers.add(c1);
		listCustomers.add(c2);
		c1.spendAmount(5);
		c1.spendAmount(5);
		c1.spendAmount(7);
		c1.spendAmount(6);
		c1.spendAmount(6);
		c1.spendAmount(8);
		
		
		c2.spendAmount(6);
		c2.spendAmount(6);
		c2.spendAmount(2);
		c2.spendAmount(4);
		MostChargedSorting mostChargedSorting = new MostChargedSorting();
		ArrayList<Customer> listCustomersSorted = mostChargedSorting.sortCustomers(listCustomers);
		assertEquals(c1.getNumOfRide(), 6);
		assertEquals(c2.getNumOfRide(), 4);
		assertTrue(listCustomersSorted.get(1)== c2);
		assertTrue(listCustomersSorted.get(0)== c1);
	}
}
