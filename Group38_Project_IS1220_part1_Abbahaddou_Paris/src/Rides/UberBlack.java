package Rides;

import Cars.Car;
import GPS.GPScoordinates;
import customersAndDrivers.Customer;

// concrete element
public class UberBlack implements Ride{
	private Car car;
	private Customer customer;
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public UberBlack(Customer customer,GPScoordinates startingPoint, 
			GPScoordinates endingPoint) {
		this.customer = customer;
	}
	@Override
	public void accept(CostVisitor costVisitor) {
		costVisitor.visit(this);
	}
}
