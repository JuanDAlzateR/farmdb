package com.solvd.farm.model;

public abstract class Countable {

    private float quantity;
    private String name;
    private int farmId;

    public Countable(String name, float quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(float quantity) {
        this.quantity += quantity;
    }

    public float getQuantity() {
        return this.quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getFarmId() {
        return farmId;
    }

    public void setFarmId(int farmId) {
        this.farmId = farmId;
    }

    public void setCountable(String name, float quantity, int farmId) {
        this.name = name;
        this.quantity = quantity;
        this.farmId =farmId;
    }
}