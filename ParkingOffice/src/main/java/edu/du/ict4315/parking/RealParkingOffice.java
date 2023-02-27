////////////////////
// This object is at the heart of the Parking System.
// It is called "RealParkingOffice" in anticipation of creating a ParkingOffice interface later
// File: RealParkingOffice.java
// Author: Nick Ligocki
////////////////////
package edu.du.ict4315.parking;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.du.ict4315.currency.Money;
import edu.du.ict4315.parking.support.FileLoader;
import edu.du.ict4315.parking.support.User;

public class RealParkingOffice {
  private String parkingOfficeName = "Not set";
  private List<Customer> listOfCustomers = new ArrayList<Customer>();
  private List<ParkingLot> listOfParkingLots = new ArrayList<ParkingLot>();
  private Address parkingOfficeAddress = new Address.Builder().build();
  private PermitManager permitManager = new PermitManager();
  private TransactionManager transactionManager = new TransactionManager(this);

  public String register(Customer c) {
    listOfCustomers.add(c);
    return c.getId();
  }

  public String register(Car c) {
    return permitManager.register(c).getId();
  }

  public ParkingTransaction park(LocalDateTime d, ParkingPermit p, ParkingLot l) {
    return transactionManager.park(d, p, l);
  }

  public Money getParkingCharges(ParkingPermit p) {
    return transactionManager.getParkingCharges(p);
  }

  public Money getParkingCharges(Customer c) {
    return transactionManager.getParkingCharges(c);
  }

  public String getParkingOfficeName() {
    return parkingOfficeName;
  }

  public void setParkingOfficeName(String parkingOfficeName) {
    this.parkingOfficeName = parkingOfficeName;
  }

  public Address getParkingOfficeAddress() {
    return parkingOfficeAddress;
  }

  public void setParkingOfficeAddress(Address parkingOfficeAddress) {
    this.parkingOfficeAddress = parkingOfficeAddress;
  }

  // TODO: All of existing Customers, Cars, Permits, Lots should be persisted
  // TODO: These interfaces should be dependency-injected, not constructed
  // If an add lot method is ever added, the transaction manager must observer it.
  public RealParkingOffice() {
    ParkingLot[] lots = FileLoader.loadCsvFileParkingLot("data/parking_lots_du.csv");
    listOfParkingLots.addAll(Arrays.asList(lots));
    
    FileLoader.loadCsvUserFile("data/users.csv", this);
  }
  
  // The provided user and password are the administrator credentials
  public void saveUserFile(String user, String password) {
      FileLoader.saveCsvUserFile("data/users.csv", this,user,password);
  }

  @Override
  public String toString() {
    StringBuffer sb = new StringBuffer();
    sb.append("Parking Office: ");
    sb.append(parkingOfficeName);
    sb.append("\n");
    sb.append(parkingOfficeAddress);
    sb.append("\n");
    sb.append("Customer List\n");
    sb.append(listOfCustomers);
    sb.append("\n");
    sb.append("User list\n");
    sb.append(User.getUsers());
    sb.append("\n");
    sb.append("Parking Lots\n");
    sb.append(listOfParkingLots);
    sb.append("\n");

    return sb.toString();
  }

  public Customer getCustomer(String id) {
    Customer result = null;
    for (Customer c : listOfCustomers) {
      if (c.getId().equals(id)) {
        result = c;
        break;
      }
    }
    return result;
  }
  
  // Delegation from User
  public User authorizeUser(String id, String passwd) {
    return User.authorizeUser(id, passwd);
  }

  // Use delegation
  public ParkingPermit getParkingPermit(String id) {
    return permitManager.findPermit(id);
  }

  public ParkingLot getParkingLot(String id) {
    ParkingLot result = null;
    for (ParkingLot p : listOfParkingLots) {
      if (p.getId().equals(id)) {
        result = p;
        break;
      }
    }

    return result;
  }
  
  public List<Customer> getCustomerList() {
	  return listOfCustomers;
  }
}
