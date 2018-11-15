package sorting;

import java.util.ArrayList;

import customersAndDrivers.Customer;


public class MostFrequentSorting implements CustomerSorting{
	@Override
	public ArrayList<Customer> sortCustomers(ArrayList<Customer> listCustomerToSort){
		ArrayList<Customer> listCustomer = new ArrayList<Customer>(listCustomerToSort);
		listCustomer.sort((Customer c1,Customer c2) -> {
			if(c1.getNumOfRide() < c2.getNumOfRide() ) {
				return 1;
			}
			if(c1.getNumOfRide() > c2.getNumOfRide() ) {
				return -1;
			}
			return 0;
		} );
		return listCustomer;
	}
}
