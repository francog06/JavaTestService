package com.technicaltest.api;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Wealth {

	private Double balance;

	public Wealth() {
	}

	public Wealth(Double balance) {
		this.balance = balance;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}
}
