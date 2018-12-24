package com.ftn.uns.payment_gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Session implements Serializable {

	private String username;
	private String issn;
	private Double price;
	private String currency;
	private String merchandise;
	private LocalDateTime timestamp;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Session() {
	}

	public Session(String username, String issn, Double price, String currency, String merchandise, LocalDateTime timestamp, Long id) {
		this.username = username;
		this.issn = issn;
		this.price = price;
		this.currency = currency;
		this.merchandise = merchandise;
		this.timestamp = timestamp;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getMerchandise() {
		return merchandise;
	}

	public void setMerchandise(String merchandise) {
		this.merchandise = merchandise;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
