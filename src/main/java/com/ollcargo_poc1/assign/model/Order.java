package com.ollcargo_poc1.assign.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "Order", schema="public")
public class Order {

	
	@Id
	private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("creationDate")
	private Date creationDate;

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
	private List<Parts> parts;
	
    @Embedded
    @JsonProperty("user")
    private User user;
    
    @Column(name = "state")
    private String state;

	
	public Order() {
    	setState("EN ATTENTE D'ASSIGNATION");
	}
	
  
	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "[id=" + id + "creationDate=" + creationDate + "User=" + user.getId() + " " +
			user.getFirstName() + " " + user.getLastName() + " " + user.getPhone() + " " + user.getEmail() +"]";
	}
		

    public Order(Order o) {
    	this.id = o.getId();
    	this.creationDate = o.getCreationDate();
    	this.user = o.getUser();
    	this.parts = o.getParts();
    	setState("EN ATTENTE D'ASSIGNATION");
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Parts> getParts() {
		return parts;
	}


	public void setParts(List<Parts> parts) {
		this.parts = parts;
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}

	public int getWeight() {
		int weight = 0;
		for (int i = 0; i < parts.size(); i++) {
			weight += parts.get(i).getWeight();
		}
		return weight;
	}

	public boolean isFragile() {
		boolean fragile = false;
		for (int i = 0; i < parts.size(); i++) {
			fragile = fragile || parts.get(i).isFragile();
		}
		return fragile;
	}

	public List<CollectPoint> getCollectPoint() {
		List<CollectPoint> collectPoints = new ArrayList<CollectPoint>();
		for (int i = 0; i < parts.size(); i++) {
			collectPoints.add(parts.get(i).getCollectPoint());
		}
		return collectPoints;
	}

	public List<DeliveryPoint> getDeliveryPoints() {
		List<DeliveryPoint> deliveryPoints = new ArrayList<DeliveryPoint>();
		for (int i = 0; i < parts.size(); i++) {
			deliveryPoints.add(parts.get(i).getDeliveryPoint());
		}
		return deliveryPoints;
	}

	public List<Interval> getDeliveryTimes() {
		List<Interval> intervals = new ArrayList<Interval>();
		for (int i = 0; i < parts.size(); i++) {
			intervals.add(parts.get(i).getInterval());
		}
		return intervals;
	}
	
}