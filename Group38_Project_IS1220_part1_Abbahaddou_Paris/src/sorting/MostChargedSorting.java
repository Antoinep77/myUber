package sorting;

import java.util.ArrayList;

import customersAndDrivers.Customer;

public class MostChargedSorting implements CustomerSorting{
	@Override
	public ArrayList<Customer> sortCustomers(ArrayList<Customer> listCustomerToSort){
		ArrayList<Customer> listCustomer = new ArrayList<Customer>(listCustomerToSort);
		listCustomer.sort((Customer c1,Customer c2) -> {
			if(c1.getTotaleAmountPaid() < c2.getTotaleAmountPaid() ) {
				return 1;
			}
			if(c1.getTotaleAmountPaid() > c2.getTotaleAmountPaid() ) {
				return -1;
			}
			return 0;
		} );
		return listCustomer;
	}
}
