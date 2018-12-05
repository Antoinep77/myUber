package myUberCLUI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import Cars.Car;
import GPS.GPScoordinates;
import Rides.Ride;
import Rides.RideFactory;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;
import customersAndDrivers.DriverState;
import myUber.MyUber;
import sorting.CustomerSorting;
import sorting.DriverSorting;
import sorting.LeastOccupiedSorting;
import sorting.MostAppreciatedSorting;
import sorting.MostChargedSorting;
import sorting.MostFrequentSorting;

public class CLUI {
	
	private MyUber myUber;
	
	public static void main(String[] args) {

		new CLUI();
	}
	
	
	/**
	 * 
	 * Run an instance of the myUberCLUI
	 */
	public CLUI() {
		Scanner reader = new Scanner(System.in);  
		String command = "";
		while (!command.equals("stop")) {
			System.out.println("Enter a command: ");
			command = reader.nextLine();
			this.executeCommand(command.split(" "),reader);
		}
		reader.close();
	}

	/**
	 * Execute one of the command. If the command does not match a message is printed.
	 * @param command the list of the keyword and the parameter
	 */
	private void executeCommand(String[] command,Scanner reader) {
		System.out.print("Your command : ");
		for (String c: command) {
			System.out.print(c+" ");
		}
		System.out.println("");
		
		if(command[0].equals("init") && command.length == 2) {
			try {
				this.executeFile(command[1],reader);
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
		
		else if(command[0].equals("addDriver") && command.length == 4) {
			
			try {
				Car car = this.myUber.getCarWithId(command[3]);
				this.myUber.createDriver(car,command[1], command[2]);
				System.out.println("List of Cars :");
				System.out.println(myUber.getCarList());
				System.out.println("List of Drivers :");
				System.out.println(myUber.getDriverList());
			} catch (Exception e) {
				System.out.println("No car with this ID");
			}
		}
		
		else if(command[0].equals("setDriverStatus") && command.length == 4) {
			try {
				Driver driver = this.myUber.getDriverWithNames(command[1], command[2]);
				if(driver.changeStateTo(DriverState.valueOf(command[3]), new Date())) {
					System.out.println("List of Drivers :");
					System.out.println(myUber.getDriverList());
				}
				else {
					System.out.println("This driver can not change to this state. His car may already be taken.");
				}
			} 
			catch (IllegalArgumentException e) {
				System.out.println("This status does not exist");
			}
			catch (Exception e) {
				System.out.println(e);

				System.out.println("No driver with this name");
			}
		}
		
		else if(command[0].equals("moveCar") && command.length == 4) {
			try {
				this.myUber.getCarWithId(command[1]).setCarPosition(new GPScoordinates(
						Double.parseDouble(command[2]),Double.parseDouble(command[3])));
				System.out.println("List of Cars :");
				System.out.println(myUber.getCarList());
			} catch (NumberFormatException e) {
				System.out.println("Invalid Parameters.");
			} catch (Exception e) {
				System.out.println("No car with this ID");
			}
		}
		
		else if(command[0].equals("moveCustomer") && command.length == 4) {
			try {
				this.myUber.getCustomerWithId(Integer.parseInt(command[1])).setCustomerPosition(new GPScoordinates(
						Double.parseDouble(command[2]),Double.parseDouble(command[3])));
				System.out.println("List of Customers :");
				System.out.println(myUber.getCustomerList());
			} catch (NumberFormatException e) {
				System.out.println("Invalid Parameters.");
			} catch (Exception e) {
				System.out.println("No customer with this ID");
			}
		}
		
		else if(command[0].equals("displayState") && command.length == 1) {
			System.out.println("The current state of the system is :");
			System.out.println(this.myUber);
		}
		
		// The destination parameters is actually 2 parameters the x-coordinate and the y-coordinate
		else if(command[0].equals("ask4price") && command.length == 5) {
			Customer cust;
			try {
				cust = myUber.getCustomerWithId(Integer.parseInt(command[1]));
				Date date  = new Date(); int time = Integer.parseInt(command[4]);
				if(0<=time && time<= 23) {
					date.setHours(time);
				}
				myUber.requireRide(cust,new GPScoordinates(Double.parseDouble(command[2]),Double.parseDouble(command[3])),date);
				for(String message: cust.getMessagebox()) {
					System.out.println(message);
				}
				cust.clearMessagebox();
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid parameters");
			} 
			catch (Exception e) {
				System.out.println("Customer could not be found");
			}
		}
		
		// The destination parameters is actually 2 parameters the x-coordinate and the y-coordinate
		else if(command[0].equals("simRide") && command.length == 7) {
			try {
				Customer cust = myUber.getCustomerWithId(Integer.parseInt(command[1]));
				Date date  = new Date(); int time = Integer.parseInt(command[4]);
				if(0<=time && time<= 23) {
					date.setHours(time);
				}
				
				Ride ride = myUber.requireRide(cust,new GPScoordinates(Double.parseDouble(command[2])
						,Double.parseDouble(command[3])),date).require(myUber, command[5]);
				if(myUber.confirm(ride)) {
					myUber.start(ride);
					myUber.finish(ride);
					myUber.mark(ride,Integer.parseInt(command[6]));
					Driver driver = ride.getDriver();
					System.out.println(cust.getMessagebox());
					cust.clearMessagebox();
					System.out.println("The ride executed with the driver : "+driver.getDriverID()+ " and the car : " + driver.getCar().getCarID());
					System.out.println("The ride started at "+ ride.getStartingDate() + " and finished at "+ ride.getArrivalDate());
					System.out.println("The customer has been charged : " + ride.getCost());
				}
				else {
					System.out.println("No driver onduty found");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("Invalid parameters");
			} 
			catch (Exception e) {
				System.out.println(e);
				System.out.println("Customer could not be found");
			}
		}
			
			// The destination parameters is actually 2 parameters the x-coordinate and the y-coordinate
			else if(command[0].equals("simRide_i") && command.length == 5) {
				try {
					Customer cust = myUber.getCustomerWithId(Integer.parseInt(command[1]));
					Date date  = new Date(); int time = Integer.parseInt(command[4]);
					if(0<=time && time<= 23) {
						date.setHours(time);
					}
					RideFactory rideFac = myUber.requireRide(cust,new GPScoordinates(Double.parseDouble(command[2])
							,Double.parseDouble(command[3])),date);
					for(String message: cust.getMessagebox()) {
						System.out.println(message);
					}
					Boolean rideTypeIsOk = false;
					Ride ride = null;
					while(!rideTypeIsOk) {
						try {
							System.out.println("What type of ride do you want");
							ride = rideFac.require(myUber, reader.next());
							rideTypeIsOk = true;
						}
						catch(Exception e) {
							System.out.println("Invalid type of ride.");
						}
					}
					if(myUber.confirm(ride)) {
						myUber.start(ride);
						myUber.finish(ride);
						
						Driver driver = ride.getDriver();
						System.out.println("The ride executed with the driver : "+driver.getDriverID()+ " and the car : " + driver.getCar().getCarID());
						System.out.println("The ride started at "+ ride.getStartingDate() + " and finished at "+ ride.getArrivalDate());
						System.out.println("The customer has been charged : " + ride.getCost());
						
						Boolean markIsOk = false;
						int mark = 0;
						while(!markIsOk) {
							System.out.println("Please enter a mark.");	
							mark = reader.nextInt();
							if (0<=mark && mark<=5) {
								markIsOk = true;
							}
							else {
								System.out.println("Invalid mark. Your mark must be between 0 and 5.");
							}
						}
						
						myUber.mark(ride,mark);
						cust.clearMessagebox();
						System.out.println(myUber);
					}
					else {
						System.out.println("No driver onduty found");
					}

				} catch (Exception e) {
					System.out.println("Customer could not be found");
				}
		}
		
		else if(command[0].equals("displayDrivers") && command.length == 2) {
	
			DriverSorting sortingPolicy;
			if(command[1].equals("mostappreciated")) {
				sortingPolicy = new MostAppreciatedSorting();
				System.out.println(sortingPolicy.sortDrivers(myUber.getDriverList()));
			}
			else if(command[1].equals("mostoccupied")) {
				sortingPolicy = new LeastOccupiedSorting();
				List<Driver> list = sortingPolicy.sortDrivers(myUber.getDriverList());
				Collections.reverse(list);
				System.out.println(list);
			}
			else {System.out.println("Invalid type of sorting.");}
		
		}
		
		else if(command[0].equals("displayCustomers") && command.length == 2) {
			
			CustomerSorting sortingPolicy = null;
			if(command[1].equals("mostfrequent")) {
				sortingPolicy = new MostFrequentSorting();
				System.out.println(sortingPolicy.sortCustomers(myUber.getCustomerList()));
			}
			else if(command[1].equals("mostcharged")) {
				sortingPolicy = new MostChargedSorting();
				System.out.println(sortingPolicy.sortCustomers(myUber.getCustomerList()));
			}
			else {System.out.println("Invalid type of sorting.");}
		}
		
		else if(command[0].equals("totalCashed") && command.length == 1) {
			
			double total = 0;
			for (Driver driver: myUber.getDriverList()) {
				total += driver.getDriverAmount();
			}
			System.out.println("The total cashed is : "+ total);
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
	private void executeFile(String filePath, Scanner reader) throws IOException {
		FileReader file = new FileReader(filePath);
		BufferedReader reader1 = new BufferedReader(file);
		String command ="";
		while ((command=reader1.readLine()) != null) {
			this.executeCommand(command.split(" "),reader);
		}
		reader1.close();
		
	}
	
	

}
