////////////////////////////
// DefaultStrategy.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking.charges.strategy;

import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingPermit;
import edu.du.ict4315.parking.PermitManager;

public class DefaultStrategy implements ParkingChargeStrategyCalculator {

	PermitManager manager = new PermitManager();

	// returns the base rate for the lot involved and checks if the car is compact
	@Override
	public Money getCharge(String permitId, LocalDateTime in, ParkingLot lot) {
		Money charge = lot.getBaseRate();
		ParkingPermit permit = manager.findPermit(permitId);

		CarType carType = permit.getCar().getType();

		// Checks if the car is compact and assigns a 20% discount if true
		if (carType.equals(CarType.COMPACT)) {
			charge = Money.times(charge, .8);
		}

		return charge;
	}

}
