package com.ollcargo_poc1.assign.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class DeliveryPoint {
	
	@Column(name="DeliveryPointCity")
	@JsonProperty("city")
	private String city;
	
	@Column(name="DeliveryPointComplement")
	@JsonProperty("complement")
	private String complement;
	
	@Column(name="DeliveryPointCountry")
	@JsonProperty("country")
	private String country;
	
	@Column(name="DeliveryPointStreet")
	@JsonProperty("street")
	private String street;
	
	@Column(name="DeliveryPointZipCode")
	@JsonProperty("zipCode")
	private String zipCode;
	
	@JsonProperty("geolocation")
	private Geolocation geolocation;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
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

	public DeliveryPoint(String city, String complement, String country, String street, String zipCode,
			Geolocation geolocation) {
		this.city = city;
		this.complement = complement;
		this.country = country;
		this.street = street;
		this.zipCode = zipCode;
		this.geolocation = geolocation;
	}

	public DeliveryPoint() {}
	

	
}
