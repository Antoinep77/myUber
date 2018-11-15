package GPS;

import java.util.ArrayList;

import customersAndDrivers.Driver;

public class GPScoordinates {
	private double x;
	private double y;
	
	// constructor method
	public GPScoordinates(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	// Setter & getters
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	// method calculating the distance between 2 positions.
	public static double distance(GPScoordinates point1, GPScoordinates point2) {
		double dist, x1, x2, y1, y2;
		x1 = point1.getX();
		y1 = point1.getY();
		x2 = point2.getX();
		y2 = point2.getY();	
		dist = Math.sqrt( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		return dist;
	}
	
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
		
	}
}
