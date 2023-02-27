////////////////////////////
// WeekendChargeStrategy.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking.charges.strategy;

import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingPermit;
import edu.du.ict4315.parking.PermitManager;


public class WeekendChargeStrategy implements ParkingChargeStrategyCalculator {
	
	PermitManager manager = new PermitManager();
	
	
	@Override
	public Money getCharge(String permitId, LocalDateTime in, ParkingLot lot) {
		//Gets the base rate for the lot
		Money baseRate = lot.getBaseRate();
		Money total = baseRate;
		
		ParkingPermit permit = manager.findPermit(permitId);
		
		CarType carType = permit.getCar().getType();
		
		//Checks if the car is compact and assigns a 20% discount if true
		if (carType.equals(CarType.COMPACT)) {
			total = Money.times(total, .8);
		}
	
		//Adds one dollar to the total fee for a weekend fee
		total = Money.add(total, Money.of(100));
		
		//Returns the base rate plus one dollar
		return total;
	}

}
