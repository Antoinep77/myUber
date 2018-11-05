package Pacjage1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Driver {
	
	public static ArrayList<Driver> driversList;
	public static int biggestDriverID;
	
	
	private String driverName;
	private String driverSurName;
	private int driverID;
	private DriverState driverState;
	private double driverAmount;
	private double numOfRides;
	private Map<String, Integer> driversTimes;
	
	
	
	////////////////////////////////  Constructor ///////////////////////////////////////////
	public Driver(String driverName, String driverSurName, DriverState driverState) {
		super();
		this.driverName = driverName;
		this.driverSurName = driverSurName;
		this.driverID = biggestDriverID + 1;
		biggestDriverID++;
		this.driverState = driverState;
		this.driverAmount = 0.0;
		this.numOfRides = 0;
		driversTimes.put("on-dutty", 0);   
	
		driversTimes.put("off-duty", 0);
		driversTimes.put("on-a-ride", 0);
		driversList.add(this);
			}
	////////////////////////////////   Setters and getters   ///////////////////////////////////////////
	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverSurName() {
		return driverSurName;
	}

	public void setDriverSurName(String driverSurName) {
		this.driverSurName = driverSurName;
	}

	public int getDriverID() {
		return driverID;
	}
/* pas besoin
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
*/
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
	public static void setBiggestDriverID(int biggestDriverID) {
		Driver.biggestDriverID = biggestDriverID;
	}
	public double getNumOfRides() {
		return numOfRides;
	}
	public void setNumOfRides(double numOfRides) {
		this.numOfRides = numOfRides;
	}
	////////////////////////////////toString   ///////////////////////////////////////////
	@Override
	public String toString() {
		return "Driver : driverName=" + driverName + ", driverSurName=" + driverSurName + ", driverID=" + driverID
				+ ", driverState=" + driverState;
	}
	////////////////////////////   addAmount  ////////////////////////
	public void addAmount(double amount) {
		
		this.setDriverAmount(this.getDriverAmount() + amount);
	} 
	
	
	public void addNewRide() {
		this.setNumOfRides(this.getNumOfRides() +1 );
	}

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
}