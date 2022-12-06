package com.ollcargo_poc1.assign.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "delivery_person")
public class DeliveryPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    @Enumerated(EnumType.STRING)
    private DeliveryPersonType type;

    private String transportation;

    private double maxWeight;

    private boolean canDeliverFragile;

    private String email;

    @OneToMany(cascade = {CascadeType.ALL})
	private List<Zone> pickUpZones = new ArrayList<Zone>();

    private Geolocation shop;

    @OneToMany(cascade = {CascadeType.ALL})
	private List<Zone> dropOffZones = new ArrayList<Zone>();

    @OneToMany(cascade = {CascadeType.ALL})
	private List<Schedule> schedules = new ArrayList<Schedule>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public DeliveryPersonType getType() {
        return type;
    }

    public void setType(DeliveryPersonType type) {
        this.type = type;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Boolean isCanDeliverFragile() {
        return canDeliverFragile;
    }

    public void setCanDeliverFragile(Boolean canDeliverFragile) {
        this.canDeliverFragile = canDeliverFragile;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public List<Zone> getPickUpZones() {
        return pickUpZones;
    }
    
    public void setPickUpZones(List<Zone> pickUpZones) {
        this.pickUpZones = pickUpZones;
    }

    public Geolocation getShop() {
        return this.shop;
    }

    public void setShop(Geolocation shop) {
        this.shop = shop;
    }


    public List<Zone> getDropOffZones() {
		return dropOffZones;
	}
	
	public void setDropOffZones(List<Zone> dropOffZones) {
		this.dropOffZones = dropOffZones;
	}

    public List<Schedule> getSchedules() {
		return schedules;
	}
	
	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}
}