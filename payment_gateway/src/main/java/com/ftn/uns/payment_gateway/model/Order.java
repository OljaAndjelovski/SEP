package com.ftn.uns.payment_gateway.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACTION")
public class Order {

	@Id
	@Column(name = "ORDER_ID")
	private String merchantOrderId;

	@Column(name = "TIMESTAMP")
	private LocalDateTime merchantTimestamp;

	private String payerId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAGAZINE")
	private Magazine magazine;

	private Integer quantity;

	private Double price;

	@Column(name = "CURRENCY", length = 3)
	private String currency;

	private PaymentType type;

	@Column(name = "BUYER_NAME")
	private String buyerFirstName;

	@Column(name = "BUYER_SURNAME")
	private String buyerLastName;

	@Column(name = "BUYER_EMAIL")
	private String buyerEmail;

	private String merchandise;

	private Boolean executed;

	
	// Dodala sam polje status jer mi to vraca btc - paid, pending... 
	private Integer idBitcoin;
	private String status;

	public Order() {
		super();
	}

	public Order(LocalDateTime merchantTimestamp, String payerId, Magazine magazine, Integer quantity, Double price,
			String currency, PaymentType type, String buyerFirstName, String buyerLastName, String buyerEmail,
			String merchandise, Boolean executed) {
		this.merchantTimestamp = merchantTimestamp;
		this.payerId = payerId;
		this.magazine = magazine;
		this.quantity = quantity;
		this.price = price;
		this.currency = currency;
		this.type = type;
		this.buyerFirstName = buyerFirstName;
		this.buyerLastName = buyerLastName;
		this.buyerEmail = buyerEmail;
		this.merchandise = merchandise;
		this.executed = executed;
	}

	@Override
	public String toString() {
		return "Order{" + "merchantOrderId=" + merchantOrderId + ", merchantTimestamp=" + merchantTimestamp
				+ ", payerId='" + payerId + '\'' + ", magazine=" + magazine + ", quantity=" + quantity + ", price="
				+ price + ", currency='" + currency + '\'' + ", type=" + type + ", buyerFirstName='" + buyerFirstName
				+ '\'' + ", buyerLastName='" + buyerLastName + '\'' + ", buyerEmail='" + buyerEmail + '\''
				+ ", merchandise='" + merchandise + '\'' + ", executed=" + executed + '}';
	}

	public String getMerchantOrderId() {
		return merchantOrderId;
	}

	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}

	public LocalDateTime getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(LocalDateTime merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public Magazine getMagazine() {
		return magazine;
	}

	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public String getBuyerFirstName() {
		return buyerFirstName;
	}

	public void setBuyerFirstName(String buyerFirstName) {
		this.buyerFirstName = buyerFirstName;
	}

	public String getBuyerLastName() {
		return buyerLastName;
	}

	public void setBuyerLastName(String buyerLastName) {
		this.buyerLastName = buyerLastName;
	}

	public String getBuyerEmail() {
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getMerchandise() {
		return merchandise;
	}

	public void setMerchandise(String merchandise) {
		this.merchandise = merchandise;
	}

	public Boolean getExecuted() {
		return executed;
	}

	public void setExecuted(Boolean executed) {
		this.executed = executed;
	}

	public Integer getIdBitcoin() {
		return idBitcoin;
	}

	public void setIdBitcoin(Integer idBitcoin) {
		this.idBitcoin = idBitcoin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
