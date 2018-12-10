package com.ftn.uns.payment_gateway.dto;

import org.hibernate.validator.constraints.URL;

public class UrlAndIdDto {

	private Integer paymentId;
	private String paymentUrl;

	public UrlAndIdDto() {
		super();
	}

	public UrlAndIdDto(Integer paymentId, @URL String paymentUrl) {
		super();
		this.paymentId = paymentId;
		this.paymentUrl = paymentUrl;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

}
