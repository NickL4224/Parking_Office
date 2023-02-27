////////////////////
// This class represents the Parking Lot
// File: ParkingLot.java
// Author: Nicholas Ligocki
////////////////////
package edu.du.ict4315.parking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import edu.du.ict4315.charges.decorator.CompactChargeDecorator;
import edu.du.ict4315.charges.decorator.FlatRateCalculator;
import edu.du.ict4315.charges.decorator.ParkingChargeCalculator;
import edu.du.ict4315.charges.decorator.ParkingChargeDecorator;
import edu.du.ict4315.charges.decorator.WeekendChargeDecorator;
import edu.du.ict4315.currency.Money;
import edu.du.ict4315.observer.ParkingEvent;
import edu.du.ict4315.observer.ParkingObserver;
import edu.du.ict4315.parking.charges.strategy.DefaultStrategy;
import edu.du.ict4315.parking.charges.strategy.ParkingChargeStrategyCalculator;

public class ParkingLot {
	private String id;
	private String name;
	private Address address;
	private Money baseRate = Money.of(5.00);
	private ParkingChargeStrategyCalculator strategy;
	private List<ParkingObserver> observers = new ArrayList<>();
	private RealParkingOffice office;

	public ParkingLot(RealParkingOffice office, String id, String name, Address address) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.office = office;
		this.observers = new ArrayList<>();
		setStrategy(new DefaultStrategy());
	}

	public ParkingLot(String id, String name, Address address, Money baseRate) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.baseRate = baseRate;
		this.observers = new ArrayList<>();
	}

	public Money getParkingCharges(ParkingPermit permit, LocalDateTime in) {
		ParkingChargeCalculator parkingCharge = new FlatRateCalculator();

		parkingCharge = new CompactChargeDecorator(new WeekendChargeDecorator(parkingCharge));
		
		Money charge = parkingCharge.getParkingCharge(in, this, permit);

		return charge;
	}

	public Money getBaseRate() {
		return baseRate;
	}


	public ParkingEvent createEvent(LocalDateTime in, ParkingPermit permit) {
		ParkingEvent e = new ParkingEvent(in, permit, this);

		return e;
	}

	// Notifys the people in the observer list
	public void notifyObservers(ParkingEvent pe) {
		for (ParkingObserver p : observers) {
			p.update(pe);
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append(id);
		sb.append("\n");
		sb.append(name);
		sb.append("\n");
		sb.append(address);
		sb.append("\n");
		sb.append(getStrategy());

		return sb.toString();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public ParkingChargeStrategyCalculator getChargeStrategy() {
		return getStrategy();
	}

	public void setChargeStrategy(ParkingChargeStrategyCalculator ps) {
		setStrategy(ps);
	}

	public void enterLot(LocalDateTime in, String permitId) {
		ParkingPermit permit = office.getParkingPermit(permitId);

		ParkingEvent event = new ParkingEvent(in, permit, this);
		subscribe(event);

		notifyObservers(createEvent(in, permit));

	}

	public void subscribe(ParkingEvent event) {
		observers.add(new ParkingObserver(event));
	}

	public void unsubscribe(ParkingObserver observer) {
		observers.remove(observer);
	}

	public ParkingChargeStrategyCalculator getStrategy() {
		return strategy;
	}

	public void setStrategy(ParkingChargeStrategyCalculator strategy) {
		this.strategy = strategy;
	}

}
