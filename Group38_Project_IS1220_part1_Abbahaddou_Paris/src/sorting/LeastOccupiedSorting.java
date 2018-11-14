package sorting;

import java.util.ArrayList;
import java.util.List;

import customersAndDrivers.Driver;

public class LeastOccupiedSorting {

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
	/*
	@Override
	public ArrayList<Driver> sort() {
		ArrayList<Driver> listDriver = new ArrayList<>(Driver.driversList);
		ArrayList<Driver> listDriverSorted = new ArrayList<Driver>();
		int len = listDriver.size();
		for(int i = 0; i<len; i++) {
			Driver d = LeastOccupiedDriver(listDriver);
			listDriverSorted.add(d);
			listDriver.remove(d);
		}
		return listDriverSorted;
	}*/
}
