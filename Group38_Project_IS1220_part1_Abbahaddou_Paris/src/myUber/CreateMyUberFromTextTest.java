package myUber;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import customersAndDrivers.Driver;

class CreateMyUberFromTextTest {

	@Test
	void test() throws Exception {
		String filePath = "scenario1.txt";
		MyUber myUber = CreateMyUberFromText.executeScenarioFromText(filePath);
	}

}
