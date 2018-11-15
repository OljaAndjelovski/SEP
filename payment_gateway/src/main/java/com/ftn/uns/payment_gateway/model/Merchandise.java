package com.ftn.uns.payment_gateway.model;

public class Merchandise {

    private String name;
    private String description;
    private Integer quantity;
    private Double price;
    private String currency;
    private String merchantId;

    public Merchandise() {}

    public Merchandise(String name, String description, Integer quantity, Double price, String currency, String merchantId) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.currency = currency;
        this.merchantId = merchantId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
