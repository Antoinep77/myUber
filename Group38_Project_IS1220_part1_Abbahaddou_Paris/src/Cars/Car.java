package Cars;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.UberBlack;
import Rides.UberPool;
import Rides.UberVan;
import Rides.UberX;
import customersAndDrivers.Driver;


/**
 * @author yassine & Antoine
 *
 */


public abstract class Car {

	
	/**
	 * GPScoordinates GPS coordinates of the car
	 */
	private GPScoordinates carPosition;  // the GPS coordinates of the car

	/**
	 * Int the number of seats 
	 */
	private int numberFreeSeats;  // the number of seats
	/**
	 * String the alphanumerical ID of the car
	 */
	private String carID;   // the alphanumerical ID
	

	/**
	 * CarState the state of the car
	 */


	private CarState carState= CarState.AVAILABLE;
	

	/**
	 * @param carPosition  the position of the car 
	 * @param carID the alphanumerical ID of the car

	 */


	public Car(GPScoordinates carPosition) {

		this.carPosition = carPosition;
	}

	public Car(GPScoordinates carPosition,String carID) {
		super();
		this.carID = carID;
		this.carPosition = carPosition;
	}

	
	@Override
	public String toString() {
		return "\n ID =" + carID +", position=" + carPosition + ", status =" + carState;
	}




	/**
	 * @return return the position of the car
	 */
	public GPScoordinates getCarPosition() {
		return carPosition;
	}
	/**
	 * @param carPosition to set the position of the car
	 */
	public void setCarPosition(GPScoordinates carPosition) {
		this.carPosition = carPosition;
	}

	/**
	 * @return return the state of the car
	 */
	public CarState getCarState() {
		return carState;
	}

	/**
	 * @param carState to set the state of the car
	 */
	
	public void setCarState(CarState carState) {
		this.carState = carState;
	}
	
	
	/**
	 * Setter
	 * @return the ID of the car
	 */
	public String getCarID() {
		return carID;
	}


	// check if the ride and the car are compatible 
	/**
	 * methods checks if the ride and the car are compatible 
	 * @param ride
	 * @return true if the ride is compatible with the car, otherwise it returns false
	 */
	public abstract boolean isCompatibleWithTheRide(Ride ride);
	
	
	
	
}
