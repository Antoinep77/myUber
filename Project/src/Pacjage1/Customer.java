package Pacjage1;

import java.util.ArrayList;

import GPS.GPScoordinates;

public class Customer {
	private String customerName;   // the name of the customer
	private String customerSurName;  // the surname of the customer
	private int cusotmerID;  // numerical ID of the customer
	private GPScoordinates customerPosition;  // the current position of the costumer
	private int creditCardNumber;  // the number of the credit card
	private ArrayList<String> Messagebox = null;   // ArrayList of strings containing the received messages  
	public static int biggestCustomerID;
	
	//private int NumOfPerson; ??
	
	//   constructor  
	
	public Customer(String customerName, String customerSurName, GPScoordinates customerPosition,
			int creditCardNumber, ArrayList<String> messagebox) {
		super();
		this.customerName = customerName;
		this.customerSurName = customerSurName;
		this.cusotmerID = biggestCustomerID +1;
		this.customerPosition = customerPosition;
		this.creditCardNumber = creditCardNumber;
		Messagebox = messagebox;
		biggestCustomerID++;
	}
	// Setters and getters  
	

	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getCustomerSurName() {
		return customerSurName;
	}


	public void setCustomerSurName(String customerSurName) {
		this.customerSurName = customerSurName;
	}


	public int getCusotmerID() {
		return cusotmerID;
	}

/*  pas besoin
	public void setCusotmerID(int cusotmerID) {
		this.cusotmerID = cusotmerID;
	}
*/

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


	public void setMessagebox(ArrayList<String> messagebox) {
		Messagebox = messagebox;
	}
	//////////////////////// add a message to the box  //////////////////////////
	public void addMessageToBox(String message){
		ArrayList<String> newListMessage = this.getMessagebox();
		newListMessage.add(message);
		this.setMessagebox(newListMessage);
	}



}
