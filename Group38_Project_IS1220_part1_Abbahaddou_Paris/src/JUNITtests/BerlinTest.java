package JUNITtests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Cars.Berlin;
import Cars.Car;
import GPS.GPScoordinates;

class BerlinTest {

	@Test
	void testinfIfTheIdIsBerlinN() {
		Car car4 = new Berlin(new GPScoordinates(6, 7));
		assertEquals(car4.getCarID(),"Berlin1");

	}

}
