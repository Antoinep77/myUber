package myUber;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import customersAndDrivers.Driver;

class CreateMyUberFromTextTest {

	@Test
	void test() throws Exception {
		String filePath = "C:\\Users\\Antoine\\git\\Uber\\Group38_Project_IS1220_part1_Abbahaddou_Paris\\scenario1.txt";
		MyUber myUber = CreateMyUberFromText.executeScenarioFromText(filePath);
	}

}
