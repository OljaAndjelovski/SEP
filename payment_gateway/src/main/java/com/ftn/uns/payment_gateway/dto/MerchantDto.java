package com.ftn.uns.payment_gateway.dto;

import java.time.LocalDateTime;

public class MerchantDto {

	private String merchantId;
	private String merchantPassword;
	private Double amount;
	private Integer merchantOrderID;
	private LocalDateTime merchantTimestamp;
	private String cancelUrl;
	private String successlUrl;
	private String failureUrl;

	public MerchantDto() {
		super();
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantPassword() {
		return merchantPassword;
	}

	public void setMerchantPassword(String merchantPassword) {
		this.merchantPassword = merchantPassword;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Integer getMerchantOrderID() {
		return merchantOrderID;
	}

	public void setMerchantOrderID(Integer merchantOrderID) {
		this.merchantOrderID = merchantOrderID;
	}

	public LocalDateTime getMerchantTimestamp() {
		return merchantTimestamp;
	}

	public void setMerchantTimestamp(LocalDateTime merchantTimestamp) {
		this.merchantTimestamp = merchantTimestamp;
	}

	public String getCancelUrl() {
		return cancelUrl;
	}

	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	public String getSuccesslUrl() {
		return successlUrl;
	}

	public void setSuccesslUrl(String successlUrl) {
		this.successlUrl = successlUrl;
	}

	public String getFailureUrl() {
		return failureUrl;
	}

	public void setFailureUrl(String failureUrl) {
		this.failureUrl = failureUrl;
	}

	public MerchantDto(String merchantId, String merchantPassword, Double amount, Integer merchantOrderID,
			LocalDateTime merchantTimestamp, String cancelUrl, String successlUrl, String failureUrl) {
		super();
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.amount = amount;
		this.merchantOrderID = merchantOrderID;
		this.merchantTimestamp = merchantTimestamp;
		this.cancelUrl = cancelUrl;
		this.successlUrl = successlUrl;
		this.failureUrl = failureUrl;
	}

}
