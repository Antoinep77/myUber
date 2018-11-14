 package customersAndDrivers;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Cars.Car;

import Cars.CarState;

import Rides.Ride;
import Rides.RideStatus;


public class Driver {
	
	public static ArrayList<Driver> listOfDivers;    // list of all drivers 
	public static int biggestDriverID;  // Integer : number of drivers in the myUber system
	private Car car;
	private String driverName;   // the name of the driver
	private String driverSurName;  // the surname of the driver
	private int driverID;            // numerical ID of the driver
	private DriverState driverState;  // the state of the driver --> off-duty, on-duty, off-line on on-a-ride
	private double driverAmount;  // total amount cashed by the driver
	private double numOfRides;    // number of rides done by the driver
	private ArrayList<Double> driverMarks;
	private Map<String, Integer> driversTimes;
	
	private Time lastTimeOfLastStateChange;
	
	//  Constructor 
	public Driver(Car car, String driverName, String driverSurName) {
		super();
		this.car = car;
		this.driverName = driverName;
		this.driverSurName = driverSurName;
		this.driverID = biggestDriverID + 1;
		biggestDriverID++;
		this.driverState = DriverState.OFFDUTY;
		this.driverAmount = 0.0;
		this.numOfRides = 0;
		driversTimes.put("on-dutty", 0);   
		driversTimes.put("off-duty", 0);
		driversTimes.put("on-a-ride", 0);
		listOfDivers.add(this);
		this.lastTimeOfLastStateChange = new Time(0,0,0);
	}
	//   Setters and getters 
	
	
	public String getDriverName() {
		return driverName;
	}

	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}

	public String getDriverSurName() {
		return driverSurName;
	}

	public int getDriverID() {
		return driverID;
	}

	public DriverState getDriverState() {
		return driverState;
	}

	public void setDriverState(DriverState driverState) {
		this.driverState = driverState;
	}
	
	
	public double getDriverAmount() {
		return driverAmount;
	}
	public void setDriverAmount(double driverAmount) {
		this.driverAmount = driverAmount;
	}
	public Map<String, Integer> getDriversTimes() {
		return driversTimes;
	}
	public void setDriversTimes(Map<String, Integer> driversTimes) {
		this.driversTimes = driversTimes;
	}
	
	public static int getBiggestDriverID() {
		return biggestDriverID;
	}

	public double getNumOfRides() {
		return numOfRides;
	}
	public void setNumOfRides(double numOfRides) {
		this.numOfRides = numOfRides;
	}
	
	public ArrayList<Double> getDriverMarks() {
		return driverMarks;
	}
	
	//only works if this is the driver of ride and the ride is confirmed
	public void start(Ride ride) {
		if(ride.getDriver() == this && ride.getStatus() == RideStatus.CONFIRMED) {
			setDriverState(DriverState.ONARIDE);
		}
	}
	//only works if this is the driver of ride and the ride is confirmed
	//Also change the position of the driver to the destination postion
	public void finish(Ride ride) {
		if(ride.getDriver() == this && ride.getStatus() == RideStatus.ONGOING) {
		setDriverState(DriverState.ONDUTY);
		ride.setStatus(RideStatus.COMPLETED);
		this.car.setCarPosition(ride.getDestinationPoint());
		}
	}
	
	public int getTimeInSeconde(Time t) {
		return 3600*t.getHours() + 60*t.getMinutes() + t.getSeconds();
	}
	public boolean changeStateTo(DriverState newdriverState, Time timeOfChange) {
		boolean change;
		DriverState previousState = this.driverState;
		if(this.driverState == newdriverState) {
			change = false;
		}else if(this.driverState == DriverState.OFFDUTY && newdriverState != DriverState.OFFDUTY && this.car.getCarState() == CarState.TAKED) {
			this.driverState = DriverState.OFFDUTY;
			change =  false;
		}else if(this.driverState == DriverState.OFFDUTY && newdriverState != DriverState.OFFDUTY && this.car.getCarState() == CarState.AVAILABLE) {
			car.setCarState(CarState.TAKED);
			this.driverState = newdriverState;
			change = true;
		}else if(this.driverState != DriverState.OFFDUTY && newdriverState == DriverState.OFFDUTY) {
			car.setCarState(CarState.AVAILABLE);
			this.driverState = newdriverState;
			change = true;
		}else {  // no need to search a car
			this.driverState = newdriverState;
			change = true;
		}
		int differenceOfTime = getTimeInSeconde(this.lastTimeOfLastStateChange) - getTimeInSeconde(timeOfChange);
		
		if(change == true) {
			this.lastTimeOfLastStateChange = timeOfChange;
			switch(previousState) {
			case OFFLINE:
				break;
			case OFFDUTY:driversTimes.put("off-duty", differenceOfTime);
				break;
			case ONARIDE:driversTimes.put("on-a-ride", differenceOfTime);
				break;
			case ONDUTY:driversTimes.put("on-dutty", differenceOfTime);   
				break;
			default:
				break;
			
			}
		}
		
		return change;
	}
	//toString   
	@Override
	public String toString() {
		return "Driver : driverName=" + driverName + ", driverSurName=" + driverSurName + ", driverID=" + driverID
				+ ", driverState=" + driverState;
	}
	//  method for adding a new amount
	public void addAmount(double amount) {
		
		this.setDriverAmount(this.getDriverAmount() + amount);
	} 
	
	// method for adding a new ride to the number of ride
	public void addNewRide() {
		this.setNumOfRides(this.getNumOfRides() +1 );
	}
	// method to add extra on-duty time
	public void addOnDutyTime(int onDutyTime) {
		int newOnDutyTime = this.driversTimes.get("on-dutty");
		this.driversTimes.put("on-dutty", newOnDutyTime);
	}
	
	
	public void addOffDutyTime(int offDutyTime) {
		int newOffDutyTime = this.driversTimes.get("off-duty");
		this.driversTimes.put("off-duty", newOffDutyTime);
	}
	
	public void addOnRideTime(int onRideTime) {
		int newOnRideTime = this.driversTimes.get("on-a-ride");
		this.driversTimes.put("on-a-ride", newOnRideTime);
	}
	
	public double getOnDutyRateOfDriving() {
		double numerator = this.driversTimes.get("on-a-ride");
		double denominator = this.driversTimes.get("on-dutty");
		if(denominator != 0) {
			return numerator/denominator;
		}else {
			return 0.0;
		}
	}
	
	public double getRateOfActivity() {
		double numerator = this.driversTimes.get("on-dutty") + this.driversTimes.get("on-a-ride");
		double denominator = this.driversTimes.get("on-dutty") + this.driversTimes.get("on-a-ride") + this.driversTimes.get("off-duty");
		if(denominator != 0) {
			return numerator/denominator;
		}else {
			return 0.0;
		}
	}
	
	public double getOccupationRate() {
		double numerator = this.driversTimes.get("on-dutty");
		double denominator = this.driversTimes.get("on-a-ride") ;
		if(denominator != 0) {
			return numerator/denominator;
		}else {
			return 0.0;
		}
	}
	public void addOneMark(double newMark) {
		driverMarks.add(newMark);
	}
	
	public double avrageOfMarks() {
		int sum = 0;
	      for (int i=0; i< driverMarks.size(); i++) {
	            sum += driverMarks.get(i);
	      }
	      return sum / driverMarks.size();
	}
	
}
