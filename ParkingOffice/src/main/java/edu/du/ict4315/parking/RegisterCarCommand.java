////////////////////////////
// RegisterCarCommand.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking;

import java.util.Properties;

public class RegisterCarCommand implements Command {

	RealParkingOffice office = new RealParkingOffice();

	private Car checkParameters(Properties p) {

		String type = null;
		String license = null;
		Customer owner = null;

		//Owner is null
		type = p.getProperty("type");
		license = p.getProperty("license");
		//owner = p.getProperty("owner");

		checkLicensePlate(license);
		CarType carType = checkType(type);

		Car c = new Car(carType, license, owner);
		return c;
	}

	@Override
	public String getCommandName() {
		return "CAR";
	}

	@Override
	public String getDisplayName() {
		return "CREATE A NEW CAR";
	}

	/*
	 * OWNER IS CURRENTLY NULL. I DO NOT KNOW HOW TO CHECK THE ENTIRE OBJECT SO I AM
	 * LEAVING IT NULL LIKE ADDRESS IN THE REGISTERCUSTOMERCOMMAND FILE
	 */
	@Override
	public String execute(Properties params) {
		Car c = checkParameters(params);
		office.register(c);

		return c.getLicensePlate();
	}

	private static String checkLicensePlate(String plate) {
		String result = plate;
		if (result == null) {
			plate = "Unknown";
		}
		return result;
	}

	private CarType checkType(String type) {
		CarType carType = null;

		if (type.equalsIgnoreCase("SUV")) {
			carType = CarType.SUV;
		} else if (type.equalsIgnoreCase("COMPACT")) {
			carType = CarType.COMPACT;
		} else {
			System.out.println("Unknown car type");
		}
		return carType;
	}

}
