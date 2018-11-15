package sorting;

import java.util.ArrayList;
import java.util.List;

import customersAndDrivers.Driver;

public class LeastOccupiedSorting implements DriverSorting{
	
	public List<Driver> sortDrivers(){
		List<Driver> listDriver = new ArrayList<Driver>(Driver.driversList);
		listDriver.sort((Driver d1,Driver d2) -> {return (int)(d1.getRateOfActivity() - d2.getRateOfActivity());} );
		return listDriver;
	}
	
	/*
	public Driver LeastOccupiedDriver(ArrayList<Driver> list) {
		Driver d = list.get(0);
		double rate = d.getRateOfActivity();
		for (Driver driver : list) {
			if(driver.getRateOfActivity() > rate) {
				d = driver;
			}
			return d;
		}
		return null;
		
	}
	public ArrayList<Driver> sortDrivers(){
		ArrayList<Driver> listDriver = new ArrayList<>(Driver.driversList);
		ArrayList<Driver> listDriverSorted = new ArrayList<Driver>();
		int len = listDriver.size();
		for(int i = 0; i<len; i++) {
			Driver d = LeastOccupiedDriver(listDriver);
			listDriverSorted.add(d);
			listDriver.remove(d);
		}
		return listDriverSorted;
	}
	*/
}
