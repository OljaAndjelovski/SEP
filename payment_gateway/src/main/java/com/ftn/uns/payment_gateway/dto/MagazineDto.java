package com.ftn.uns.payment_gateway.dto;

import java.util.ArrayList;

import com.ftn.uns.payment_gateway.model.Magazine;

public class MagazineDto {
	private String issn;
	private String title;
	private Double membership;
	private String merchantId;
	private String merchantPassword;
	private ArrayList<Integer> typesCode = new ArrayList<Integer>();

	public Magazine createMagazine(MagazineDto magazineDto) {
		Magazine magazine = new Magazine();
		magazine.setIssn(magazineDto.getIssn());
		magazine.setTitle(magazineDto.getTitle());
		magazine.setMembership(magazineDto.getMembership());
		magazine.setMerchantId(magazineDto.getMerchantId());
		magazine.setMerchantPassword(magazineDto.getMerchantPassword());
		return magazine;
	}

	public MagazineDto() {
		super();
	}

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

	public ArrayList<Integer> getTypesCode() {
		return typesCode;
	}

	public void setTypesCode(ArrayList<Integer> typesCode) {
		this.typesCode = typesCode;
	}

}
