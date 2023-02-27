//////////////////////////
// This class represents a parking transaction event.
// This class is immutable.
// File: ParkingTransaction.java
// Author: Nick Ligocki
//////////////////////////
package edu.du.ict4315.parking;

import java.time.Instant;
import java.time.LocalDateTime;

import edu.du.ict4315.currency.Money;

public class ParkingTransaction {
	private Instant transactionDate;
	private LocalDateTime date;
	private ParkingPermit permit;
	private ParkingLot parkingLot;
	private Money chargedAmount;

	public ParkingTransaction(LocalDateTime d, ParkingPermit p, ParkingLot l, Money m) {
		transactionDate = Instant.now();
		date = d;
		permit = p;
		parkingLot = l;
		chargedAmount = m;
	}

	public static class Builder {

		private Instant transactionDate;
		private LocalDateTime date;
		private ParkingPermit permit;
		private ParkingLot parkingLot;
		private Money chargedAmount;

		public Builder(ParkingPermit permit, Money chargedAmount) {
			this.permit = permit;
			this.chargedAmount = chargedAmount;
		}

		public Builder dateTime(LocalDateTime date) {
			this.date = date;
			return this;
		}

		public Builder parkingLot(ParkingLot parkingLot) {
			this.parkingLot = parkingLot;
			return this;
		}

		public Builder transactionDate(Instant transactionDate) {
			this.transactionDate = transactionDate;
			return this;
		}

		public ParkingTransaction build() {
			ParkingTransaction p = new ParkingTransaction(this);
			return p;
		}

	}

	private ParkingTransaction(Builder builder) {
	}

	public Money getChargedAmount() {
		return chargedAmount;
	}

	public ParkingPermit getPermit() {
		return permit;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public ParkingLot getParkingLot() {
		return parkingLot;
	}

	public Instant getTransactionDate() {
		return transactionDate;
	}

	@Override
	public String toString() {
		return "ParkingTransaction [transactionDate=" + transactionDate + ", date=" + date + ", permit=" + permit
				+ ", parkingLot=" + parkingLot + ", chargedAmount=" + chargedAmount + "]";
	}

}
