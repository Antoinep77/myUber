package Rides;

import Cars.Car;
import Pacjage1.Customer;

//concrete element
public class UberVan implements Ride{
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
	public UberVan(Car car, Customer customer) {
		super();
		this.car = car;
		this.customer = customer;
	}
	@Override
	public void accept(CostVisitor costVisitor) {
		costVisitor.visit(this);
	}
}
