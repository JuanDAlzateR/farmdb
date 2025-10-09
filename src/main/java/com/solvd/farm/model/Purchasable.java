package com.solvd.farm.model;

public abstract class Purchasable extends Countable {

    private int purchasableId;
    private float pricePerUnit;
    private Currency currency = Currency.USD;

    public Purchasable() {
        super("New name", 0);
        this.pricePerUnit = 0;
    }

    public Purchasable(String name, float quantity) {
        super(name, quantity);
        this.pricePerUnit = 0;
    }

    public Purchasable(String name, float quantity, float price) {
        super(name, quantity);
        this.pricePerUnit = price;
    }

    public void setPurchasableId(int purchasableId) {
        this.purchasableId = purchasableId;
    }

    public void setPrice(float price) {
        this.pricePerUnit = price;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getPurchasableId() {
        return purchasableId;
    }

    public float getPrice() {
        return this.pricePerUnit;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setPurchasable(float pricePerUnit, String currencyAbbreviation) {
        this.pricePerUnit = pricePerUnit;
        this.currency = Currency.fromAbbreviation(currencyAbbreviation);
    }

    public void setPurchasable(int id, float pricePerUnit, String currencyAbbreviation) {
        this.purchasableId = id;
        this.pricePerUnit = pricePerUnit;
        this.currency = Currency.fromAbbreviation(currencyAbbreviation);
    }

}