////////////////////////////
// TestRegisterCustomerCommand.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Properties;

import org.junit.jupiter.api.Test;

import edu.du.ict4315.parking.RegisterCustomerCommand;

class TestRegisterCustomerCommand {
	private RegisterCustomerCommand registerCustomer = new RegisterCustomerCommand();

	@Test
	void testExecute() {
		Properties properties = new Properties();

		properties.put("id", "CUST-1");
		properties.put("firstName", "Nick");
		properties.put("lastName", "Ligocki");
		properties.put("phone", "1234567895");

		String customerID = registerCustomer.execute(properties);

		assertEquals(customerID, "CUST-1");

	}

}
