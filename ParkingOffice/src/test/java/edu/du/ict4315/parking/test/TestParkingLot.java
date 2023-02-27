////////////////////////////
// TestParkingLot.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import edu.du.ict4315.charges.decorator.CompactChargeDecorator;
import edu.du.ict4315.charges.decorator.FlatRateCalculator;
import edu.du.ict4315.charges.decorator.ParkingChargeCalculator;
import edu.du.ict4315.charges.decorator.WeekendChargeDecorator;
import edu.du.ict4315.currency.Money;
import edu.du.ict4315.observer.ParkingEvent;
import edu.du.ict4315.parking.Address;
import edu.du.ict4315.parking.Address.Builder;
import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingPermit;
import edu.du.ict4315.parking.RealParkingOffice;

class TestParkingLot {

	private RealParkingOffice office = new RealParkingOffice();
	private Address address = new Builder().withCity("Denver").build();
	private ParkingLot lot = new ParkingLot(office, "LOT ID", "LOT NAME", address);
	private Car car = new Car();
	private ParkingPermit permit = new ParkingPermit("PERMMIT ID", car, LocalDateTime.now());

	@Test
	void testConstructor() {
		assertEquals(lot.getId(), "LOT ID");
	}
	
	@Test
	void testCreateEvent() {
		RealParkingOffice office2 = new RealParkingOffice();
		Address address2 = new Builder().withCity("Denver").build();
		ParkingLot lot2 = new ParkingLot(office2, "LOT ID", "LOT NAME", address2);
		Car car2 = new Car();
		ParkingPermit permit2 = new ParkingPermit("PERMMIT ID", car2, LocalDateTime.now());
		LocalDateTime in = LocalDateTime.now();
		ParkingEvent eventCompare = new ParkingEvent(in, permit2, lot2);
		
		ParkingEvent event = lot.createEvent(in, permit2);
		
		assertEquals(event.getPermit(),eventCompare.getPermit());
	}

	@Test
	void testGetParkingCharges() {
		
		RealParkingOffice office2 = new RealParkingOffice();
		Address address2 = new Builder().withCity("Denver").build();
		ParkingLot lot2 = new ParkingLot(office2, "LOT ID", "LOT NAME", address2);
		Car car2 = new Car();
		ParkingPermit permit2 = new ParkingPermit("PERMMIT ID", car2, LocalDateTime.now());
		LocalDateTime in = LocalDateTime.now();
		
		ParkingChargeCalculator calculator = new CompactChargeDecorator(new WeekendChargeDecorator(new FlatRateCalculator()));
		Money chargeCompare = calculator.getParkingCharge(in, lot2, permit2);
		
		Money charge = lot.getParkingCharges(permit, in);
		
		assertEquals(charge, chargeCompare);
		
		
		
		
		
		
	}
}
