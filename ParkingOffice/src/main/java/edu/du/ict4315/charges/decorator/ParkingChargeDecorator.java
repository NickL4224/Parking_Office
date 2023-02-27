////////////////////////////
// ParkingChargeDecorator.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.charges.decorator;

public abstract class ParkingChargeDecorator extends ParkingChargeCalculator {
	
	private final ParkingChargeCalculator parkingCharge;
	
	public ParkingChargeDecorator(ParkingChargeCalculator charge) {
		parkingCharge = charge;
	}
	
	public final ParkingChargeCalculator getParkingCharge() {
		return parkingCharge;
	}
	
}