package com.ollcargo_poc1.assign.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class User {

	@Column(name="UserId")
	@JsonProperty("id")
	private String id;
	
	@Column(name="UserPhone")
	@JsonProperty("phone")
	private String phone;
	
	@Column(name="UserFirstName")
	@JsonProperty("firstName")
	private String firstName;
	
	@Column(name="UserLastName")
	@JsonProperty("lastName")
	private String lastName;
	
	@Column(name="UserEmail")
	@JsonProperty("email")
	private String email;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User(String id, String phone, String firstName, String lastName, String email) {
		super();
		this.id = id;
		this.phone = phone;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public User() {}
	
	
	
}
