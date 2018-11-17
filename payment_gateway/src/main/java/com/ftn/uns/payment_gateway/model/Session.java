package com.ftn.uns.payment_gateway.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Session {
	@Id
	@GeneratedValue
	@Column(name = "SESSION_ID")
	private Long sessionId;

	private LocalDateTime timestamp;
	private String product;
	private double price;
	@Column(length = 8)
	private String issn;

	public Session() {
		super();
	}

	public Session(Long sessionId, LocalDateTime timestamp, String product, double price, String issn) {
		super();
		this.sessionId = sessionId;
		this.timestamp = timestamp;
		this.product = product;
		this.price = price;
		this.issn = issn;
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

}
