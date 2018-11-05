package Pacjage1;

public class GPScoordiantes {
	private double latitude;
	private double longitude;
	
	public GPScoordiantes(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "GPScoordiantes : latitude=" + latitude + ", longitude=" + longitude;
	}

	public static double length(GPScoordiantes point1, GPScoordiantes point2) {
		double lat1 = point1.getLatitude(); // in degrees 
		double lat2 = point2.getLatitude(); // in degrees
		double longit1 = point1.getLongitude();
		double longit2 = point2.getLongitude();
		double angulardistance; // angulardistance is in radians
		angulardistance = Math.acos(Math.cos(Math.toRadians(lat1))
	            * Math.cos(Math.toRadians(lat2))
	            * Math.cos(Math.toRadians((longit1) - (longit2)))
	            + Math.sin(Math.toRadians(lat1))
	            * Math.sin(Math.toRadians(lat2)));
			
		double distance = 6371.01*angulardistance;
		return distance;
		
 	}
	// tester 
	/*
	public static void main(String[] args) {
		GPScoordiantes paris = new GPScoordiantes(48.856614, 2.3522219000000177);
		GPScoordiantes marseille = new GPScoordiantes(43.300000, 5.400000);
		System.out.println(length(paris, marseille)); // à vérifier sur https://www.lexilogos.com/calcul_distances.htm
	}
	*/
	
	
}
