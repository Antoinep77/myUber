package Pacjage1;

public class Berlin extends Car{
	static int BerlinNumber = 0;
	private Driver driver;
	private GPScoordiantes carPosition;
	private int NumberFreeSeats = 4;
	// no carID as attribut because we know that the carID is BerlinN
	public Berlin(Driver driver, GPScoordiantes carPosition, int numberFreeSeats) {
		super(driver, carPosition, numberFreeSeats, "Berlin" + Integer.toString(BerlinNumber+1));
		BerlinNumber++;
		
	}
}
