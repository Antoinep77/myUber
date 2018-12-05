package JUNITtests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GPS.GPScoordinates;

class GPScoordinatesTest {

	@Test
	void EqualityTest() {
		GPScoordinates z1 = new GPScoordinates(0, 0);
		GPScoordinates z2 = new GPScoordinates(4, 0);
		assertEquals(GPScoordinates.distance(z1, z2), 4);
	}
	
	@Test
	void EqualityTest2() {
		GPScoordinates z1 = new GPScoordinates(3, -2);
		GPScoordinates z2 = new GPScoordinates(-1, 1);
		assertEquals(GPScoordinates.distance(z1, z2), 5);
	}
	
	@Test
	void EqualityTest3() {
		GPScoordinates z1 = new GPScoordinates(0.5, 86);
		GPScoordinates z2 = new GPScoordinates(-0.5, 85);
		assertEquals(GPScoordinates.distance(z1, z2), Math.sqrt(2));
	}
	
	@Test
	void EqualityTestWhenSamePoints() {
		GPScoordinates z1 = new GPScoordinates(0, 0);
		GPScoordinates z2 = new GPScoordinates(0, 0);
		assertEquals(GPScoordinates.distance(z1, z2), 0);
	}
	
	@Test
	void EqualityTestWhenSamePoints2() {
		GPScoordinates z1 = new GPScoordinates(-4.5, 5);
		GPScoordinates z2 = new GPScoordinates(-4.5, 5);
		assertEquals(GPScoordinates.distance(z1, z2), 0);
	}

}
