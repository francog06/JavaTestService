package com.technicaltest.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@XmlRootElement
public class Customer {

	public Customer() {
	}

	public Customer(UUID id, String firstName, String lastName, Integer age, String address, String phoneNumber) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.accounts = new HashSet<>();
	}

	@NotNull
	private UUID id;
	
	@NotNull
	@NotEmpty
	private String firstName;
	
	@NotNull
	@NotEmpty
	private String lastName;
	
	@NotNull
	@Min(value = 0)
	private Integer age;
	
	@NotNull
	@NotEmpty
	private String address;
	
	@NotNull
	@NotEmpty
	private String phoneNumber;
	
	private Set<Account> accounts;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastname) {
		this.lastName = lastname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
}
