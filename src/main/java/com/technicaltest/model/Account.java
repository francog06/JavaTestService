package com.technicaltest.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Account {

	public Account() {
	}
	
	public Account(UUID id, LocalDateTime date, Double balance) {
		this.id = id;
		this.created = date;
		this.updated = date;
		this.balance = balance;
		this.customer = null;
	}
	
	@NotNull
	private UUID id;
	
	@NotNull
	private Customer customer;
	
	@NotNull
	private LocalDateTime created;
	
	@NotNull
	private LocalDateTime updated;
	
	@NotNull
	private Double balance;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getUpdated() {
		return updated;
	}
	public void setUpdated(LocalDateTime updated) {
		this.updated = updated;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
	    if (other == this)
	    	return true;
	    if (!(other instanceof Account))
	    	return false;
	    Account o = (Account) other;
	    if (o.getId().compareTo(this.getId()) != 0)
	    	return false;
	    if (!o.getCreated().equals(this.getCreated()))
	    	return false;
	    if (!o.getUpdated().equals(this.getUpdated()))
	    	return false;
	    if (!o.getBalance().equals(this.getBalance()))
	    	return false;
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
        hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
	}
}
