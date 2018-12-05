package customersAndDrivers;

import java.util.ArrayList;

import GPS.GPScoordinates;
import Rides.Ride;
import Rides.RideStatus;

public class Customer {
	
	private Boolean printMessageBox = false;
	/**
	 * the name of the customer
	 */
	private String customerName;   // the name of the customer
	/**
	 * the surname of the customer
	 */
	private String customerSurName;  // the surname of the customer
	/**
	 * A numerical ID of the customer
	 */
	private int customerID;  // numerical ID of the customer
	/**
	 * the position (GPScoordinates) of car
	 */
	private GPScoordinates customerPosition;  // the current position of the costumer
	/**
	 * the credit card number
	 */
	private int creditCardNumber;  // the number of the credit card
	/**
	 * the customer message box containing all his message
	 */
	private ArrayList<String> Messagebox = new ArrayList<String>();   // ArrayList of strings containing the received messages  
	/**
	 * the number of customers, its used to generate automatically the ID of the customer
	 */
	public static int biggestCustomerID;
	/**
	 * the aggregation of all amount paid by the cusotmer
	 */
	private double totaleAmountPaid  = 0;
	/**
	 * the number of time the cusomer seats in a uber car
	 */
	private int numOfRide = 0;
	

	
	/**
	 * Constuctor
	 * @param customerName the name of the customer
	 * @param customerSurName the surname of the customer
	 * @param customerPosition the position (GPScoordinates) of car
	 * @param creditCardNumber the credit card number
	 */
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
	
	@Override
	public String toString() {
		return "\n name=" + customerName + ", surname =" + customerSurName + ", ID ="
				+ customerID + ", position=" + customerPosition + ", total paid =" + totaleAmountPaid
				+ ", number of rides =" + numOfRide;
	}



	/**
	 * @return return the name of the customer
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @return return the surname of the customer
	 */
	public String getCustomerSurName() {
		return customerSurName;
	}
	


	/**
	 * @return return the ID of the customer
	 */
	public int getCustomerID() {
		return customerID;
	}


	/**
	 * @return return the position (GPScoordinates) of the customer
	 */
	public GPScoordinates getCustomerPosition() {
		return customerPosition;
	}


	/**
	 * @param customerPosition to set the poisiton (GPScoordinates) of the customer
	 */
	public void setCustomerPosition(GPScoordinates customerPosition) {
		this.customerPosition = customerPosition;
	}


	/**
	 * @return return the number of customer credit card
	 */
	public int getCreditCardNumber() {
		return creditCardNumber;
	}


	/**
	 * @param creditCardNumber to set the number of customer credit card
	 */
	public void setCreditCardNumber(int creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}


	/**
	 * @return return an ArrayList<String> of all messages received by the customer
	 */
	public ArrayList<String> getMessagebox() {
		return Messagebox;
	}
	
	public void clearMessagebox() {
		Messagebox = new ArrayList<String>();
	}

	
	/**
	 * @return return the number of ride done by the customer
	 */
	public int getNumOfRide() {
		return numOfRide;
	}
	
	

	/**
	 * @return return the aggregation of all amount paid by the cusotmer
	 */
	public double getTotaleAmountPaid() {
		return totaleAmountPaid;
	}


	//////////////////////// add a message to the box  //////////////////////////
	/**
	 * @param message the message to add in the customer message box
	 */
	public void addMessageToBox(String message){
		if(printMessageBox) {
			System.out.println(this.customerName + " : " +message);
		}
		this.Messagebox.add(message);
	}
	
	// ad the amount to the total amount and add one ride to the total number of ride
	/**
	 * @param amount the amount to add to the total amount paid by the customer
	 */
	public void spendAmount(double amount) {
		this.totaleAmountPaid = this.totaleAmountPaid + amount;
		this.numOfRide = this.numOfRide + 1;
	}




}
