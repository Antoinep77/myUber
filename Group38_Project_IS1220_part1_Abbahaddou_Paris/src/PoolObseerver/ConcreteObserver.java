package PoolObseerver;

import Rides.Ride;

public class ConcreteObserver implements ObserverPool{
	/**
	 * attributes of the object
	 */
	private Ride ride;
	/**
	 * Constuctor
	 * @param ride uber pool ride 
	 */
	public ConcreteObserver(Ride ride) {
		super();
		this.ride = ride;
	}
	
	/**
	 * getter
	 * @return the ride of the observer
	 */
	public Ride getRide() {
		return ride;
	}

	
	@Override
	public void update(Observable observable) {
		if(observable instanceof ConcreteObservable) {
			int n = ((ConcreteObservable) observable).listOfRides().size();
			for(ObserverPool o : ((ConcreteObservable) observable).getObservers()) {
				if(n==1) {
					((ConcreteObserver) o).ride.getCustomer().addMessageToBox("You are the first customer in this pool ride");
				}else {
					((ConcreteObserver) o).ride.getCustomer().addMessageToBox("A new customer is taking place with you in the car, the number of customers now is "+ Integer.toString(n));
				}
				
			}
		}
		
	}
}
