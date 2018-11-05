package Pacjage1;

import java.util.ArrayList;

public class Customer {
	private String customerName;
	private String customerSurName;
	private int cusotmerID;
	private GPScoordiantes customerPosition;
	private int creditCardNumber;
	private ArrayList<String> Messagebox = null;
	public static int biggestCustomerID;
	
	//private int NumOfPerson;
	
	////////////////////////   constructor  //////////////////////////
	
	public Customer(String customerName, String customerSurName, GPScoordiantes customerPosition,
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
	//////////////////////// Setters and getters  //////////////////////////
	

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

	public GPScoordiantes getCustomerPosition() {
		return customerPosition;
	}


	public void setCustomerPosition(GPScoordiantes customerPosition) {
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
		
		}



}
