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
	 
	public static int biggestDriverID;  // Integer : number of drivers in the myUber system
	private Car car;
	private String driverName;   // the name of the driver
	private String driverSurName;  // the surname of the driver
	private int driverID;            // numerical ID of the driver
	private DriverState driverState = DriverState.ONDUTY;  // the state of the driver --> off-duty, on-duty, off-line on on-a-ride
	private double driverAmount = 0.0;  // total amount cashed by the driver
	private int numOfRides = 0;    // number of rides done by the driver
	private ArrayList<Double> driverMarks = new ArrayList<Double>();
	private Map<String, Long> driversTimes = new HashMap<String, Long>(); //the time are in ms.
	
	private Date lastTimeOfLastStateChange;
	
	//  Constructor 
	public Driver(Car car, String driverName, String driverSurName) {
		this.car = car;
		this.driverName = driverName;
		this.driverSurName = driverSurName;
		this.driverID = biggestDriverID + 1;
		biggestDriverID++; 
		driversTimes.put("on-dutty", (long) 0);   
		driversTimes.put("off-duty", (long) 0);
		driversTimes.put("on-a-ride", (long) 0);
		this.lastTimeOfLastStateChange = new Date(0,0,0);
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
	
	public double getDriverAmount() {
		return driverAmount;
	}
	
	public Map<String, Long> getDriversTimes() {
		return driversTimes;
	}

	public int getNumOfRides() {
		return numOfRides;
	}

	
	//only works if this is the driver of ride and the ride is confirmed
	//also credit the driver for the ride
	public void start(Ride ride) {
		if(ride.getDriver() == this && ride.getStatus() == RideStatus.CONFIRMED) {
			this.numOfRides++;
			ride.getCustomer().spendAmount(ride.getCost());
			this.addAmount(ride.getCost());
			ride.setStatus(RideStatus.ONGOING);
			ride.getCustomer().addMessageToBox("Your ride has started");
		}
	}
	//only works if this is the driver of ride and the ride is confirmed
	//Also change the position of the driver to the destination position
	public void finish(Ride ride) {
		if(ride.getDriver() == this && ride.getStatus() == RideStatus.ONGOING
				&& this.changeStateTo(DriverState.ONDUTY,ride.getArrivalDate())) {
		ride.setStatus(RideStatus.COMPLETED);
		this.car.setCarPosition(ride.getDestinationPoint());
		ride.getCustomer().setCustomerPosition(ride.getDestinationPoint());
		ride.getCustomer().addMessageToBox("Your ride is finished");
		}
	}

	
	public boolean changeStateTo(DriverState newdriverState, Date dateOfChange) {

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

		long differenceOfTime = this.lastTimeOfLastStateChange.getTime() - dateOfChange.getTime();
		
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
	@Override
	public String toString() {
		return "Driver : driverName=" + driverName + ", driverSurName=" + driverSurName + ", driverID=" + driverID
				+ ", driverState=" + driverState;
	}
	//  method for adding a new amount
	public void addAmount(double amount) {
		this.driverAmount = (this.getDriverAmount() + amount);
	} 
	

	// method to add extra on-duty time
	public void addOnDutyTime(long onDutyTime) {
		long newOnDutyTime = this.driversTimes.get("on-dutty") + onDutyTime;
		this.driversTimes.put("on-dutty", newOnDutyTime);
	}
	
	
	public void addOffDutyTime(long offDutyTime) {
		long newOffDutyTime = this.driversTimes.get("off-duty") +offDutyTime;
		this.driversTimes.put("off-duty", newOffDutyTime);
	}
	
	public void addOnRideTime(long onRideTime) {
		long newOnRideTime = this.driversTimes.get("on-a-ride") + onRideTime;
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
