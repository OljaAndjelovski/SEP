package com.ftn.uns.payment_gateway.dto;

import java.time.LocalDateTime;

import com.ftn.uns.payment_gateway.model.PaymentType;

public class OrderDto {

	private String merchantOrderId;

	private LocalDateTime merchantTimestamp;

	private String payerId;

	private String merchantId;

	private Double amount;

	private PaymentType type;

	private String magazinId;

	private String currency;

	private String merchandise;

	private Integer quantity;

	public OrderDto() {
	}

	public OrderDto(String merchantOrderId, LocalDateTime merchantTimestamp, String payerId, String merchantId,
			Double amount, PaymentType type, String currency, String merchandise, Integer quantity) {
		this.merchantOrderId = merchantOrderId;
		this.merchantTimestamp = merchantTimestamp;
		this.payerId = payerId;
		this.merchantId = merchantId;
		this.amount = amount;
		this.type = type;
		this.currency = currency;
		this.merchandise = merchandise;
		this.quantity = quantity;
	}

	public String getMerchandise() {
		return merchandise;
	}

	public void setMerchandise(String merchandise) {
		this.merchandise = merchandise;
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

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public String getMagazinId() {
		return magazinId;
	}

	public void setMagazinId(String magazinId) {
		this.magazinId = magazinId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;

	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
