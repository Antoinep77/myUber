package Cars;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;

public class Car {
	
	private GPScoordinates carPosition;
	private int NumberFreeSeats;
	private String carID;
	public Car(GPScoordinates carPosition, int numberFreeSeats, String carID) {
		super();
		this.carPosition = carPosition;
		NumberFreeSeats = numberFreeSeats;
		this.carID = carID;
	}
	
	public GPScoordinates getCarPosition() {
		return carPosition;
	}
	public void setCarPosition(GPScoordinates carPosition) {
		this.carPosition = carPosition;
	}


	

}
