package com.ollcargo_poc1.assign.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Coordinates {

	@Column(insertable = false, updatable = false)
	public double latitude;
	
	@Column(insertable = false, updatable = false)
	public double longitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
	
}
