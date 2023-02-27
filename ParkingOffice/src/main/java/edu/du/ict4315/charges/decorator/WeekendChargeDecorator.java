////////////////////////////
// WeekendChargeDecorator.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.charges.decorator;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingPermit;

public class WeekendChargeDecorator extends ParkingChargeDecorator{

	public WeekendChargeDecorator(ParkingChargeCalculator charge) {
		super(charge);
	}

	
	//Gives a 10% discount
	@Override
	public Money getParkingCharge(LocalDateTime in, ParkingLot lot, ParkingPermit permit) {
		
		Money amount = getParkingCharge(in, lot, permit);
		
		
		if (in.getDayOfWeek().equals(DayOfWeek.SATURDAY) || in.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			amount = giveWeekendDiscount(amount);
		}
		return amount;
	}
	
	private Money giveWeekendDiscount(Money amount) {
		amount = Money.times(amount, .9);
		return amount;
	}

}
