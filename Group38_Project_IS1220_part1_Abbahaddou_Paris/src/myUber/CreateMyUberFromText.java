package myUber;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import Cars.Car;
import GPS.GPScoordinates;
import Rides.Ride;
import Rides.RideFactory;
import Rides.RideStatus;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;

public class CreateMyUberFromText {
	
	

	/*do a random scenario from text
	Look below for more detail of instruction available*/
	public static MyUber executeScenarioFromText(String filePath) throws Exception {
		FileReader file = new FileReader(filePath);
		BufferedReader reader = new BufferedReader(file);
		String line ="";
		ArrayList<String[]> listOfCommands = new ArrayList<String[]>();
		MyUber myUber = new MyUber();
		int positionMax = 100;
		while ((line=reader.readLine()) != null) {
			String[] listFromLine = line.split(" ");
			listOfCommands.add(listFromLine);
		}
		reader.close();
		for (String[] command: listOfCommands) {
			
			// Command "Car TypeCar NumberOfCar" ex: "Car Standard 30" (the positions of car are random)
			if (command[0].equals("Car")) {
				for(int i=0; i< Integer.parseInt(command[2]);i++ ) {
					myUber.createCar(command[1], new GPScoordinates(Math.random()*positionMax, Math.random()*positionMax));
				}
			}
			
			// Command "Driver NumberOfDriver" ex: "Driver 30" (the car is set randomly)
			if(command[0].equals("Driver")) {
				for(int i=0; i< Integer.parseInt(command[1]);i++ ) {
					List<Car> carList = myUber.getCarList();
					Collections.shuffle(carList);
					myUber.createDriver(carList.get(i%carList.size()), "driver"+i, "Surname").connect(new Date());;
				}
			}
			// Command "Customer NumberOfCustomer"
			if (command[0].equals("Customer")) {
				for(int i=0; i< Integer.parseInt(command[1]);i++ ) {
					myUber.createCustomer("customer"+i, "Surname", new GPScoordinates(Math.random()*positionMax, Math.random()*positionMax), i);
				} 
			}
			
			//Command "Rides numberOfRides probabilityOfUberX pbltyOfUberPool pbltyOfUberBlack pbltyOfUnconfirmedFromDriver pbltyOfCanceledFromCustomer"
			// The UberVan probability equals 1 minus the others.
			//The customer is taken randomly
			if (command[0].equals("Rides")) {
				List<Customer> custList = myUber.getCustomerList();
				
				for(int i=0;i< Integer.parseInt(command[1]);i++ ) {
					Customer cust = custList.get(new Random().nextInt(custList.size()));
					GPScoordinates coords = new GPScoordinates(Math.random()*positionMax, Math.random()*positionMax);
					RideFactory rideFac = myUber.requireRide(cust,coords,new Date());
					
					double random = Math.random();
					Ride ride;
					if(random< Double.parseDouble(command[2])) {
						ride = rideFac.require(myUber, "uberX");
					}
					else if(random< Double.parseDouble(command[2]) +Double.parseDouble(command[3])) {
						ride = rideFac.require(myUber, "uberPool");
					}
					else if(random< Double.parseDouble(command[4])+Double.parseDouble(command[3]) +Double.parseDouble(command[2])) {
						ride = rideFac.require(myUber, "uberBlack");
					}
					else {
						ride = rideFac.require(myUber, "uberVan");
					}
					Driver driver = ride.getDriver();
					
					//The ride should change to CONFIRMED or CANCELED
					while(ride.getStatus() == RideStatus.UNCONFIRMED) {
						double random1 = Math.random();
						double random2 = Math.random();
						
						if(random1<Double.parseDouble(command[6])) {
							cust.cancel(ride);
						}
						if(random2<Double.parseDouble(command[5])) {
							myUber.refuse(driver, ride);
						}
						else {
							myUber.confirm(driver,ride);
						}
					}
					if(ride.getStatus()==RideStatus.CONFIRMED) {
						driver.start(ride);
						driver.finish(ride);
						cust.mark(ride, new Random().nextInt(6));
					}
				}
				
				
				
			}
		}

		return myUber;
	}
}
