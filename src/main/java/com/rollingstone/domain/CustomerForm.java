package com.rollingstone.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.SafeHtml;

public class CustomerForm {

	@NotNull
	@Size(min = 2, max = 50)
	@SafeHtml
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 50)
	@SafeHtml
	private String lastName;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	
	
}
