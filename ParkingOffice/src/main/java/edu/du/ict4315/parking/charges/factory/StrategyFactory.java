////////////////////////////
// StrategyFactory.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking.charges.factory;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import edu.du.ict4315.parking.charges.strategy.DefaultStrategy;
import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategyCalculator;
import edu.du.ict4315.parking.charges.strategy.SummerChargeStrategy;
import edu.du.ict4315.parking.charges.strategy.WeekendChargeStrategy;

public class StrategyFactory {

	private LocalDateTime summerStart = LocalDateTime.of(2022, 6, 1, 0, 0);
	private LocalDateTime summerEnd = LocalDateTime.of(2022, 8, 15, 0, 0);

	public ParkingChargeStrategyCalculator giveStrategy(LocalDateTime in) {
		ParkingChargeStrategyCalculator strategy = new DefaultStrategy();
		

		if (in.isAfter(summerStart) && in.isBefore(summerEnd)) {
			strategy = new SummerChargeStrategy();
			return strategy;
		} else if (in.getDayOfWeek().equals(DayOfWeek.SATURDAY) || in.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
			strategy = new WeekendChargeStrategy();
			return strategy;
		} else {
			strategy = new DefaultStrategy();
			return strategy;
		}
	}

}
