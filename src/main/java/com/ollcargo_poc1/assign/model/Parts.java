package com.ollcargo_poc1.assign.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Parts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
	private CollectPoint collectPoint;

	@JsonProperty("fragile")
	private boolean fragile;
	
	@JsonProperty("weight")
	private int weight;
	
    @OneToOne(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
	private DeliveryPoint deliveryPoint;	

	public CollectPoint getCollectPoint() {
		return collectPoint;
	}

	public void setCollectPoint(CollectPoint collectPoint) {
		this.collectPoint = collectPoint;
	}

	public DeliveryPoint getDeliveryPoint() {
		return deliveryPoint;
	}

	public void setDeliveryPoint(DeliveryPoint deliveryPoint) {
		this.deliveryPoint = deliveryPoint;
	}

	public boolean isFragile() {
		return fragile;
	}

	public void setFragile(boolean fragile) {
		this.fragile = fragile;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Parts(boolean fragile, int weight) {
		this.fragile = fragile;
		this.weight = weight;
	}

	public Parts() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
