package com.ollcargo_poc1.assign.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

	
	public Order() {
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

	
}