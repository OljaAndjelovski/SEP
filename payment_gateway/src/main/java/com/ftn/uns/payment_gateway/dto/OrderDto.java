package com.ftn.uns.payment_gateway.dto;

import com.ftn.uns.payment_gateway.model.PaymentType;

import java.time.LocalDateTime;

public class OrderDto {

    private Long merchantOrderId;

    private LocalDateTime merchantTimestamp;

    private String payerId;

    private String merchantId;

    private Double amount;

    private PaymentType type;

    public OrderDto() {
    }

    public OrderDto(Long merchantOrderId, LocalDateTime merchantTimestamp, String payerId, String merchantId, Double amount, PaymentType type) {
        this.merchantOrderId = merchantOrderId;
        this.merchantTimestamp = merchantTimestamp;
        this.payerId = payerId;
        this.merchantId = merchantId;
        this.amount = amount;
        this.type = type;
    }

    public Long getMerchantOrderId() {
        return merchantOrderId;
    }

    public void setMerchantOrderId(Long merchantOrderId) {
        this.merchantOrderId = merchantOrderId;
    }

    public LocalDateTime getMerchantTimestamp() {
        return merchantTimestamp;
    }

    public void setMerchantTimestamp(LocalDateTime merchantTimestamp) {
        this.merchantTimestamp = merchantTimestamp;
    }

    public String getPayerId() {
        return payerId;
    }

    public void setPayerId(String payerId) {
        this.payerId = payerId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentType getType() {
        return type;
    }

    public void setType(PaymentType type) {
        this.type = type;
    }
}
