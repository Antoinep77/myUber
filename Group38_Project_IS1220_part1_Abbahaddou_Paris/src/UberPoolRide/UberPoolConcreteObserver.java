package UberPoolRide;
import GPS.GPScoordinates;
import Rides.Ride;
import customersAndDrivers.Customer;

public class UberPoolConcreteObserver implements UberPoolObserver{
	private Ride ride;
	
	public UberPoolConcreteObserver(Ride ride) {
		this.ride = ride;
	}
	
	public Ride getRide() {
		return ride;
	}

	public void update(UberPoolConcreteObservable observable) {
		
		
	}
	
}
