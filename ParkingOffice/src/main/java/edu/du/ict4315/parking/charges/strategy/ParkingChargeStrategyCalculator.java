////////////////////////////
// ParkingChargeStrategyCalculator.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking.charges.strategy;

import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.ParkingLot;

public interface ParkingChargeStrategyCalculator {
	Money getCharge(String permitId, LocalDateTime in, ParkingLot lot);
}
