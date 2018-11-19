package sorting;

import java.util.ArrayList;
import java.util.List;

import customersAndDrivers.Customer;
import customersAndDrivers.Driver;

public interface CustomerSorting {
	/**
	 * method to sort the customers
	 * @param listCustomerToSort the list of customers to sort
	 * @return the list of drivers sorted
	 */
	public abstract ArrayList<Customer> sortCustomers(ArrayList<Customer> listCustomerToSort);
	
	}
