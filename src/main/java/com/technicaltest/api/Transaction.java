package com.technicaltest.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {

	public Transaction() {
	}

	public Transaction(String customerId, String accountId, Double amount) {
		this.customerId = customerId;
		this.accountId = accountId;
		this.amount = amount;
	}
	
	private String customerId;

	private String accountId;

	private Double amount;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double money) {
		this.amount = money;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
