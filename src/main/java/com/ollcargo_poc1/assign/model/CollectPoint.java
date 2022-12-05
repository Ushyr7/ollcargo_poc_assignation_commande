package com.ollcargo_poc1.assign.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class CollectPoint {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="CollectPointCity")
	@JsonProperty("city")
	private String city;

	@Column(name="CollectPointCountry")
	@JsonProperty("country")
	private String country;

	@JsonProperty("geolocation")
	private Geolocation geolocation;

	@Column(name="CollectPointStreet")
	@JsonProperty("street")
	private String street;

	@Column(name="CollectPointZipCode")
	@JsonProperty("zipCode")
	private String zipCode;

	@JsonProperty("companyName")	
	private String companyName;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Geolocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CollectPoint(String city, String country, Geolocation geolocation, String street, String zipCode,
			String companyName) {
		this.city = city;
		this.country = country;
		this.geolocation = geolocation;
		this.street = street;
		this.zipCode = zipCode;
		this.companyName = companyName;
	}

	public CollectPoint() {}

	
	
}
