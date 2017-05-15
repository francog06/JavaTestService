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

import com.technicaltest.controller.AccountResource;
import com.technicaltest.model.Account;
import com.technicaltest.repository.AccountRepository;
import com.technicaltest.services.AccountService;

public class AccountResourceTest {

	private AccountResource accountResource;
	
	@Before
	public void setup() {
		Set<Account> acs = new HashSet<>();
		acs.add(new Account(UUID.fromString("3f389976-2066-4a1d-9065-76960ac238ec"), LocalDateTime.of(2017, 5, 15, 12, 0), 0.0));
		accountResource = new AccountResource(new AccountService(new AccountRepository(acs)));
	}

	@Test
	public void createAccount() {
		Account ac = new Account(null, LocalDateTime.now(), 1000.0);
		Response response = accountResource.createAccount(ac);
		assertNotNull(response);
		assertEquals(201, response.getStatus());
	}

	@Test
	public void updateSuccessAccount() {
		Account ac = new Account(UUID.fromString("3f389976-2066-4a1d-9065-76960ac238ec"), null, 2000.0);
		Response response = accountResource.updateAccount(ac);
		assertNotNull(response);
		assertEquals(200, response.getStatus());
	}

	@Test
	public void updateFailAccount() {
		Account ac = new Account(UUID.fromString("3f389976-2066-4a1d-9065-79960ac938ec"), null, 2000.0);
		Response response = accountResource.updateAccount(ac);
		assertNotNull(response);
		assertEquals(500, response.getStatus());
	}
	
	@Test
	public void getSuccessAccount() {
		Response response = accountResource.getAccount("3f389976-2066-4a1d-9065-76960ac238ec");
		assertNotNull(response);
		assertEquals(200, response.getStatus());
		Account ac = (Account) response.getEntity();
		assertEquals("3f389976-2066-4a1d-9065-76960ac238ec", ac.getId().toString());
		assertEquals(LocalDateTime.of(2017, 5, 15, 12, 0), ac.getCreated());
		assertEquals(0.0d, ac.getBalance(), 0.0d);
	}
	
	@Test
	public void getFailAccount() {
		Response response = accountResource.getAccount("3f389976-2066-4a1d-9065-79960ac938ec");
		assertNotNull(response);
		assertEquals(204, response.getStatus());
	}
	
	@Test
	public void deleteSuccessAccount() {
		Response response = accountResource.deleteAccount("3f389976-2066-4a1d-9065-76960ac238ec");
		assertNotNull(response);
		assertEquals(200, response.getStatus());
	}
	
	@Test
	public void deleteFailAccount() {
		Response response = accountResource.deleteAccount("3f389976-2066-4a1d-9065-79960ac298ec");
		assertNotNull(response);
		assertEquals(500, response.getStatus());
	}
}
