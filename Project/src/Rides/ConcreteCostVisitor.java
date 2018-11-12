package Rides;

<<<<<<< HEAD
import java.sql.Time;

public abstract class ConcreteCostVisitor implements CostVisitor{
	
=======
public class ConcreteCostVisitor implements CostVisitor{
>>>>>>> branch 'master' of git@github.com:Antoinep77/myUber.git
	@Override
	public double visit(UberX uberX) {
		Time time = uberX.getTime();
		TrafficCondition currentTrafficCondition = TrafficCondition.setTrafficCondition(time);
		double trafficFactor = 1;
		switch(currentTrafficCondition) {
		case LOW :trafficFactor = 1;
			break;	
		case MEDUIM :trafficFactor = 1.1;
			break;	
		case HEAVY :trafficFactor = 1.5;
			break;	
		}
		double length = uberX.getLentgh();
		double rate = uberX.basicRates();
		return length*rate*trafficFactor;
		
		
	}
	@Override
	public double visit(UberBlack uberBlack) {
		Time time = uberBlack.getTime();
		TrafficCondition currentTrafficCondition = TrafficCondition.setTrafficCondition(time);
		double trafficFactor = 1;
		switch(currentTrafficCondition) {
		case LOW :trafficFactor = 1;
			break;	
		case MEDUIM :trafficFactor = 1.3;
			break;	
		case HEAVY :trafficFactor = 1.6;
			break;
		}
		double length= uberBlack.getLentgh();
		double rate = uberBlack.basicRates();
		return length*rate*trafficFactor;
	}
	@Override
	public double visit(UberVan uberVan) {
		Time time = uberVan.getTime();
		TrafficCondition currentTrafficCondition = TrafficCondition.setTrafficCondition(time);
		double trafficFactor = 1;
		switch(currentTrafficCondition) {
		case LOW :trafficFactor = 1;
			break;	
		case MEDUIM :trafficFactor = 1.1;
			break;	
		case HEAVY :trafficFactor = 1.2;
			break;
		}
		double length =  uberVan.getLentgh();
		double rate = uberVan.basicRates();
		return length*rate*trafficFactor;
	}
	@Override
	public double visit(UberPool uberPool) {
		Time time = uberPool.getTime();
		TrafficCondition currentTrafficCondition = TrafficCondition.setTrafficCondition(time);
		double trafficFactor = 1;
		switch(currentTrafficCondition) {
		case LOW :trafficFactor = 1;
			break;	
		case MEDUIM :trafficFactor = 1.5;
			break;	
		case HEAVY :trafficFactor = 1.8;
			break;
		}
		double length = uberPool.getLentgh();
		double rate = uberPool.basicRates();
		return length*rate*trafficFactor;
	}
	public static void main(String[] args) {
		Car car1 = new 
	}
	
}
