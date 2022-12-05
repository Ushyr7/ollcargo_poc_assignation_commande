package com.ollcargo_poc1.assign.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class Geolocation {
	
	@JsonProperty("coordinates")
	@ElementCollection
	private List<Double> coordinates;

	public List<Double> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Double> coordinates) {
		this.coordinates = coordinates;
	}


	public Geolocation(String type, List<Double> coordinates) {
		this.coordinates = coordinates;
	}

	public Geolocation() {}
	
	

}
