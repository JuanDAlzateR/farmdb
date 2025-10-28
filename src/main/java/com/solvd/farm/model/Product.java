package com.solvd.farm.model;

public class Product extends Purchasable {
    private int productId;
    private float rottenPercentage;
    private float rotPerDay;
    private float sellPrice;

    public Product() {
        super("New Product", 0);
        this.rottenPercentage = 0;
        this.rotPerDay = 5F;
    }

    public Product(Crop crop) {
        super(crop.getName(), crop.getExpectedQuantity());
        this.rottenPercentage = 0;
        this.rotPerDay = 2F;
        crop.setQuantity(0);
    }

    public Product(float sellPrice, float rottenPercentage, float rotPerDay) {
        this.rottenPercentage = rottenPercentage;
        this.rotPerDay = rotPerDay;
        this.sellPrice = sellPrice;
    }

    @Override
    public String toString() {
        return (this.getName() + " | quantity " + getQuantity() + " | " + this.rottenPercentage + "% of rot");
    }

    public void setRottenPercentage(Float Percentage) {
        this.rottenPercentage = Percentage;
    }
    public void setRotPerDay(Float rotPerDay) {
        this.rotPerDay = rotPerDay;
    }
    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }
    public void setProductId(int id) {
        this.productId = id;
    }

    public float getRottenPercentage() {
        return this.rottenPercentage;
    }
    public float getRottenPerDay() {
        return this.rotPerDay;
    }
    public float getSellPrice() {
        return this.sellPrice;
    }
    public int getProductId() {
        return productId;
    }

    //    @Override
    public void passTime(int days) {
        if (this.getQuantity() > 0) {
            float newRot = this.rottenPercentage + this.rotPerDay * days;
            if (newRot >= 100) {
                newRot = 100;
            }
            this.rottenPercentage = newRot;
        }
    }

//    @Override
//    public void addToFarm(Farm farm) {
//        farm.addProduct(this);
//    }

}