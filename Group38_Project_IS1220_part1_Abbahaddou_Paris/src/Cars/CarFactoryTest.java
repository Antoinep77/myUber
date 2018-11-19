package Cars;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GPS.GPScoordinates;
import Rides.UberX;

class CarFactoryTest {

	@Test
	void whenCreatingStandardThenReturnStandard() throws Exception {
		Car car = CarFactory.create("Standard", new GPScoordinates(1,2));
		assertTrue(car instanceof Standard);
	}
	
	@Test
	void whenCreatingBerlinThenReturnBerlin() throws Exception {
		Car car = CarFactory.create("Berlin", new GPScoordinates(1,2));
		assertTrue(car instanceof Berlin);
	}
	@Test
	void whenCreatingVanThenReturnVan() throws Exception {
		Car car = CarFactory.create("Van", new GPScoordinates(1,2));
		assertTrue(car instanceof Van);
	}
	
	@Test
	void whenCreatingUnknownThenReturnNull() {
		assertThrows(Exception.class,()->CarFactory.create("fail", new GPScoordinates(1,2)));

	}

}
