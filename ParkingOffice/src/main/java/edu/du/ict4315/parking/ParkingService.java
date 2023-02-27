////////////////////////////
// ParkingService.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.du.ict4315.currency.Money;

public class ParkingService {

	private Map<String, Command> commands = new HashMap<>();
	private static final Logger logger = Logger.getLogger(ParkingService.class.getName());
	RealParkingOffice office = new RealParkingOffice();
	RegisterCustomerCommand customer = new RegisterCustomerCommand();
	RegisterCarCommand car = new RegisterCarCommand();

	public ParkingService() {
		return;
	}

	public String performCommand(String command, String[] args) {
		Properties parameters = new Properties();
		List<String> messages = new ArrayList<>();
		logger.log(Level.INFO, "Performing {0} command", command);
		messages.add(command + ": " + String.join(", ", args));
		switch (command) {
		case "CUSTOMER":
			if (checkNumberOfParameters(5, args.length)) {
				for (String arg : args) {
					String[] result = splitArgs(arg);
					parameters.put(result[0], result[1]);
				}
			} else {
				messages.add("Cannot create customer: wrong number of parameters");
			}
			
			customer.execute(parameters);

			break;
		case "CAR":
			if (checkNumberOfParameters(3, args.length)) {

				for (String arg : args) {
					String[] result = splitArgs(arg);
					parameters.put(result[0], result[1]);
				}

			} else {
				messages.add("Cannot create car permit: wrong number of parameters");
			}
			
			car.execute(parameters);
			break;
		case "CHARGES":
			if(checkNumberOfParameters(1, args.length)) {
				String id = args[0];
				Customer c = office.getCustomer(id);
				Money charge = office.getParkingCharges(c);
				messages.add(charge.toString());
			} else {
				messages.add("Cannot get Charge: wrong number of parameters");
			}
			break;
		default:
			messages.add("Command unknown");
		}
		return String.join("\n", messages);

	}
	private static boolean checkNumberOfParameters(int expected, int provided) {
		boolean result = true;
		if (provided < expected) {
			logger.log(Level.SEVERE, "Not enough parameters! Expected {0} received {1}",
					new Object[] { expected, provided });
			result = false;
		}
		return result;
	}

	private String[] splitArgs(String value) {
		String[] splitArray = {};
		String[] result = {};

		splitArray = value.split("=");

		if (splitArray.length == 2) {
			result[0] = splitArray[0];
			result[1] = splitArray[1];
		} else if (splitArray.length == 1) {
			result[0] = null;
			result[1] = splitArray[0];
		}

		return result;
	}
}
