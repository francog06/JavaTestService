package com.technicaltest.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;

import com.technicaltest.api.Link;
import com.technicaltest.api.Transaction;
import com.technicaltest.controller.BankResource;
import com.technicaltest.model.Account;
import com.technicaltest.model.Customer;
import com.technicaltest.repository.AccountRepository;
import com.technicaltest.repository.CustomerRepository;
import com.technicaltest.services.BankService;

public class BankResourceTest {

	private BankResource bankResource;

	@Before
	public void setup() {
		Set<Account> acs = new HashSet<>();
		Account ac = new Account(UUID.fromString("2f389976-2066-4a1d-9065-76960ac238ec"), LocalDateTime.of(2017, 5, 16, 12, 0), 1500.0d);
		acs.add(ac);
		acs.add(new Account(UUID.fromString("1f389976-2066-4a1d-9065-76960ac238ec"), LocalDateTime.of(2017, 5, 15, 12, 0), 0.0));
		Set<Customer> customers = new HashSet<>();
		Customer alain = new Customer(UUID.fromString("3f389976-2066-4a1d-9065-76960ac238ec"), "Alain", "Pame", 33, "20 rue de la paix", "+33 621222324");
		alain.getAccounts().add(ac);
		customers.add(alain);
		bankResource = new BankResource(new BankService(new AccountRepository(acs), new CustomerRepository(customers)));
	}
	
	@Test
	public void linkAccountAndCustomer() {
		Response response = bankResource.linkAccountToCustomer(new Link("3f389976-2066-4a1d-9065-76960ac238ec", "1f389976-2066-4a1d-9065-76960ac238ec"));
		assertNotNull(response);
		assertEquals(201, response.getStatus());
	}
	
	@Test
	public void deposit() {
		Response response = bankResource.accountTransaction(new Transaction("3f389976-2066-4a1d-9065-76960ac238ec", "2f389976-2066-4a1d-9065-76960ac238ec", 200.0d));
		assertNotNull(response);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void withdrawal() {
		Response response = bankResource.accountTransaction(new Transaction("3f389976-2066-4a1d-9065-76960ac238ec", "2f389976-2066-4a1d-9065-76960ac238ec", -200.0d));
		assertNotNull(response);
		assertEquals(200, response.getStatus());
	}
}
