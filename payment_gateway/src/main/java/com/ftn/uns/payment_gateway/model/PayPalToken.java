package com.ftn.uns.payment_gateway.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class PayPalToken {

    @Id
    private String issn;

    @MapsId
    @OneToOne
    Magazine magazine;

    @Column
    String token;

    @Column
    LocalDateTime validUntil;

    public PayPalToken() {
    }

    public PayPalToken(String issn, Magazine magazine, String token, LocalDateTime validUntil) {
        this.issn = issn;
        this.magazine = magazine;
        this.token = token;
        this.validUntil = validUntil;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}
