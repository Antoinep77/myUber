package Cars;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GPS.GPScoordinates;
import Rides.UberX;

class CarFactoryTest {

	@Test
	void whenCreatingStandardThenReturnStandard() {
		Car car = CarFactory.create("Standard", new GPScoordinates(1,2));
		assertTrue(car instanceof Standard);
	}
	
	@Test
	void whenCreatingBerlinThenReturnBerlin() {
		Car car = CarFactory.create("Berlin", new GPScoordinates(1,2));
		assertTrue(car instanceof Berlin);
	}
	@Test
	void whenCreatingVanThenReturnVan() {
		Car car = CarFactory.create("Van", new GPScoordinates(1,2));
		assertTrue(car instanceof Van);
	}
	
	@Test
	void whenCreatingUnknownThenReturnNull() {
		Car car = CarFactory.create("dazdd", new GPScoordinates(1,2));
		assertEquals(car, null);
	}

}
