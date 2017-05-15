package com.technicaltest.controller;

import javax.inject.Inject;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.technicaltest.api.Link;
import com.technicaltest.api.Transaction;
import com.technicaltest.services.BankService;

@Path("/bank")
public class BankResource {

	public BankResource() {
	}
	
	public BankResource(BankService service) {
		this.bankService = service;
	}
	
	@Inject
	private BankService bankService;
	
	@Path("/link")
	@PUT
	public Response linkAccountToCustomer(Link link) {
		return bankService.linkAccountToCustomer(link.getCustomerId(), link.getAccountId());
	}
	
	@Path("/transaction")
	@PUT
	public Response accountTransaction(Transaction transaction) {
		return bankService.accountTransaction(transaction.getCustomerId(), transaction.getAccountId(), transaction.getAmount());
	}
}