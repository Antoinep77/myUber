package PoolObseerver;

import Rides.Ride;

public class ConcreteObserver implements Observer{
	private Ride ride;
	
	public ConcreteObserver(Ride ride) {
		super();
		this.ride = ride;
	}
	

	public Ride getRide() {
		return ride;
	}

	
	@Override
	public void update(Observable observable) {
		if(observable instanceof ConcreteObservable) {
			int n = ((ConcreteObservable) observable).listOfRides().size();
			for(Observer o : ((ConcreteObservable) observable).getObservers()) {
				if(n==1) {
					((ConcreteObserver) o).ride.getCustomer().addMessageToBox("You are the first customer in this pool ride");
				}else {
					((ConcreteObserver) o).ride.getCustomer().addMessageToBox("A new customer is taking place with you in the car, the number of customers now is "+ Integer.toString(n));
				}
				
			}
		}
		
	}
}
