////////////////////////////
// ParkingChargeStrategyFactory.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking.charges.factory;


import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategyCalculator;

public interface ParkingChargeStrategyFactory {
	ParkingChargeStrategyCalculator getStrategy();

}
