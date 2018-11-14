package Cars;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.UberBlack;
import Rides.UberPool;
import Rides.UberVan;
import Rides.UberX;
import customersAndDrivers.Driver;

public class Car {
	
<<<<<<< HEAD
	private GPScoordinates carPosition;  // the GPS coordinates of the car
	private int NumberFreeSeats;  // the number of seats
	private String carID;   // the alphanumercial ID
	private CarState carState;
	
	public Car(GPScoordinates carPosition, int numberFreeSeats, String carID) {
		super();
=======
	private GPScoordinates carPosition;
	private int numberFreeSeats;
	private String carID;
	public Car(GPScoordinates carPosition, String carID) {
>>>>>>> branch 'master' of git@github.com:Antoinep77/myUber.git
		this.carPosition = carPosition;
		this.carID = carID;
		this.carState = CarState.AVAILABLE;
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
	public boolean isCompatibleWithTheRide(Ride ride) {
		if(ride instanceof UberBlack && this instanceof Standard) {
			return true;
		}else if(ride instanceof UberX && this instanceof Berlin) {
			return true;
		}else if(ride instanceof UberVan && this instanceof Van) {
			return true;
		}else if(ride instanceof UberPool && this instanceof Standard) {
			return true;
		}else {
			return false;
		}
			
	}

	

}
