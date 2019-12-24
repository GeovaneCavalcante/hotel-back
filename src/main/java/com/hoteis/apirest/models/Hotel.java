package com.hoteis.apirest.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hoteis")
public class Hotel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String title;

	private String description;

	private String img_url;

	private BigDecimal price;

	private Date startDateAvailable;
	
	private Date endDateAvailable;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public void setHotel_id(Integer hotel_id) {
		this.id = hotel_id;
		
	}

}
