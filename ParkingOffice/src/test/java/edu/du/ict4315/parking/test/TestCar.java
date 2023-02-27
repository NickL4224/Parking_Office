////////////////////////////
// TestCar.java
// Author: Nick Ligocki
////////////////////////////
package edu.du.ict4315.parking.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.du.ict4315.parking.Car;
import edu.du.ict4315.parking.CarType;
import edu.du.ict4315.parking.Customer;
import edu.du.ict4315.parking.Customer.Builder;

class TestCar {

	Customer customer = new Builder().withFirstName("Nick").withLastName("Ligocki").build();
	CarType carType = CarType.COMPACT;
	Car car = new Car(carType, "ABC123", customer);
	
	@Test
	void testConstructor() {
		
		assertEquals(car.getLicensePlate(), "ABC123");
		
	}
	
	

}
