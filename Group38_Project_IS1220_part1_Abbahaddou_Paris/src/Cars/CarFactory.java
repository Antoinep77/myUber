package Cars;

import GPS.GPScoordinates;

public class CarFactory {
	
	//return null if the type doesn't match
	public static Car create(String type,GPScoordinates carPosition) {
		if (type.equals("Standard")) {
			return new Standard(carPosition);
		}
		if (type.equals("Berlin")) {
			return new Berlin(carPosition);
		}
		if (type.equals("Van")) {
			return new Van(carPosition);
		}
		return null;
	}
}
