 package customersAndDrivers;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import Cars.Car;
import Cars.CarState;

import Rides.Ride;
import Rides.RideStatus;
import TimeOperation.TimeOperation;

public class Driver {
	 
	/**
	 * Int the number of drivers
	 */
	public static int biggestDriverID;  // Integer : number of drivers in the myUber system
	/**
	 * the car driven by the driver
	 */
	private Car car;
	/**
	 * the name of the driver
	 */
	private String driverName;   // the name of the driver
	/**
	 * the surname of the driver
	 */
	private String driverSurName;  // the surname of the driver
	/**
	 * the numerical IDof the driver
	 */
	private int driverID;            // numerical ID of the driver
	/**
	 *  the state of the driver
	 */
	private DriverState driverState = DriverState.OFFLINE;  // the state of the driver --> off-duty, on-duty, off-line on on-a-ride
	/** 
	 * the aggregation of all amounts received by the driver
	 */
	private double driverAmount = 0.0;  // total amount cashed by the driver
	/**
	 * Int the number of rides dones by the driver
	 */
	private int numOfRides = 0;    // number of rides done by the driver
	/**
	 * ArrayList of integers containing all the marks
	 */
	private ArrayList<Integer> driverMarks = new ArrayList<Integer>();
	/**
	 * a map where the key is a string ("on-duty", "on-a-ride","off-duty") and the value is the time spent in the appropriate state
	 */
	private Map<String, Long> driversTimes = new HashMap<String, Long>(); //the time are in ms.
	
	/**
	 * Date the last date when a the driver change his state
	 */
	private Date lastTimeOfLastStateChange;
	
	//  Constructor 
	/**
	 * Constructor
	 * @param car the car driven
	 * @param driverName the name of the driver
 	 * @param driverSurName the surname of the driver
	 */
	public Driver(Car car, String driverName, String driverSurName) {
		this.car = car;
		this.driverName = driverName;
		this.driverSurName = driverSurName;
		this.driverID = biggestDriverID + 1;
		biggestDriverID++; 
		driversTimes.put("on-duty", (long) 0);   
		driversTimes.put("off-duty", (long) 0);
		driversTimes.put("on-a-ride", (long) 0);
		this.lastTimeOfLastStateChange = new Date(0,0,0);
	}
	//   Setters and getters 
	
	
	/**
	 * @return return the driver name
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * @return return the car of the driver
	 */
	public Car getCar() {
		return car;
	}
	/**
	 * @param car to set 
	 */
	public void setCar(Car car) {
		this.car = car;
	}

	/**
	 * @return the surname of the driver
	 */
	public String getDriverSurName() {
		return driverSurName;
	}

	/**
	 * @return the ID of the driver
	 */
	public int getDriverID() {
		return driverID;
	}

	/**
	 * @return the state of the driver
	 */
	public DriverState getDriverState() {
		return driverState;
	}
	
	/**
	 * @return the amount of the driver
	 */
	public double getDriverAmount() {
		return driverAmount;
	}
	
	/**
	 * @return the map  where the key is a string ("on-duty", "on-a-ride","off-duty") and the value is the time spent in the appropriate state
	 */
	public Map<String, Long> getDriversTimes() {
		return driversTimes;
	}

	/**
	 * @return the number of rides done by the driver
	 */
	public int getNumOfRides() {
		return numOfRides;
	}
	
	/**
	 * incremente the number of rides by one
	 */
	public void addOneRide() {
		numOfRides++;
	}
	
	/**
	 * method to change the state of the driver into on-duty
	 * @param date
	 */
	public void connect(Date date) {
		this.changeStateTo(DriverState.ONDUTY, date);
	}
	
	/**
	 * method to change the state of the driver into offline
	 * @param date
	 */
	public void disconnect(Date date) {
		this.changeStateTo(DriverState.OFFLINE, date);
		
	}
	
	/**
	 * method to change the state of the driver into on-duty
	 * @param date
	 */
	public void pause(Date date) {
		this.changeStateTo(DriverState.OFFDUTY, date);
	}
	
