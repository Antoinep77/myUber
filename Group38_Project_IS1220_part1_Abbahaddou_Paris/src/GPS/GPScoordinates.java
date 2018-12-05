package GPS;

import java.util.ArrayList;

import customersAndDrivers.Driver;

public class GPScoordinates {
	/**
	 * the x coordinate
	 */
	private double x;
	/**
	 * the y coordinate
	 */
	private double y;
	
	// constructor method
	/**
	 * CONSTRUCTOR
	 * @param x the x coordinate
	 * @param y the Y coordinate
	 */
	public GPScoordinates(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GPScoordinates other = (GPScoordinates) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

	// Setter & getters
	/**
	 * @return the x coordinate
	 */
	public double getX() {
		return x;
	}
	/**
	 * @param x set the x coordinate 
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @return the y coordinate
	 */
	public double getY() {
		return y;
	}
	/**
	 * @param y set the y coordinate 
	 */
	public void setY(double y) {
		this.y = y;
	}
	// method calculating the distance between 2 positions.
	/**
	 * method to calculate the distance between 2 points
	 * @param point1 the first point
	 * @param point2 the second point
	 * @return return a double which is the distance between 2 points
	 */
	public static double distance(GPScoordinates point1, GPScoordinates point2) {
		double dist, x1, x2, y1, y2;
		x1 = point1.getX();
		y1 = point1.getY();
		x2 = point2.getX();
		y2 = point2.getY();	
		dist = Math.sqrt( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		return dist;
	}
	


}
