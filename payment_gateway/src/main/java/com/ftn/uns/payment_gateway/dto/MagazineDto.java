package com.ftn.uns.payment_gateway.dto;

import java.util.HashSet;
import java.util.Set;

import com.ftn.uns.payment_gateway.model.PaymentServiceDetails;

public class MagazineDto {
    private String issn;
    private String title;
    private Set<PaymentServiceDetails> details = new HashSet<>();

    public MagazineDto() {
    }

    public MagazineDto(String issn, String title, Set<PaymentServiceDetails> details) {
        this.issn = issn;
        this.title = title;
        this.details = details;
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

    public Set<PaymentServiceDetails> getDetails() {
        return details;
    }

    public void setDetails(Set<PaymentServiceDetails> details) {
        this.details = details;
    }
}
