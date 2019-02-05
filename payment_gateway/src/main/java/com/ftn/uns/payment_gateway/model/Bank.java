package com.ftn.uns.payment_gateway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "BANK")
@Entity
public class Bank {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "MERCHANT_ID")
	private String merchantId;

	@Column(name = "BANK_URL")
	private String bankUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getBank_url() {
		return bankUrl;
	}

	public void setBank_url(String bank_url) {
		this.bankUrl = bank_url;
	}

	public Bank(Integer id, String merchantId, String bank_url) {
		super();
		this.id = id;
		this.merchantId = merchantId;
		this.bankUrl = bank_url;
	}

	public Bank() {
		super();
	}

}
