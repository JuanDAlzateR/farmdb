package com.solvd.farm.model;

public abstract class Purchasable extends Countable {

    private float pricePerUnit;
    private Currency currency= Currency.USD;

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

    public void setPrice(float price) {
        this.pricePerUnit = price;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public float getPrice() {
        return this.pricePerUnit;
    }

    public Currency getCurrency() {
        return this.currency;
    }
}