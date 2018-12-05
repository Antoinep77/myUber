package TimeOperation;

import java.sql.Time;

import Rides.TrafficCondition;

public class TimeOperation {
	

	//return time (in ms) to travel a distance with a traffic condition
	/**
	 * return time (in ms) to travel a distance with a traffic condition
	 * @param distance the distance to travel
	 * @param traffic the traffic condition (because the speed of the car depends on the traffic condition
	 * @return the duration of the travel in msecondes
	 */
	public static long rideDurationTime(double distance, TrafficCondition traffic) {
		double speed ;
		if(traffic == TrafficCondition.LOW) {
			speed = 15;
		}
		else if(traffic == TrafficCondition.MEDIUM) {
			speed = 7.5;
		}
		else {
			speed = 3;
		}
		return (long)(distance/speed*3600*1000);
	}
}
