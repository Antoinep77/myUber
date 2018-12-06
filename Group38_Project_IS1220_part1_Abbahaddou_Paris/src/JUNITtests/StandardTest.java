package JUNITtests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Cars.Berlin;
import Cars.Car;
import Cars.Standard;
import GPS.GPScoordinates;

class StandardTest {

	@Test
	void testinfIfTheIdIsStandardN() {
		Car car1 = new Berlin(new GPScoordinates(6, 7));
		Car car2 = new Berlin(new GPScoordinates(5, 10));
		Car car3 = new Berlin(new GPScoordinates(6, 22));
		Car car4 = new Standard(new GPScoordinates(7, 7));
		Car car5 = new Standard(new GPScoordinates(11, 6));
		assertEquals(car5.getCarID(),"Standard2");
	}


}
