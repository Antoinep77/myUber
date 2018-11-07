package Rides;

import Cars.Car;
import customersAndDrivers.Customer;

//Element interface
public interface Ride {
	
	public void accept(CostVisitor costVisitor);
}
