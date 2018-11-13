package com.ftn.uns.payment_gateway.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Magazine {

	
	private String issn;
	
	private String title;
	private Double membership;
	
	@Id
	private String merchantId;
	
	private String merchantPassword;

	@ManyToMany(fetch = FetchType.LAZY)
	private Set<PaymentType> types = new HashSet<>();
	
	public String getIssn() {
		return issn;
	}

	public void setIssn(String issn) {
		this.issn = issn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getMembership() {
		return membership;
	}

	public void setMembership(Double membership) {
		this.membership = membership;
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
	
	public Set<PaymentType> getTypes() {
		return types;
	}

	public void setTypes(Set<PaymentType> types) {
		this.types = types;
	}

	public Magazine(String issn, String title, Double membership, String merchantId, String merchantPassword, Set<PaymentType> types) {
		super();
		this.issn = issn;
		this.title = title;
		this.membership = membership;
		this.merchantId = merchantId;
		this.merchantPassword = merchantPassword;
		this.types = types;
	}

	public Magazine() {
		super();
	}

}
