package customersAndDrivers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;
import java.util.Date;

import org.junit.jupiter.api.Test;

import Cars.Car;
import Cars.CarState;
import Cars.Standard;
import GPS.GPScoordinates;

class DriverTest {

	@Test
	void whenCreatingDriversIDAreDifferent() {
		Car car = new Standard(new GPScoordinates(0, 1));
		Driver driver1 = new Driver(car, "prenom", "nom");
		Driver driver2 = new Driver(car, "prenom", "nom");
		assertTrue(driver1.getDriverID()!= driver2.getDriverID());
	}
	
	@Test
	void whenGettingCarGetCar() {
		Car car = new Standard(new GPScoordinates(0, 1));
		Driver driver = new Driver(car, "prenom", "nom");
		assertEquals(driver.getCar(),car);
	}
	
	@Test
	void whenChangingStateOfDriverThenStateChange() {
		Car car = new Standard(new GPScoordinates(0, 1));
		Driver driver = new Driver(car, "prenom", "nom");
		driver.changeStateTo(DriverState.ONDUTY, new Date());	
		assertEquals(driver.getDriverState(),DriverState.ONDUTY);
	}
	
	@Test
	void whenChangingIsAllowedThenReturnTrue() {
		Car car = new Standard(new GPScoordinates(0, 1));
		Driver driver = new Driver(car, "prenom", "nom");
		assertTrue(driver.changeStateTo(DriverState.ONDUTY, new Date()));
	}
	@Test
	void whenChangingStateOfDriverThenCarIsTaked() {
		Car car = new Standard(new GPScoordinates(0, 1));
		Driver driver = new Driver(car, "prenom", "nom");
		driver.changeStateTo(DriverState.ONDUTY, new Date());	
		assertEquals(driver.getCar().getCarState(),CarState.TAKED);
	}
	
	@Test
	void whenTryingToGoOndutyWithTakedCarThenStateDontChange() {
		Car car = new Standard(new GPScoordinates(0, 1));
		Driver driver = new Driver(car, "prenom", "nom");
		Driver driver2 = new Driver(car, "prenom", "nom");
		driver2.changeStateTo(DriverState.ONDUTY, new Date());	
		driver.changeStateTo(DriverState.ONDUTY, new Date());	
		assertEquals(driver.getDriverState(),DriverState.OFFLINE);
	}
	
	@Test
	void whenTryingToGoOndutyWithTakedCarThenReturnFalse() {
		Car car = new Standard(new GPScoordinates(0, 1));
		Driver driver = new Driver(car, "prenom", "nom");
		Driver driver2 = new Driver(car, "prenom", "nom");
		driver2.changeStateTo(DriverState.ONDUTY, new Date());	
		assertTrue(!driver.changeStateTo(DriverState.ONDUTY, new Date()));
	}
	
	@Test
	void whenChangingBackToOffdutyThenCarIsAvailable() {
		Car car = new Standard(new GPScoordinates(0, 1));
		Driver driver = new Driver(car, "prenom", "nom");
		driver.changeStateTo(DriverState.ONDUTY, new Date());	
		driver.changeStateTo(DriverState.OFFLINE, new Date());
		assertEquals(driver.getCar().getCarState(),CarState.AVAILABLE);
	}
}
