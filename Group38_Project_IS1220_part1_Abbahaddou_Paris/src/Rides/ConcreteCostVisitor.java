package Rides;

import java.sql.Time;

import customersAndDrivers.Customer;

public class ConcreteCostVisitor implements CostVisitor{
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
		Customer cust = uberX.getCustomer();
		double rideFare = length*rate*trafficFactor;
		cust.addMessageToBox("the price of a uberX ride to yout destination is  : " + Double.toString(rideFare) + "  Euro");
		return rideFare;
		
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
		Customer cust = uberBlack.getCustomer();
		double rideFare = length*rate*trafficFactor;
		cust.addMessageToBox("the price of a uberBlack ride to yout destination is  : " + Double.toString(rideFare) + "  Euro");
		return rideFare;
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
		Customer cust = uberVan.getCustomer();
		double rideFare = length*rate*trafficFactor;
		cust.addMessageToBox("the price of a uberVan ride to yout destination is  : " + Double.toString(rideFare) + "  Euro");
		return rideFare;
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
		Customer cust = uberPool.getCustomer();
		double rideFare = length*rate*trafficFactor;
		cust.addMessageToBox("the price of a uberPool ride to yout destination is  : " + Double.toString(rideFare) + "  Euro");
		return rideFare;
	}
	
}
