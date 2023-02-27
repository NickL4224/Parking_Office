////////////////////////////
// WeekendChargeStrategyFactory.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking.charges.factory;


import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategyCalculator;
import edu.du.ict4315.parking.charges.strategy.SummerChargeStrategy;

public class SummerChargeFactory implements ParkingChargeStrategyFactory {
	
	public SummerChargeStrategy strategy;

	@Override
	public ParkingChargeStrategyCalculator getStrategy() {
		return strategy;
	}

}
