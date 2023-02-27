///////////////////////////
// ParkingEvent.java
// Author: Nick Ligocki
///////////////////////////
package edu.du.ict4315.observer;


import java.time.LocalDateTime;

import edu.du.ict4315.parking.ParkingLot;
import edu.du.ict4315.parking.ParkingPermit;

public class ParkingEvent {
	
	
	private ParkingLot lot;
	private ParkingPermit permit;
	private LocalDateTime time;
	
	
	public ParkingEvent(LocalDateTime time, ParkingPermit permit, ParkingLot lot) {
		this.time = time;
		this.permit = permit;
		this.lot = lot;
	}


	public ParkingLot getLot() {
		return lot;
	}


	public ParkingPermit getPermit() {
		return permit;
	}


	public LocalDateTime getTime() {
		return time;
	}
	
	

	
}
