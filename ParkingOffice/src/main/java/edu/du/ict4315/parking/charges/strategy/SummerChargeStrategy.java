////////////////////////////
// SummerChargeStrategy.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking.charges.strategy;

import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingPermit;
import edu.du.ict4315.parking.PermitManager;

public class SummerChargeStrategy implements ParkingChargeStrategyCalculator {
	
	PermitManager manager = new PermitManager();
	//Dtaes that "Summer" starts... not accurate dates
	LocalDateTime summerStart = LocalDateTime.of(2022, 6, 1, 0, 0);
	LocalDateTime summerEnd = LocalDateTime.of(2022, 8, 15, 0, 0);

	@Override
	public Money getCharge(String permitId, LocalDateTime in, ParkingLot lot) {
		Money charge = lot.getBaseRate();
		Money subtract = Money.of(1.00);
		
		//Subtracts one dollar from the charge for summer months
		if (in.isAfter(summerStart) && in.isBefore(summerEnd)) {
			charge = Money.subtract(charge, subtract);
		}
		
		ParkingPermit permit = manager.findPermit(permitId);

		CarType carType = permit.getCar().getType();

		// Checks if the car is compact and assigns a 20% discount if true
		if (carType.equals(CarType.COMPACT)) {
			charge = Money.times(charge, .8);
		}
		
		
		
		return charge;
	}
	
	

}