	/**
	 * @param date
	 */
	public void unpause(Date date) {
		this.changeStateTo(DriverState.ONDUTY, date);
	}

	
	/**
	 * method for changing the state of driver
	 * @param newdriverState the new state
	 * @param dateOfChange the date of change
	 * @return return true if a change of the driver state was done
	 */
	public boolean changeStateTo(DriverState newdriverState, Date dateOfChange) {

		boolean change;
		DriverState previousState = this.driverState;
		if(this.driverState == newdriverState) {
			change = false;
		}else if(this.driverState == DriverState.OFFLINE  && this.car.getCarState() == CarState.TAKED) {
			change =  false;
		}else if(this.driverState == DriverState.OFFLINE && this.car.getCarState() == CarState.AVAILABLE) {
			car.setCarState(CarState.TAKED);
			this.driverState = newdriverState;
			change = true;
		}else if(this.driverState != DriverState.OFFLINE && newdriverState == DriverState.OFFLINE) {
			car.setCarState(CarState.AVAILABLE);
			this.driverState = newdriverState;
			change = true;
		}else {  // no need to search a car
			this.driverState = newdriverState;
			change = true;
		}

		long differenceOfTime = dateOfChange.getTime() - this.lastTimeOfLastStateChange.getTime() ;
		
		if(change == true) {
			this.lastTimeOfLastStateChange = dateOfChange;
			switch(previousState) {
			case OFFLINE:
				break;
			case OFFDUTY:addOffDutyTime(differenceOfTime);
				break;
			case ONARIDE:addOnRideTime(differenceOfTime);
				break;
			case ONDUTY:addOnDutyTime(differenceOfTime);   
				break;
			default:
				break;
				//
			
			}
		}
		
		return change;
	}
	
	//toString   
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n name =" + driverName + ", surname=" + driverSurName + ", ID =" + driverID
				+ ", status =" + driverState + ", total cashed =" + driverAmount + ", number of rides =" + numOfRides;
	}
	
	
	//  method for adding a new amount
	/**
	 * method for adding an amount to the total amount 
	 * @param amount 
	 */
	public void addAmount(double amount) {
		this.driverAmount = (this.getDriverAmount() + amount);
	} 
	

	// method to add extra on-duty time
	/**
	 * method to add a duration to all on duty time
	 * @param onDutyTime the number of ms to add
	 */
	public void addOnDutyTime(long onDutyTime) {
		long newOnDutyTime = this.driversTimes.get("on-duty") + onDutyTime;
		this.driversTimes.put("on-duty", newOnDutyTime);
	}
	
	
	/**
	 * method to add a duration to all off duty time
	 * @param offDutyTime the number of ms to add
	 */
	public void addOffDutyTime(long offDutyTime) {
		long newOffDutyTime = this.driversTimes.get("off-duty") +offDutyTime;
		this.driversTimes.put("off-duty", newOffDutyTime);
	}
	
	/**
	 * method to add a duration to all on a ride time
	 * @param onRideTime the number of ms to add
	 */
	public void addOnRideTime(long onRideTime) {
		long newOnRideTime = this.driversTimes.get("on-a-ride") + onRideTime;
		this.driversTimes.put("on-a-ride", newOnRideTime);
	} 
	
	/**
	 * @return the on-duty rate of the driver
	 */
	public double getOnDutyRateOfDriving() {
		double numerator = this.driversTimes.get("on-a-ride");
		double denominator = this.driversTimes.get("on-duty");
		if(denominator != 0) {
			return numerator/denominator;
		}else {
			return 0.0;
		}
	}
	
	/**
	 * @return the rate of activity of the driver
	 */
	public double getRateOfActivity() {
		double numerator = this.driversTimes.get("on-duty") + this.driversTimes.get("on-a-ride");
		double denominator = this.driversTimes.get("on-duty") + this.driversTimes.get("on-a-ride") + this.driversTimes.get("off-duty");
		if(denominator != 0) {
			return numerator/denominator;
		}else {
			return 0.0;
		}
	}
	
	/**
	 * @return the occupation rate of the driver
	 */
	public double getOccupationRate() {
		double numerator = this.driversTimes.get("on-duty");
		double denominator = this.driversTimes.get("on-a-ride") ;
		if(denominator != 0) {
			return numerator/denominator;
		}else {
			return 0.0;
		}
	}
	/**
	 * @param newMark the new mark to add to the list of marks
	 */
	public void addOneMark(int newMark) {
		driverMarks.add(newMark);
	}
	
	/**
	 * @return the average of all marks
	 */
	public double avrageOfMarks() {
		int sum = 0;
	      for (int i=0; i< driverMarks.size(); i++) {
	            sum += driverMarks.get(i);
	      }
	      return sum / driverMarks.size();
	}
	
}
