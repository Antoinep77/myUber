package Pacjage1;

public class GPScoordiantes {
	private double x;
	private double y;
	
	// constructor method
	public GPScoordiantes(double x, double y) {
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
	public double distance(GPScoordiantes point1, GPScoordiantes point2) {
		double dist, x1, x2, y1, y2;
		x1 = point1.getX();
		y1 = point2.getY();
		x2 = point1.getX();
		y2 = point2.getY();	
		dist = Math.sqrt( Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
		return dist;
	}
	
}
