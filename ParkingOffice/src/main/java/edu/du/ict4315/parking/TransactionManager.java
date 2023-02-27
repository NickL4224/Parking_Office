//////////////////////////
// This class keeps the collection of active transactions.
// In a larger system it might be implemented with a database
// File: TransactionManager.java
// Author: Nick Ligocki
//////////////////////////
package edu.du.ict4315.parking;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import edu.du.ict4315.currency.Money;

// The methods of the transaction manager are implemented with the stream() interface (Java 8)
// This allows examples of map() and reduce(), as well as use of lambdas
// Of course, these aren't required for the solution. Do you think they are more elegant?
public class TransactionManager {
  
  private List<ParkingTransaction> transactions = new ArrayList<ParkingTransaction>();
  public TransactionManager(RealParkingOffice office) {
  }

  public ParkingTransaction park(LocalDateTime d, ParkingPermit p, ParkingLot l) {
    ParkingTransaction transaction = null;
    if ( l != null && p != null && l != null ) {
      Money money = l.getParkingCharges(p, d);     
      Instant transactionDate = d.toInstant(ZoneOffset.UTC);
      transaction = new ParkingTransaction.Builder(p, money).transactionDate(transactionDate).dateTime(d).parkingLot(l).build();
      transactions.add(transaction);
    } else {
    }
    return transaction;
  }

  public Money getParkingCharges(Customer c) {
    List<ParkingTransaction> customerTransactions;
    // First, let's get a list of the transactions for the customer.
    customerTransactions = transactions.stream()
            .filter(transaction -> transaction.getPermit().getCar().getOwner().equals(c))
            .collect(Collectors.toList());

    // Now lets add up all the charged amounts
    Money result = customerTransactions.stream()
            .map(transaction -> transaction.getChargedAmount())
            .reduce(Money.of(0.0), (a, b) -> Money.add(a, b));

    return result;
  }

  public Money getParkingCharges(ParkingPermit p) {
    return transactions.stream()
            .filter(transaction -> transaction.getPermit().equals(p))
            .map(transaction -> transaction.getChargedAmount())
            .reduce(Money.of(0.0), (a, b) -> Money.add(a, b));
  }

}
