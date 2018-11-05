package Pacjage1;

public class Car {
	private Driver driver;
	private GPScoordiantes carPosition;
	private int NumberFreeSeats;
	private String carID;
	public Car(Driver driver, GPScoordiantes carPosition, int numberFreeSeats, String carID) {
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
	public GPScoordiantes getCarPosition() {
		return carPosition;
	}
	public void setCarPosition(GPScoordiantes carPosition) {
		this.carPosition = carPosition;
	}


	

}
