package Cars;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.UberBlack;
import Rides.UberPool;
import Rides.UberVan;
import Rides.UberX;
import customersAndDrivers.Driver;

public class Car {
	
	private GPScoordinates carPosition;  // the GPS coordinates of the car
	private int numberFreeSeats;  // the number of seats
	private String carID;   // the alphanumerical ID
	private CarState carState= CarState.AVAILABLE;
	
	public Car(GPScoordinates carPosition, String carID) {
		this.carPosition = carPosition;
		this.carID = carID;
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
	
			
	

	

}
