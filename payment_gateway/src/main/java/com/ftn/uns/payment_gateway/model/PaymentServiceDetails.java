package com.ftn.uns.payment_gateway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DETAILS")
public class PaymentServiceDetails {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "merchant")
	private String merchantID;

	@Column(name = "password")
	private String merchantPassword;

	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private PaymentType type;

	public PaymentServiceDetails() {
	}

	public PaymentServiceDetails(Long id, String merchantID, String merchantPassword, PaymentType type) {
		this.id = id;
		this.merchantID = merchantID;
		this.merchantPassword = merchantPassword;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}
}
