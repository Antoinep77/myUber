package JUNITtests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Rides.TrafficCondition;
import TimeOperation.TimeOperation;

class TimeOperationTest {

	@Test
	void testingSpeedLowTrafficCondition() {
		System.out.println(TimeOperation.rideDurationTime(2, TrafficCondition.LOW));
		assertEquals(TimeOperation.rideDurationTime(2, TrafficCondition.LOW), 480000);
	}
	
	@Test
	void testingSpeedMEDIUMTrafficCondition() {
		System.out.println(TimeOperation.rideDurationTime(4, TrafficCondition.MEDIUM));
		assertEquals(TimeOperation.rideDurationTime(4, TrafficCondition.MEDIUM), 1920000);
	}
	
	@Test
	void testingSpeedHEAVYTrafficCondition() {
		System.out.println(TimeOperation.rideDurationTime(8, TrafficCondition.HEAVY));
		assertEquals(TimeOperation.rideDurationTime(8, TrafficCondition.HEAVY), 9600000);
	}

}
