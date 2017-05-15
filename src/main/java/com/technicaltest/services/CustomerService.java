package com.technicaltest.services;

import java.util.Set;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.technicaltest.api.Wealth;
import com.technicaltest.model.Account;
import com.technicaltest.model.Customer;
import com.technicaltest.repository.CustomerRepository;

@Stateless
public class CustomerService {

	public CustomerService() {
	}
	
	public CustomerService(CustomerRepository repo) {
		customerRepository = repo;
	}
	
	@Inject
	private CustomerRepository customerRepository;
	
	public Response createCustomer(Customer newCustomer) {
		customerRepository.create(newCustomer);
		return Response.created(null).build();
	}

	public Response updateCustomer(Customer customer) {
		if (customerRepository.update(customer) == true) {
			return Response.ok().build();
		}
		return Response.serverError().build();
	}

	public Response deleteCustomer(String id) {
		if (customerRepository.delete(UUID.fromString(id)) == true) {
			return Response.ok().build();
		}
		return Response.serverError().build();
	}

	public Response getCustomer(String id) {
		Customer cust = customerRepository.get(UUID.fromString(id));
		if (cust != null) {
			return Response.ok(cust).build();
		}
		return Response.noContent().build();
	}

	public Response getAllAccountsFromCustomer(String customerId) {
		Set<Account> acs = customerRepository.getAllAccountsFromCustomer(UUID.fromString(customerId));
		if (acs != null) {
			return Response.ok(acs).build();
		}
		return Response.noContent().build();
	}

	public Response getWealthFromCustomer(String customerId) {
		return Response.ok(new Wealth(customerRepository.getWealthFromCustomer(UUID.fromString(customerId)))).build();
	}
}