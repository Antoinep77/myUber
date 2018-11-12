package sorting;

import java.util.ArrayList;

import customersAndDrivers.Driver;

public class MostAppreciatedSorting implements DriverSorting{
	public Driver MostAppreciatedDriver(ArrayList<Driver> list) {
		Driver d = list.get(0);
		double mark = d.avrageOfMarks();
		for (Driver driver : list) {
			if(driver.avrageOfMarks() > mark) {
				d = driver;
			}
			return d;
		}
		return null;
		
	}
	
	@Override
	public ArrayList<Driver> sort() {
		ArrayList<Driver> listDriver = new ArrayList<>(Driver.driversList);
		ArrayList<Driver> listDriverSorted = new ArrayList<Driver>();
		int len = listDriver.size();
		for(int i = 0; i<len; i++) {
			Driver d = MostAppreciatedDriver(listDriver);
			listDriverSorted.add(d);
			listDriver.remove(d);
		}
		return listDriverSorted;
	}
}
