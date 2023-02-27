////////////////////////////
// CompactChargeDecorator.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.charges.decorator;

import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingPermit;

public class CompactChargeDecorator extends ParkingChargeDecorator{

	public CompactChargeDecorator(ParkingChargeCalculator charge) {
		super(charge);
	}

	
	//Gives a 20% discount for a compact vehicle
	@Override
	public Money getParkingCharge(LocalDateTime in, ParkingLot lot, ParkingPermit permit) {
		
		Money amount = getParkingCharge(in, lot, permit);
		
		
		if (permit.getCar().getType().equals(CarType.COMPACT)) {
			amount = giveCompactDiscount(amount);
		}
		
		return amount;
	}
	
	private Money giveCompactDiscount(Money amount) {
		amount = Money.times(amount, .8);
		return amount;
	}

}