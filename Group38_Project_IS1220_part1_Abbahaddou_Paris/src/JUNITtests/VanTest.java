package JUNITtests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Cars.Car;
import Cars.Van;
import GPS.GPScoordinates;

class VanTest {

	
	
	@Test
	void testinfIfTheIdIsVanN() {
		Car car1 = new Van(new GPScoordinates(5, 1));
		Car car2 = new Van(new GPScoordinates(7, 1));
		assertEquals(car2.getCarID(),"Van2");

	}

}
