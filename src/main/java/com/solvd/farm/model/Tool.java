package com.solvd.farm.model;

public class Tool extends Purchasable {

    private float wearAndTearPercentage;
    private float wearAndTearPerDay;

    public Tool() {
        super();
        this.wearAndTearPercentage = 0;
        this.wearAndTearPerDay = 0.1F;
    }

    public Tool(String name, float price) {
        super(name, 1, price);
        this.wearAndTearPercentage = 0;
        this.wearAndTearPerDay = 0.1F;
    }

    @Override
    public String toString() {
        return (this.getName() + " | " + this.wearAndTearPercentage + "% of wear and tear");
    }

    public void setRottenPercentage(Float Percentage) {
        this.wearAndTearPercentage = Percentage;
    }

    public void setWearAndTearPerDay(Float wearAndTearPerDay) {
        this.wearAndTearPerDay = wearAndTearPerDay;
    }

    public float getRottenPercentage() {
        return this.wearAndTearPercentage;
    }

    public float getRottenPerDay() {
        return this.wearAndTearPerDay;
    }

//    @Override
//    public void passTime(int days) {
//        float newPercentage = this.wearAndTearPercentage + this.wearAndTearPerDay * days;
//        if (newPercentage >= 100) {
//            newPercentage = 100;
//        }
//        this.wearAndTearPercentage = newPercentage;
//    }
//
//    @Override
//    public void addToFarm(Farm farm) {
//        farm.addTool(this);
//    }

}