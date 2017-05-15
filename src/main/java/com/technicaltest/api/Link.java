package com.technicaltest.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Link {

	public Link() {
	}
	
	public Link(String customerId, String accountId) {
		this.customerId = customerId;
		this.accountId = accountId;
	}
	
	private String customerId;
	
	private String accountId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
}
