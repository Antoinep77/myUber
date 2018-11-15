package Rides;

import java.util.ArrayList;

import GPS.GPScoordinates;
import customersAndDrivers.Customer;
import customersAndDrivers.Driver;

public class UberPoolRide {
	private ArrayList<Customer> poolRequests;
	
	
	public static GPScoordinates ClosestPointFrom(ArrayList<GPScoordinates> listOfPoints, Driver d) {
		GPScoordinates point = d.getCar().getCarPosition();
		ArrayList<GPScoordinates> sortedListOfPoints = new ArrayList<GPScoordinates>(listOfPoints);
		sortedListOfPoints.sort((GPScoordinates p1, GPScoordinates p2) -> {
			if(GPScoordinates.distance(p1, point) < GPScoordinates.distance(p2, point)) {
				return -1;
			}
			if(GPScoordinates.distance(p1, point) > GPScoordinates.distance(p2, point)) {
				return 1;
			}
			return 0;
		});
		return sortedListOfPoints.get(0);
		
	}/*
	public double cost(Driver d) {
		double c = 0;
		ArrayList<Customer> listPoolCustomer = new ArrayList<Customer>(poolRequests);
		while(listPoolCustomer.size() != 0) {
			GPScoordinates point = UberPoolRide.ClosestPointFrom(listPoolCustomer, d);
		}
			
			
	}*/
}
