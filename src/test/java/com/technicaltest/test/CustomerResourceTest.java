package com.technicaltest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import com.technicaltest.api.Wealth;
import com.technicaltest.controller.CustomerResource;
import com.technicaltest.model.Account;
import com.technicaltest.model.Customer;
import com.technicaltest.repository.CustomerRepository;
import com.technicaltest.services.CustomerService;

public class CustomerResourceTest {

	private CustomerResource customerResource;
	
	@Before
	public void setup() {
		Set<Customer> customers = new HashSet<>();
		Customer alain = new Customer(UUID.fromString("3f389976-2066-4a1d-9065-76960ac238ec"), "Alain", "Pame", 33, "20 rue de la paix", "+33 621222324");
		alain.getAccounts().add(new Account(UUID.fromString("1f389976-2066-4a1d-9065-76960ac238ec"), LocalDateTime.of(2017, 5, 15, 11, 0), 500.0d));
		alain.getAccounts().add(new Account(UUID.fromString("2f389976-2066-4a1d-9065-76960ac238ec"), LocalDateTime.of(2017, 5, 16, 12, 0), 1500.0d));
		customers.add(alain);
		customerResource = new CustomerResource(new CustomerService(new CustomerRepository(customers)));
	}
	
	@Test
	public void createCustomer() {
		Customer cust = new Customer(null, "Pierre", "Lenom", 33, "20 rue de la paix", "+33 621222324");
		Response response = customerResource.createCustomer(cust);
		assertNotNull(response);
		assertEquals(201, response.getStatus());
	}

	@Test
	public void updateSuccessCustomer() {
		Customer alain = new Customer(UUID.fromString("3f389976-2066-4a1d-9065-76960ac238ec"), "Alain", "Pame", 34, "68 avenue de la République", "+33 621222324");
		Response response = customerResource.updateCustomer(alain);
		assertNotNull(response);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void updateFailCustomer() {
		Customer alain = new Customer(UUID.fromString("3f389976-2066-4a1d-9065-76960ac478ec"), "Alain", "Pame", 34, "68 avenue de la République", "+33 621222324");
		Response response = customerResource.updateCustomer(alain);
		assertNotNull(response);
		assertEquals(500, response.getStatus());
	}
	
	@Test
	public void getSuccessCustomer() {
		Response response = customerResource.getCustomer("3f389976-2066-4a1d-9065-76960ac238ec");
		assertNotNull(response);
		assertEquals(response.getStatus(), 200);
		Customer customer = (Customer) response.getEntity();
		assertEquals("3f389976-2066-4a1d-9065-76960ac238ec", customer.getId().toString());
		assertEquals("Alain", customer.getFirstName());
		assertEquals("Pame", customer.getLastName());
		assertEquals(new Integer(33), customer.getAge());
		assertEquals("20 rue de la paix", customer.getAddress());
		assertEquals("+33 621222324", customer.getPhoneNumber());
		assertNotNull(customer.getAccounts());
	}
	
	@Test
	public void getFailCustomer() {
		Response response = customerResource.getCustomer("3f389976-2066-4a1d-9065-76960ac478ec");
		assertNotNull(response);
		assertEquals(204, response.getStatus());
	}
	
	@Test
	public void deleteSuccessCustomer() {
		Response response = customerResource.deleteCustomer("3f389976-2066-4a1d-9065-76960ac238ec");
		assertNotNull(response);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void deleteFailCustomer() {
		Response response = customerResource.deleteCustomer("3f389976-2066-4a1d-9065-76960ac478ec");
		assertNotNull(response);
		assertEquals(500, response.getStatus());
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void getAllAccountsFromCustomer() {
		Response response = customerResource.getAllAccountsFromCustomer("3f389976-2066-4a1d-9065-76960ac238ec");
		assertNotNull(response);
		assertEquals(200, response.getStatus());
		Set<Account> acs = (Set<Account>) response.getEntity();
		assertNotNull(acs);
		assertEquals(2, acs.size());
		assertTrue(acs.contains(new Account(UUID.fromString("1f389976-2066-4a1d-9065-76960ac238ec"), LocalDateTime.of(2017, 5, 15, 11, 0), 500.0d)));
		assertTrue(acs.contains(new Account(UUID.fromString("2f389976-2066-4a1d-9065-76960ac238ec"), LocalDateTime.of(2017, 5, 16, 12, 0), 1500.0d)));
	}
	
	@Test
	public void getWealthFromCustomer() {
		Response response = customerResource.getWealthFromCustomer("3f389976-2066-4a1d-9065-76960ac238ec");
		assertNotNull(response);
		assertEquals(200, response.getStatus());
		Wealth balance = (Wealth) response.getEntity();
		assertEquals(2000.0d, balance.getBalance(), 0.0d);
		
	}
}
