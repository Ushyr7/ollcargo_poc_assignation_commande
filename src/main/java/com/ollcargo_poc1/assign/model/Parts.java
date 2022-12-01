package com.ollcargo_poc1.assign.model;

import javax.persistence.Embeddable;

@Embeddable
public class Parts {

	private CollectPoint collectPoint;

	private boolean fragile;
	
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
	
	
}
