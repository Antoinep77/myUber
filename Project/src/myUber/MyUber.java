package myUber;

import java.sql.Driver;
import java.util.List;

import Cars.Car;
import customersAndDrivers.Customer;

public class MyUber {
	
	private static List<Customer> customerList;
	private static List<Driver> driverList;
	private static List<Car> carList;

	public MyUber(int nc, int nd, int nu) {
		// TODO Auto-generated constructor stub
	}
	
	//Generate a myUber instance with a text file as scenario
	public MyUber(String filepath) {
		
	}

	
	//
	public Customer getUserWithId(int i) {
		for(Customer cust: customerList) {
			if (cust.getCustomerID() == i) {
				return cust;
			}
		}
		return null;
		
	}

}
