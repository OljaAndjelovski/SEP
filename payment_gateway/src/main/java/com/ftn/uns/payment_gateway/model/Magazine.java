package com.ftn.uns.payment_gateway.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Magazine {

    @Id
    @Column(length = 8, nullable = false, unique = true)
    private String issn;

    private String title;

    @OneToMany
    @JoinColumn(name = "MAGAZINE")
    private Set<PaymentServiceDetails> details = new HashSet<>();

    public Magazine() {
    }

    public Magazine(String issn, String title, Set<PaymentServiceDetails> details) {
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
