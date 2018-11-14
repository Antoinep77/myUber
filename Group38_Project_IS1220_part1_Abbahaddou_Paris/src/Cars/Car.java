package Cars;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;

public class Car {
	
	private GPScoordinates carPosition;  // the GPS coordinates of the car
	private int NumberFreeSeats;  // the number of seats
	private String carID;   // the alphanumercial ID
	private CarState carState;
	
	public Car(GPScoordinates carPosition, int numberFreeSeats, String carID) {
		super();
		this.carPosition = carPosition;
		NumberFreeSeats = numberFreeSeats;
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
	
	

	

}
