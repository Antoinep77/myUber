package Cars;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.UberBlack;
import Rides.UberPool;
import Rides.UberVan;
import Rides.UberX;
import customersAndDrivers.Driver;

public abstract class Car {
	
	private GPScoordinates carPosition;  // the GPS coordinates of the car
	private CarState carState= CarState.AVAILABLE;
	
	public Car(GPScoordinates carPosition) {
		this.carPosition = carPosition;
	}
	
	public GPScoordinates getCarPosition() {
		return carPosition;
	}
	public void setCarPosition(GPScoordinates carPosition) {
		this.carPosition = carPosition;
	}

	public CarState getCarState() {
		return carState;
	}

	public void setCarState(CarState carState) {
		this.carState = carState;
	}
	
	// check if the ride and the car are compatible 
	public abstract boolean isCompatibleWithTheRide(Ride ride);
				

}
