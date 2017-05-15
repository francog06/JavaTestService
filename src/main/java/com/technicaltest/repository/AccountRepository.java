package com.technicaltest.repository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.technicaltest.model.Account;

@ApplicationScoped
public class AccountRepository {

	public AccountRepository() {
		accounts = new HashSet<>();
	}
	
	public AccountRepository(Set<Account> ac) {
		accounts = ac;
	}
	
	private Set<Account> accounts;
	
	@PostConstruct
	public void createList() {
		accounts = new HashSet<>();
	}
	
	public Boolean create(Account ac) {
		ac.setId(UUID.randomUUID());
		accounts.add(ac);
		return true;
	}
	
	public Boolean update(Account ac) {
		for (Account account: accounts) {
			if (account.getId().compareTo(ac.getId()) == 0) {
				account.setUpdated(LocalDateTime.now());
				account.setCustomer(ac.getCustomer());
				account.setBalance(ac.getBalance());
				return true;
			}
		}
		return false;
	}

	public Boolean delete(UUID accountId) {
		for (Account account: accounts) {
			if (account.getId().compareTo(accountId) == 0) {
				accounts.remove(account);
				return true;
			}
		}
		return false;
	}
	
	public Account get(UUID accountId) {
		for (Account account: accounts) {
			if (account.getId().compareTo(accountId) == 0) {
				return account;
			}
		}
		return null;
	}
}
