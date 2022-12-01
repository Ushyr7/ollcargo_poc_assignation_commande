package com.ollcargo_poc1.assign.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Geolocation {

	@Column(insertable = false, updatable = false)
	private String type;
	
	private Coordinates coordinates;

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
