package com.technicaltest.services;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

import com.technicaltest.model.Account;
import com.technicaltest.repository.AccountRepository;

@Stateless
public class AccountService {

	public AccountService() {
	}
	
	public AccountService(AccountRepository repo) {
		this.accountRepository = repo;
	}
	
	@Inject
	private AccountRepository accountRepository;
	
	public Response createAccount(Account ac) {
		accountRepository.create(ac);
		return Response.created(null).build();
	}

	public Response updateAccount(Account ac) {
		if (accountRepository.update(ac) == true) {
			return Response.ok().build();
		}
		return Response.serverError().build();
	}

	public Response deleteAccount(String id) {
		if (accountRepository.delete(UUID.fromString(id)) == true) {
			return Response.ok().build();
		}
		return Response.serverError().build();
	}

	public Response getAccount(String id) {
		Account ac = accountRepository.get(UUID.fromString(id));
		if (ac != null) {
			return Response.ok(ac).build();
		}
		return Response.noContent().build();
	}
}
