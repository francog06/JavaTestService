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

import com.technicaltest.model.Account;
import com.technicaltest.services.AccountService;

@Path("/accounts")
public class AccountResource {

	public AccountResource() {
	}
	
	public AccountResource(AccountService service) {
		this.accountService = service;
	}
	
	@Inject
	private AccountService accountService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAccount(Account ac) {
		return accountService.createAccount(ac);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAccount(Account ac) {
		return accountService.updateAccount(ac);
	}
	
	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccount(@PathParam("id")String id) {
		return accountService.getAccount(id);
	}
	
	@Path("/{id}")
	@DELETE
	public Response deleteAccount(@PathParam("id")String id) {
		return accountService.deleteAccount(id);
	}
}
