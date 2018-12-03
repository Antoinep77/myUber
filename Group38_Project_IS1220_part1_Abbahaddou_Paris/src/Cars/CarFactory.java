package Cars;

import GPS.GPScoordinates;

public class CarFactory {
	
	//return null if the type doesn't match
	/**
	 * @param type the type of car to create as string ("standard","berline", "van")
	 * @param carPosition
	 * @return
	 * @throws Exception
	 */
	public static Car create(String type,GPScoordinates carPosition) throws Exception {
		if (type.equals("standard")) {
			return new Standard(carPosition);
		}
		if (type.equals("berline")) {
			return new Berlin(carPosition);
		}
		if (type.equals("van")) {
			return new Van(carPosition);
		}
		throw new Exception("Invalid Type of Car");

	}
}
