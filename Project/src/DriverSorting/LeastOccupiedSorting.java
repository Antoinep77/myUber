package DriverSorting;

import java.util.ArrayList;
import java.util.List;

import Pacjage1.Driver;

public class LeastOccupiedSorting implements DriverSorting{
	public List<Driver> sort(){
		List<Driver> listDriver = new ArrayList<Driver>(Driver.driversList);
		listDriver.sort((Driver d1,Driver d2) -> {return (int)(d1.getRateOfActivity() - d2.getRateOfActivity());} );
		return listDriver;
	}
}
