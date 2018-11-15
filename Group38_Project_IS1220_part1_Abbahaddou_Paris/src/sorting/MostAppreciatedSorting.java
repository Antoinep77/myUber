package sorting;

import java.util.ArrayList;

import customersAndDrivers.Driver;

public class MostAppreciatedSorting implements DriverSorting{
	

	@Override
	public ArrayList<Driver> sortDrivers(ArrayList<Driver> listDriverToSort){
		ArrayList<Driver> listDriver = new ArrayList<Driver>(listDriverToSort);
		listDriver.sort((Driver d1,Driver d2) -> {
			if(d1.avrageOfMarks() < d2.avrageOfMarks() ) {
				return 1;
			}
			if(d1.avrageOfMarks()  > d2.avrageOfMarks() ) {
				return -1;
			}
			return 0;
		} );
		return listDriver;
	}
}
