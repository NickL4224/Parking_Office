////////////////////////////
// RegisterCustomerCommand.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import edu.du.ict4315.parking.Customer.Builder;

public class RegisterCustomerCommand implements Command {

	private RealParkingOffice office = new RealParkingOffice();
	private List<String> messages = new ArrayList<>();

	private Customer checkParameters(Properties p) {

		String id = p.getProperty("id");
		String firstName = p.getProperty("firstName");
		String lastName = p.getProperty("lastName");
		String phone = p.getProperty("phone");

		Customer c = checkCustomer(id, firstName, lastName, phone);
		office.register(c);
		return c;
	}

	@Override
	public String getCommandName() {
		return "CUSTOMER";
	}

	@Override
	public String getDisplayName() {
		return "Create a new customer";
	}

	@Override
	public String execute(Properties params) {
		Customer c = checkParameters(params);
		return c.getId();
	}

	private Customer checkCustomer(String customerId, String firstName, String lastName, String phone) {
		Customer customer = office.getCustomer(customerId);
		if (customer == null) {

			customer = new Builder().withCustomerId(customerId).withFirstName(firstName)
					.withLastName(lastName).withPhoneNumber(phone).build();

		}
		return customer;
	}

}
