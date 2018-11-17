package customersAndDrivers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import GPS.GPScoordinates;

class CustomerTest {
	

	@Test
	void whenAskingNameGetRealName() {
		Customer cust1 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		assertEquals(cust1.getCustomerName(),"prenom");
	}

	@Test
	void whenCreatingCustomerIdAreDifferent() {
		Customer cust1 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		Customer cust2 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		assertTrue(cust1.getCustomerID() != cust2.getCustomerID());
	}
	
	@Test
	void whenAskingSurnameGetRealSurname() {
		Customer cust1 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		assertEquals(cust1.getCustomerSurName(),"nom");
	}
	
	@Test
	void whenAskingCoordsGetRealCoords() {
		Customer cust1 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		assertEquals(cust1.getCustomerPosition(),new GPScoordinates(0, 1));
	}
	
	@Test
	void whenSettingCoordsGetRealCoords() {
		Customer cust1 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		cust1.setCustomerPosition(new GPScoordinates(1, 2));
		assertEquals(cust1.getCustomerPosition(),new GPScoordinates(1, 2));
	}
	
	@Test
	void whenSettingCreditCardGetCard() {
		Customer cust1 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		cust1.setCreditCardNumber(1267);
		assertEquals(cust1.getCreditCardNumber(),1267);
	}

	@Test
	void whenAddingMessageGetTheMessage() {
		Customer cust1 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		cust1.addMessageToBox("test");
		assertEquals(cust1.getMessagebox().get(0), "test");
	}
	
	@Test
	void whenAddingMessagesGetAllMessages() {
		Customer cust1 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		cust1.addMessageToBox("test");
		cust1.addMessageToBox("test2");
		assertEquals(cust1.getMessagebox().get(1), "test2");
	}
	
	@Test
	void whenSpendingAmount() {
		Customer cust1 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		cust1.spendAmount(30);
		assertEquals(cust1.getTotaleAmountPaid(), 30);
	}
	
	void whenSpendingManyAmount() {
		Customer cust1 = new Customer("prenom","nom",new GPScoordinates(0, 1), 123);
		cust1.spendAmount(30);
		cust1.spendAmount(15);
		cust1.spendAmount(20);
		assertEquals(cust1.getTotaleAmountPaid(), 65);
	}


}
