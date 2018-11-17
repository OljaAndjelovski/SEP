package com.ftn.uns.payment_gateway.dto;

public class TransactionDto {

	private String product;
	private double price;
	private Integer paymentTypeCode;

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

	public Integer getPaymentTypeCode() {
		return paymentTypeCode;
	}

	public void setPaymentTypeCode(Integer paymentTypeCode) {
		this.paymentTypeCode = paymentTypeCode;
	}

}
