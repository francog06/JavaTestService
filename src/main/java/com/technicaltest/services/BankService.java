package com.technicaltest.services;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.technicaltest.model.Account;
import com.technicaltest.model.Customer;
import com.technicaltest.repository.AccountRepository;
import com.technicaltest.repository.CustomerRepository;

@Stateless
public class BankService {

	public BankService() {
	}
	
	public BankService(AccountRepository acRepo, CustomerRepository custRepo) {
		this.accountRepository = acRepo;
		this.customerRepository = custRepo;
	}
	
	@Inject
	private AccountRepository accountRepository;
	
	@Inject
	private CustomerRepository customerRepository;

	public Response linkAccountToCustomer(String customerId, String accountId) {
		Account ac = accountRepository.get(UUID.fromString(accountId));
		Customer cust = customerRepository.get(UUID.fromString(customerId));
		if (ac != null && cust != null) {
			ac.setCustomer(cust);
			cust.getAccounts().add(ac);
			return Response.created(null).build();
		}
		return Response.serverError().build();
	}

	public Response accountTransaction(String customerId, String accountId, Double amount) {
		Customer cust = customerRepository.get(UUID.fromString(customerId));
		if (cust.getAccounts() != null) {
			for (Account ac: cust.getAccounts()) {
				if (ac.getId().compareTo(UUID.fromString(accountId)) == 0) {
					ac.setBalance(ac.getBalance() + amount);
					ac.setUpdated(LocalDateTime.now());
					return Response.ok().build();
				}
			}
		}
		return Response.serverError().build();
	}
}
