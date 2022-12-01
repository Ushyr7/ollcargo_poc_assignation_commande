package com.ollcargo_poc1.assign.model;

import javax.persistence.Embeddable;

@Embeddable
public class DeliveryPoint {
	
	private String deliveryPointCity;
	
	private String deliveryPointComplement;
	
	private String deliveryPointCountry;
	
	private String clientFirstName;
	
	private String clientLastName;
	
	private String clientPhone;
	
	private String deliveryPointStreet;
	
	private String deliveryPointZipCode;
	
	private Geolocation deliveryPointGeolocation;

	public String getDeliveryPointCity() {
		return deliveryPointCity;
	}

	public void setDeliveryPointCity(String deliveryPointCity) {
		this.deliveryPointCity = deliveryPointCity;
	}

	public String getDeliveryPointComplement() {
		return deliveryPointComplement;
	}

	public void setDeliveryPointComplement(String deliveryPointComplement) {
		this.deliveryPointComplement = deliveryPointComplement;
	}

	public String getDeliveryPointCountry() {
		return deliveryPointCountry;
	}

	public void setDeliveryPointCountry(String deliveryPointCountry) {
		this.deliveryPointCountry = deliveryPointCountry;
	}

	public String getClientFirstName() {
		return clientFirstName;
	}

	public void setClientFirstName(String clientFirstName) {
		this.clientFirstName = clientFirstName;
	}

	public String getClientLastName() {
		return clientLastName;
	}

	public void setClientLastName(String clientLastName) {
		this.clientLastName = clientLastName;
	}

	public String getClientPhone() {
		return clientPhone;
	}

	public void setClientPhone(String clientPhone) {
		this.clientPhone = clientPhone;
	}

	public String getDeliveryPointStreet() {
		return deliveryPointStreet;
	}

	public void setDeliveryPointStreet(String deliveryPointStreet) {
		this.deliveryPointStreet = deliveryPointStreet;
	}

	public String getDeliveryPointZipCode() {
		return deliveryPointZipCode;
	}

	public void setDeliveryPointZipCode(String deliveryPointZipCode) {
		this.deliveryPointZipCode = deliveryPointZipCode;
	}

	public Geolocation getDeliveryPointGeolocation() {
		return deliveryPointGeolocation;
	}

	public void setDeliveryPointGeolocation(Geolocation deliveryPointGeolocation) {
		this.deliveryPointGeolocation = deliveryPointGeolocation;
	}



}
