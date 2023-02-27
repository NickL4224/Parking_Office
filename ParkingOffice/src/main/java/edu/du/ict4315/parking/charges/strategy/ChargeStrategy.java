///////////////////////////
// ChargeStrategy.java
// Author: Nick Ligocki
///////////////////////////
package edu.du.ict4315.parking.charges.strategy;

import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.ParkingLot;

public class ChargeStrategy {
	

	private ParkingChargeStrategyCalculator strategy;
	
	
	public ChargeStrategy(ParkingChargeStrategyCalculator strategy) {
		this.strategy = strategy;
	}
	
	public Money executeStrategy(String permitId, LocalDateTime in, ParkingLot lot) {
		Money charge = lot.getStrategy().getCharge(permitId, in, lot);
		return charge;
	}

}
