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
	private ArrayList<String> Messagebox = null;   // ArrayList of strings containing the received messages  
	public static int biggestCustomerID;
	
	//private int NumOfPerson; ??
	
	//   constructor  
	
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


	//////////////////////// add a message to the box  //////////////////////////
	public void addMessageToBox(String message){
		this.Messagebox.add(message);
	}
	
	//Give a mark to the driver of one of their rides 
	// work only if the customer has taken the ride, the ride is completed and the ride hasn't already be marked
	public void mark(Ride ride, double newmark) {
		if (this == ride.getCustomer() && ride.getStatus() == RideStatus.COMPLETED && !ride.isMarked()) {
			ride.getDriver().addOneMark(newmark);
		}
	}



}
