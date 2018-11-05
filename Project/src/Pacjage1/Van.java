package Pacjage1;

public class Van extends Car{
	static int VanNumber = 0;
	private Driver driver;
	private GPScoordiantes carPosition;
	private int NumberFreeSeats = 6;
	// no carID as attribut because we know that the carID is VanN
	public Van(Driver driver, GPScoordiantes carPosition, int numberFreeSeats) {
		super(driver, carPosition, numberFreeSeats, "Van" + Integer.toString(VanNumber+1));
		VanNumber++;
		
	}
	
	
	}
