package com.ftn.uns.payment_gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Session implements Serializable {

	private String username;

	//
	private String issn;
	private Double price;
	private String currency;
	private String merchandise;
	private String description;
	private LocalDateTime timestamp;
	private String buyerSurname;
	private String buyerName;
	private String buyerEmail;
	private String payerId;
	private String type;
	private String productId;
	private Integer quantity;
	//

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Session() {
	}

	public Session(String username, String issn, Double price, String currency, String merchandise,
			LocalDateTime timestamp, Long id) {
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

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public String getProductId() {
		return productId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBuyerSurname() {
		return buyerSurname;
	}

	public void setBuyerSurname(String buyerSurname) {
		this.buyerSurname = buyerSurname;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
