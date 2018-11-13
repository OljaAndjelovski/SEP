package com.ftn.uns.payment_gateway.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PaymentType {

	@Id
	private Integer code;
	
	private String name;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PaymentType() {
		super();
	}

	public PaymentType(Integer code, String name) {
		super();
		this.code = code;
		this.name = name;
	}
	
}
