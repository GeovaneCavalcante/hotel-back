package com.hoteis.apirest.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "reservations")
public class Reserve implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private BigDecimal value;

	private Date startDateAvailable;

	private Date endDateAvailable;

	@ManyToOne(targetEntity = Hotel.class)
	private Hotel hotel;

	@JsonProperty("hotel_id")
	private void unpackNested(Integer hotel_id) {
		this.hotel = new Hotel();
		hotel.setHotel_id(hotel_id);
	}

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@JsonProperty("user_id")
	private void unpackNestede(Integer user_id) {
		this.user = new User();
		user.setUser_id(user_id);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Date getStartDateAvailable() {
		return startDateAvailable;
	}

	public void setStartDateAvailable(Date startDateAvailable) {
		this.startDateAvailable = startDateAvailable;
	}

	public Date getEndDateAvailable() {
		return endDateAvailable;
	}

	public void setEndDateAvailable(Date endDateAvailable) {
		this.endDateAvailable = endDateAvailable;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}

}
