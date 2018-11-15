package UberPoolRide;
import java.util.ArrayList;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;

public interface UberPoolObeservable {
	public abstract void addObserver(UberPoolConcreteObserver o);
	public abstract void removeObserver(UberPoolConcreteObserver o);
	public abstract void notifyObserver();
	
}
