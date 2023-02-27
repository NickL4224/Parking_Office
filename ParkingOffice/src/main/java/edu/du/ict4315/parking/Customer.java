////////////////////
// The Customer class represents a parker in the Parking system
// File: Customer.java
// Author: Nick Ligocki
////////////////////
package edu.du.ict4315.parking;

import edu.du.ict4315.parking.support.IdMaker;
import edu.du.ict4315.parking.support.User;

public class Customer {
	private String id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Address address;
	private User userInfo = null;


	public String getCustomerName() {
		return firstName + " " + lastName;
	}

	public static class Builder {
		private String id;
		private String firstName;
		private String lastName;
		private String phoneNumber;
		private Address address;

		public Builder() {
		}

		public Builder withCustomerId(String id) {
			this.id = id;
			return this;
		}

		public Builder withFirstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder withLastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public Builder withPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Builder withAddress(Address address) {
			this.address = address;
			return this;
		}

		public Customer build() {
			Customer c = new Customer();
			c.id = this.id;
			c.firstName = this.firstName;
			c.lastName = this.lastName;
			c.phoneNumber = this.phoneNumber;
			c.address = this.address;

			return c;
		}

	}
	

	public Customer() {
		id = IdMaker.makeId("CUST-1");
		firstName = "";
		lastName = "";
		phoneNumber = "";
		address = new Address.Builder().build();
	}

	public Customer(String id, String firstName, String lastName, String phoneNumber, Address address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void storeUserInfo(String passwd) {
		userInfo = User.authorizeUser(id, passwd);
	}

	public User getUserInfo() {
		return userInfo;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Customer id: ");
		sb.append(id);
		sb.append("\n");
		sb.append(lastName);
		sb.append(", ");
		sb.append(firstName);
		sb.append("\n");
		sb.append(address);
		sb.append("\n");
		sb.append(phoneNumber);
		return sb.toString();
	}
}
