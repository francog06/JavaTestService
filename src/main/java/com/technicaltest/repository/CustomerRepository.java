package com.technicaltest.repository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.technicaltest.model.Account;
import com.technicaltest.model.Customer;

@ApplicationScoped
public class CustomerRepository {

	public CustomerRepository() {
		customers = new HashSet<>();
	}
	
	public CustomerRepository(Set<Customer> custs) {
		customers = custs;
	}
	
	private Set<Customer> customers;
	
	@PostConstruct
	public void createList() {
		customers = new HashSet<>();
	}
	
	public Boolean create(Customer newCustomer) {
		newCustomer.setId(UUID.randomUUID());
		newCustomer.setAccounts(new HashSet<>());
		customers.add(newCustomer);
		return true;
	}
	
	public Boolean update(Customer customer) {
		for(Customer cust: customers) {
			if (customer.getId().compareTo(cust.getId()) == 0) {
				cust.setFirstName(customer.getFirstName());
				cust.setLastName(customer.getLastName());
				cust.setAddress(customer.getAddress());
				cust.setPhoneNumber(customer.getPhoneNumber());
				cust.setAge(customer.getAge());
				return true;
			}
		}
		return false;
	}

	public Customer get(UUID id) {
		for (Customer cust: customers) {
			if (cust.getId().compareTo(id) == 0) {
				return cust;
			}
		}
		return null;
	}

	public Boolean delete(UUID id) {
		for (Customer cust: customers) {
			if (cust.getId().compareTo(id) == 0) {
				customers.remove(cust);
				return true;
			}
		}
		return false;
	}

	public Set<Account> getAllAccountsFromCustomer(UUID id) {
		Customer cust = get(id);
		if (cust != null) {
			if (cust.getAccounts() != null && !cust.getAccounts().isEmpty()) {
				Set<Account> acs = new HashSet<>();
				for (Account account : cust.getAccounts()) {
					Account ac = new Account();
					ac.setId(account.getId());
					ac.setCreated(account.getCreated());
					ac.setUpdated(account.getUpdated());
					ac.setBalance(account.getBalance());
					acs.add(ac);
				}
				return acs;
			}
		}
		return null;
	}

	public Double getWealthFromCustomer(UUID id) {
		Customer cust = get(id);
		Double balance = 0.0d;
		if (cust != null) {
			if (cust.getAccounts() != null && !cust.getAccounts().isEmpty()) {
				for (Account ac: cust.getAccounts()) {
					balance += ac.getBalance();
				}
			}
		}
		return balance;
	}
}
