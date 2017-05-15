package com.technicaltest.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.technicaltest.model.Customer;
import com.technicaltest.services.CustomerService;

@Path("/customers")
public class CustomerResource {

	public CustomerResource() {
		
	}
	
	public CustomerResource(CustomerService service) {
		customerService = service;
	}
	
	@Inject
	private CustomerService customerService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createCustomer(Customer newCustomer) {
		return customerService.createCustomer(newCustomer);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(Customer customer) {
		return customerService.updateCustomer(customer);
	}
	
	@Path("/{id}")
	@DELETE
	public Response deleteCustomer(@PathParam("id")String id) {
		return customerService.deleteCustomer(id);
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomer(@PathParam("id")String id) {
		return customerService.getCustomer(id);
	}
	
	@Path("/{id}/accounts")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAccountsFromCustomer(@PathParam("id") String customerId) {
		return customerService.getAllAccountsFromCustomer(customerId);
	}
	
	@Path("/{id}/wealth")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWealthFromCustomer(@PathParam("id") String customerId) {
		return customerService.getWealthFromCustomer(customerId);
	}
}