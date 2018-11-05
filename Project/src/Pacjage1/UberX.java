package Pacjage1;
// concrete element
public class UberX implements Ride{
	private Car car;
	private Customer customer;
	public UberX(Car car, Customer customer) {
		super();
		this.car = car;
		this.customer = customer;
	}
	
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
	@Override
	public void accept(CostVisitor costVisitor) {
		costVisitor.visit(this);
	}

}
