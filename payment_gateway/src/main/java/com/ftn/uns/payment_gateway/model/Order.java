package com.ftn.uns.payment_gateway.model;

import java.time.LocalDateTime;
import java.util.stream.StreamSupport;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTION")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long merchantOrderId;

    @Column(name = "TIMESTAMP")
    private LocalDateTime merchantTimestamp;

    private String payerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MAGAZINE")
    private Magazine magazine;

    private Integer quantity;

    private Double price;

    @Column(name = "CURRENCY", length = 3)
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE")
    private PaymentType type;

    @Column(name = "BUYER_NAME")
    private String buyerFirstName;

    @Column(name = "BUYER_SURNAME")
    private String buyerLastName;

    @Column(name = "BUYER_EMAIL")
    private String buyerEmail;

    private String merchandise;

    public Order() {
        super();
    }

    public Order(LocalDateTime merchantTimestamp, String payerId, Magazine magazine, Integer quantity, Double price,
                 String currency, PaymentType type, String buyerFirstName, String buyerLastName, String buyerEmail, String merchandise) {
        this.merchantTimestamp = merchantTimestamp;
        this.payerId = payerId;
        this.magazine = magazine;
        this.quantity = quantity;
        this.price = price;
        this.currency = currency;
        this.type = type;
        this.buyerFirstName = buyerFirstName;
        this.buyerLastName = buyerLastName;
        this.buyerEmail = buyerEmail;
        this.merchandise = merchandise;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }

    public Double getAmount() {
        return price;
    }

    public void setAmount(Double price) {
        this.price = price;
    }

    public Long getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(Long merchantOrderId) {
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

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }

    public String getBuyerEmail() {
        return buyerEmail;
    }

    public void setBuyerEmail(String buyerEmail) {
        this.buyerEmail = buyerEmail;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(String merchandise) {
        this.merchandise = merchandise;
    }
}
