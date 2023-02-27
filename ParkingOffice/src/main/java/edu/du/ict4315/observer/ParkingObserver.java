///////////////////////////
// ParkingObserver.java
// Author: Nick Ligocki
///////////////////////////
package edu.du.ict4315.observer;

import edu.du.ict4315.parking.ParkingTransaction;

public class ParkingObserver implements ParkingAction{
	
	private ParkingTransaction transaction;
	private ParkingEvent event; 
	
	public ParkingObserver(ParkingEvent event) {
		this.event = event;
	}

	@Override
	public void update(ParkingEvent event) {
		transaction = new ParkingTransaction(event.getTime(), event.getPermit(), event.getLot(), event.getLot().getBaseRate());
		System.out.println(transaction.getPermit().getId());
	}

	public ParkingEvent getParkingEvent() {
		return event;
	}
	
	

}
