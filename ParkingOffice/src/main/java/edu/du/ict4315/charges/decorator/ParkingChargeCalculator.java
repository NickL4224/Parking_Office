////////////////////////////
// ParkingChargeCalculator.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.charges.decorator;

import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingPermit;

public abstract class ParkingChargeCalculator {
	
	public ParkingChargeCalculator() {
	}
	
	public abstract Money getParkingCharge(LocalDateTime in, ParkingLot lot, ParkingPermit permit);

}
