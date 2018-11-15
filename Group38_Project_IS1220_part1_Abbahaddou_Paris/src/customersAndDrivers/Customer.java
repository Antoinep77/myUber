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
	private double totaleAmountRetreived = 0;
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
	
	

	public double getTotaleAmountRetreived() {
		return totaleAmountRetreived;
	}


	//////////////////////// add a message to the box  //////////////////////////
	public void addMessageToBox(String message){
		System.out.println(this.customerName + " : " +message);
		this.Messagebox.add(message);
	}
	
	//add one ride to the total number of ride
	public void addOneRide() {
		this.numOfRide = this.numOfRide + 1;
		
	}
	
	//
	public void retreiveAmount(double amount) {
		this.totaleAmountRetreived = this.totaleAmountRetreived - amount;
	}
	
	//Give a mark to the driver of one of their rides 
	// work only if the customer has taken the ride, the ride is completed and the ride hasn't already be marked
	public void mark(Ride ride, double newmark) {
		if (this == ride.getCustomer() && ride.getStatus() == RideStatus.COMPLETED && !ride.isMarked()) {
			ride.getDriver().addOneMark(newmark);
		}
	}
	
	//only works if this is the customer of ride and the ride is confirmed or unconfirmed
	public void cancel(Ride ride) {
		if(ride.getCustomer() == this && 
				(ride.getStatus() == RideStatus.CONFIRMED || ride.getStatus() == RideStatus.UNCONFIRMED )
				&& ride.getDriver().changeStateTo(DriverState.ONARIDE,ride.getTime())) {
			ride.setStatus(RideStatus.CANCELED);
		}
	}



}
