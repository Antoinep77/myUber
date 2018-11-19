package sorting;

import java.util.ArrayList;
import java.util.List;

import customersAndDrivers.Driver;

public interface DriverSorting {
	/**
	 * method that sort a list of drivers
	 * @param listDriverToSort the list of drivers to sort
	 * @return the list of drivers sorted 
	 */
	public abstract ArrayList<Driver> sortDrivers(ArrayList<Driver> listDriverToSort);

}
