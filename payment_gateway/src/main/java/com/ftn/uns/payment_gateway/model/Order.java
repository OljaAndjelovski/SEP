package com.ftn.uns.payment_gateway.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION")
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    private Integer merchantOrderId;

    @Column(name = "TIMESTAMP")
    private LocalDateTime merchantTimestamp;


    private String payerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    private Double amount;

    public Order() {
        super();
    }

    public Order(Integer merchantOrderId, LocalDateTime merchantTimestamp, String payerId, Double amount,
                 Magazine magazine) {
        super();
        this.merchantOrderId = merchantOrderId;
        this.merchantTimestamp = merchantTimestamp;
        this.payerId = payerId;
        this.amount = amount;
        this.magazine = magazine;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(Integer merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public LocalDateTime getMerchantTimestamp() {
        return merchantTimestamp;
    }

    public void setMerchantTimestamp(LocalDateTime merchantTimestamp) {
        this.merchantTimestamp = merchantTimestamp;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }

}
