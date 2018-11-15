package customersAndDrivers;

import java.util.ArrayList;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.RideStatus;

public class Customer {
	private String customerName;   // the name of the customer
	private String customerSurName;  // the surname of the customer
	private int customerID;  // numerical ID of the customer
	private GPScoordinates customerPosition;  // the current position of the costumer
	private int creditCardNumber;  // the number of the credit card
	private ArrayList<String> Messagebox = new ArrayList<String>();   // ArrayList of strings containing the received messages  
	public static int biggestCustomerID;
	private double totaleAmountPaid  = 0;
	private int numOfRide = 0;
	

	
	public Customer(String customerName, String customerSurName, GPScoordinates customerPosition,
			int creditCardNumber) {
		super();
		this.customerName = customerName;
		this.customerSurName = customerSurName;
		this.customerID = biggestCustomerID +1;
		this.customerPosition = customerPosition;
		this.creditCardNumber = creditCardNumber;
		biggestCustomerID++;
	}
	// Setters and getters  
	

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerSurName() {
		return customerSurName;
	}



	public int getCustomerID() {
		return customerID;
	}


	public GPScoordinates getCustomerPosition() {
		return customerPosition;
	}


	public void setCustomerPosition(GPScoordinates customerPosition) {
		this.customerPosition = customerPosition;
	}


	public int getCreditCardNumber() {
		return creditCardNumber;
	}


	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}


	public ArrayList<String> getMessagebox() {
		return Messagebox;
	}

	
	public int getNumOfRide() {
		return numOfRide;
	}
	
	

	public double getTotaleAmountPaid() {
		return totaleAmountPaid;
	}


	//////////////////////// add a message to the box  //////////////////////////
	public void addMessageToBox(String message){
		System.out.println(this.customerName + " : " +message);
		this.Messagebox.add(message);
	}
	
	// ad the amount to the total amount and add one ride to the total number of ride
	public void spendAmount(double amount) {
		this.totaleAmountPaid = this.totaleAmountPaid + amount;
		this.numOfRide = this.numOfRide + 1;
	}
	
	//Give a mark to the driver of one of their ride
	// work only if the customer has taken the ride, the ride is completed and the ride hasn't already be marked
	public void mark(Ride ride, int newmark) {
		if (this == ride.getCustomer() && ride.getStatus() == RideStatus.COMPLETED && !ride.isMarked() &&
				newmark <= 5 && newmark >= 0) {
			ride.getDriver().addOneMark(newmark);
			addMessageToBox("You rated your Driver with a " + newmark +" star mark.");
		}else {
			addMessageToBox("Invalid mark, your mark must be beetween 0 and 5");
		}
	}
	
	//only works if this is the customer of ride and the ride is confirmed or unconfirmed
	public void cancel(Ride ride) {
		if(ride.getCustomer() == this && 
				(ride.getStatus() == RideStatus.CONFIRMED || ride.getStatus() == RideStatus.UNCONFIRMED)) {
			ride.getDriver().changeStateTo(DriverState.ONDUTY,ride.getStartingDate());
			ride.setStatus(RideStatus.CANCELED);
			addMessageToBox("Your ride has been canceled");
		}
	}



}
