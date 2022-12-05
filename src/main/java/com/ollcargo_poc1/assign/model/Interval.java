package com.ollcargo_poc1.assign.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Interval")
public class Interval {
	
	private Integer id;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("start")
	@Column(name = "start")
	private Date start;
	
	@Temporal(TemporalType.TIMESTAMP)
	@JsonProperty("end")
	@Column(name = "end")
	private Date end;

	public Interval(Date start, Date end) {
		this.start = start;
		this.end = end;
	}
	
	public Interval() {
	}

	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
}
