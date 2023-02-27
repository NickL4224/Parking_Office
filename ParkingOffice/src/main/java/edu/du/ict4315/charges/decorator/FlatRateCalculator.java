////////////////////////////
// FlatRateChargeDecorator.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.charges.decorator;

import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingPermit;

public class FlatRateCalculator extends ParkingChargeCalculator {
	
	public FlatRateCalculator(){
		super();
	}


	@Override
	public Money getParkingCharge(LocalDateTime in, ParkingLot lot, ParkingPermit permit) {
		Money charge = lot.getBaseRate();
		return charge;
	}
}
