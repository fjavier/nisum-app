package com.demo.nisum.controller;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDto {
	
	@NotEmpty
	private String name;
	@NotEmpty
	@Email(regexp = ".+@.+\\..+")
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private List<PhoneDto> phones;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the phones
	 */
	public List<PhoneDto> getPhones() {
		return phones;
	}
	/**
	 * @param phones the phones to set
	 */
	public void setPhones(List<PhoneDto> phones) {
		this.phones = phones;
	}
	
	
}
