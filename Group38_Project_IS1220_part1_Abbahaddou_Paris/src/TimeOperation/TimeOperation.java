package TimeOperation;

import java.sql.Time;

import Rides.TrafficCondition;

public class TimeOperation {
	
	public static int getTimeInSeconde(Time t) {
		return 3600*t.getHours() + 60*t.getMinutes() + t.getSeconds();
	}
	
	//return time (in ms) to travel a distance with a traffic condition
	public static long rideDurationTime(double distance, TrafficCondition traffic) {
		double speed ;
		if(traffic == TrafficCondition.LOW) {
			speed = 3000/3600;
		}
		else if(traffic == TrafficCondition.MEDIUM) {
			speed = 7500/3600;
		}
		else {
			speed = 15000/3600;
		}
		return (long)(distance/speed);
	}
}
