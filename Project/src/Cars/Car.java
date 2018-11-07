package Cars;

import GPS.GPScoordinates;
import customersAndDrivers.Driver;

public class Car {
	private Driver driver;
	private GPScoordinates carPosition;
	private int NumberFreeSeats;
	private String carID;
	public Car(Driver driver, GPScoordinates carPosition, int numberFreeSeats, String carID) {
		super();
		this.driver = driver;
		this.carPosition = carPosition;
		NumberFreeSeats = numberFreeSeats;
		this.carID = carID;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public GPScoordinates getCarPosition() {
		return carPosition;
	}
	public void setCarPosition(GPScoordinates carPosition) {
		this.carPosition = carPosition;
	}


	

}
