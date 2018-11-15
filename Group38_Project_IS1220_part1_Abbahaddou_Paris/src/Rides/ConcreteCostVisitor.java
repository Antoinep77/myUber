package Rides;



import java.util.Date;

import customersAndDrivers.Customer;

public class ConcreteCostVisitor implements CostVisitor{
	@Override
	public double visit(UberX uberX) {
		Date date = uberX.getStartingDate();
		TrafficCondition currentTrafficCondition = TrafficCondition.setTrafficCondition(date);
		double trafficFactor = 1;
		switch(currentTrafficCondition) {
		case LOW :trafficFactor = 1;
			break;	
		case MEDIUM :trafficFactor = 1.1;
			break;	
		case HEAVY :trafficFactor = 1.5;
			break;	
		}
		double length = uberX.getLentgh();
		double rate = uberX.basicRates();
		Customer cust = uberX.getCustomer();
		double rideFare = length*rate*trafficFactor;
		cust.addMessageToBox("the price of a uberX ride to your destination is  : " + Double.toString(rideFare) + "  Euro");
		return rideFare;
		
	}
	@Override
	public double visit(UberBlack uberBlack) {
		Date date = uberBlack.getStartingDate();
		TrafficCondition currentTrafficCondition = TrafficCondition.setTrafficCondition(date);
		double trafficFactor = 1;
		switch(currentTrafficCondition) {
		case LOW :trafficFactor = 1;
			break;	
		case MEDIUM :trafficFactor = 1.3;
			break;	
		case HEAVY :trafficFactor = 1.6;
			break;
		}
		double length= uberBlack.getLentgh();
		double rate = uberBlack.basicRates();
		Customer cust = uberBlack.getCustomer();
		double rideFare = length*rate*trafficFactor;
		cust.addMessageToBox("the price of a uberBlack ride to your destination is  : " + Double.toString(rideFare) + "  Euro");
		return rideFare;
	}
	
	@Override
	public double visit(UberVan uberVan) {
		Date date = uberVan.getStartingDate();
		TrafficCondition currentTrafficCondition = TrafficCondition.setTrafficCondition(date);
		double trafficFactor = 1;
		switch(currentTrafficCondition) {
		case LOW :trafficFactor = 1;
			break;	
		case MEDIUM :trafficFactor = 1.1;
			break;	
		case HEAVY :trafficFactor = 1.2;
			break;
		}
		double length =  uberVan.getLentgh();
		double rate = uberVan.basicRates();
		Customer cust = uberVan.getCustomer();
		double rideFare = length*rate*trafficFactor;
		cust.addMessageToBox("the price of a uberVan ride to your destination is  : " + Double.toString(rideFare) + "  Euro");
		return rideFare;
	}
	
	@Override
	public double visit(UberPool uberPool) {
		Date date = uberPool.getStartingDate();
		TrafficCondition currentTrafficCondition = TrafficCondition.setTrafficCondition(date);
		double trafficFactor = 1;
		switch(currentTrafficCondition) {
		case LOW :trafficFactor = 1;
			break;	
		case MEDIUM :trafficFactor = 1.5;
			break;	
		case HEAVY :trafficFactor = 1.8;
			break;
		}
		double length = uberPool.getLentgh();
		double rate = uberPool.basicRates();
		Customer cust = uberPool.getCustomer();
		double rideFare = length*rate*trafficFactor;
		cust.addMessageToBox("the price of a uberPool ride to your destination is  : " + Double.toString(rideFare) + "  Euro");
		return rideFare;
	}
	
}
