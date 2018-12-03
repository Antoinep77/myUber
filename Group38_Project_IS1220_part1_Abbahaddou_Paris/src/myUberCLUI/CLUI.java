package myUberCLUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import GPS.GPScoordinates;
import myUber.MyUber;

public class CLUI {
	
	private MyUber myUber;
	
	/**
	 * Run an instance of the myUberCLUI
	 */
	public CLUI() {
		Scanner reader = new Scanner(System.in);  
		String command = "";
		while (!command.equals("stop")) {
			System.out.println("Enter a command: ");
			command = reader.next();
			this.executeCommand(command.split(" "));
		}
	}

	/**
	 * Execute one of the command. If the command does not match a message is printed.
	 * @param command the list of the keyword and the parameter
	 */
	private void executeCommand(String[] command) {
		
		if(command[0].equals("init") && command.length == 2) {
			try {
				this.executeFile(command[1]);
				System.out.println(myUber);
			} catch (FileNotFoundException e) {
				System.out.println("The file " + command[1] + " could not be found.");
			} catch (IOException e) {
				System.out.println("An error occured while reading the file.");
			}
		} 
		
		else if(command[0].equals("setup") && command.length == 5) {
			this.myUber = new MyUber(Integer.parseInt(command[1]),Integer.parseInt(command[2]),
					Integer.parseInt(command[3]),Integer.parseInt(command[4]));
		}
		
		else if(command[0].equals("addCustomer") && command.length == 3) {
			this.myUber.createCustomer(command[1], command[2], 
					new GPScoordinates((Math.random()-0.5)*10, (Math.random()-0.5)*100), 0);
			System.out.println("List of Customers :");
			System.out.println(myUber.getCustomerList());
		}
		
		else if(command[0].equals("addCarDriver") && command.length == 4) {
			try {
			GPScoordinates coord = new GPScoordinates((Math.random()-0.5)*10, (Math.random()-0.5)*100);
			this.myUber.createDriver(this.myUber.createCar(command[3], coord),command[1], command[2]);
			System.out.println("List of Cars :");
			System.out.println(myUber.getCarList());
			System.out.println("List of Drivers :");
			System.out.println(myUber.getDriverList());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command[0].equals("addCustomer") && command.length == 3) {
			this.myUber.createCustomer(command[1], command[2], 
					new GPScoordinates((Math.random()-0.5)*10, (Math.random()-0.5)*100), 0);
			System.out.println("List of Cars :");
			System.out.println(myUber.getCarList());
			System.out.println("List of Drivers :");
			System.out.println(myUber.getDriverList());
		}
	
		
		
		else {
			System.out.println("The command keyword or the number of parameters do not match with any command.");
		}
		
	}

	/**
	 * Execute the commands written in the file with the given path
	 * @param filePath 
	 * @throws IOException if the file is not found
	 */
	private void executeFile(String filePath) throws IOException {
		FileReader file = new FileReader(filePath);
		BufferedReader reader = new BufferedReader(file);
		String command ="";
		while ((command=reader.readLine()) != null) {
			this.executeCommand(command.split(" "));
		}
		reader.close();
		
	}
	
	

}
