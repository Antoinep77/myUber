package Rides;
import java.sql.Time;
import java.util.Date;

public enum TrafficCondition {
	LOW,
	MEDIUM,
	HEAVY;
	
	// we create a new constructor that takes the time as a parameter and returns the traffic condition
	public static TrafficCondition setTrafficCondition(Date date) {
		Time time = new Time(date.getHours(), date.getMinutes(), date.getSeconds());
		Time time1 = new Time(7, 00, 00);
		Time time2 = new Time(11, 00, 00);
		Time time3 = new Time(17, 00, 00);
		Time time4 = new Time(22, 00, 00);
		double lowTrafficProbability;
		double meduimTrafficProbability;
		double heavyTrafficProbabitiy;
		if(time.after(time1) && time.before(time2)){
			lowTrafficProbability = 0.05;
			meduimTrafficProbability = 0.20;
			heavyTrafficProbabitiy = 0.75;
			//
			
			
		}else if(time.after(time2) && time.before(time3)){
			lowTrafficProbability = 0.15;
			meduimTrafficProbability = 0.70;
			heavyTrafficProbabitiy = 0.15;
		}else if(time.after(time3) && time.before(time4)) {
			lowTrafficProbability = 0.01;
			meduimTrafficProbability = 0.04;
			heavyTrafficProbabitiy = 0.95;
		}else {
			lowTrafficProbability = 0.95;
			meduimTrafficProbability = 0.04;
			heavyTrafficProbabitiy = 0.01;
		}
		
		double randomNumber = Math.random();
		
		if(randomNumber < lowTrafficProbability) {
			return TrafficCondition.LOW;
		}else if(randomNumber >= lowTrafficProbability && randomNumber < lowTrafficProbability + meduimTrafficProbability) {
			return TrafficCondition.MEDIUM;
		}else {
			return TrafficCondition.HEAVY;
		}
		  

	}
}
