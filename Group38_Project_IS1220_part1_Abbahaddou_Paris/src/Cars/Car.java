package Cars;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;

public class Car {
	
	private GPScoordinates carPosition;
	private int numberFreeSeats;
	private String carID;
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


	

}
