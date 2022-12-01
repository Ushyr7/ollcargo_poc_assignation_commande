package com.ollcargo_poc1.assign.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "Order", schema="public")
public class Order {

	
	@Id
    private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	public Order() {
	}
	
    public Order(Order o) {
    	this.id = o.getId();
    	this.creationDate = o.getCreationDate();

    }
    
    public void setOrder(Order o) {
    	this.id = o.getId();
    }
    
    public String getId() {
        return id;
    }
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "Order [id=" + id +" creationDate=" + creationDate +"]";
	}
	
	
	/*private Parts parts;
	
	private User user;
	


    public Order(Order o) {
    	this.creationDate = o.getCreationDate();
    	this.user = o.getUser();
    	this.parts = o.getParts();
    }

	public Parts getParts() {
		return parts;
	}

	public void setParts(Parts parts) {
		this.parts = parts;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	*/
}