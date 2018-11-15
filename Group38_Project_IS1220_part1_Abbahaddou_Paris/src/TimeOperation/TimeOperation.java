package TimeOperation;

import java.sql.Time;

import Rides.TrafficCondition;

public class TimeOperation {
	public static int getTimeInSeconde(Time t) {
		return 3600*t.getHours() + 60*t.getMinutes() + t.getSeconds();
	}
	
	public static Time durationTime(double distance, TrafficCondition traffic) {
		if(traffic == TrafficCondition.LOW) {
			return null;
		}
}
