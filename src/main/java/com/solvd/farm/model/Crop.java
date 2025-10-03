package com.solvd.farm.model;

public class Crop extends Countable {

    private float germinationRate;
    private float growthPercentage;
    private float growthPerDay;
    private CropState cropState;

    public Crop() {
        super("new crop", 0);
        this.germinationRate = 0.5F;
        this.growthPercentage = 0;
        this.growthPerDay = 1F;
        this.cropState = CropState.PLANTED;
    }

    public Crop(Grain grain) {
        super(grain.getName(), grain.getQuantity());
        this.germinationRate = 0.5F;
        this.growthPercentage = 0;
        this.growthPerDay = 1F;
        grain.setQuantity(0);
        this.cropState = CropState.PLANTED;
    }

    @Override
    public String toString() {
        return (this.getName() + " | expected quantity " + getExpectedQuantity() + " | " + this.growthPercentage + "% of growth" + " | " + this.cropState);
    }

    public void setGerminationRate(Float Rate) {
        this.germinationRate = Rate;
    }

    public void setGrowthPercentage(Float Percentage) {
        this.growthPercentage = Percentage;
    }

    public void setGrowthPerDay(Float growthPerDay) {
        this.growthPerDay = growthPerDay;
    }

    public void setCropState(CropState cropState) {
        this.cropState = cropState;
    }

    public float getGerminationRate() {
        return this.germinationRate;
    }

    public float getGrowthPercentage() {
        return this.growthPercentage;
    }

    public float getGrowthPerDay() {
        return this.growthPerDay;
    }

    public int getExpectedQuantity() {
        return (int) (this.getQuantity() * this.germinationRate);
    }

    public CropState getCropState() {
        return cropState;
    }

    public void advanceState() {
        this.cropState = this.cropState.next();
    }

    @Override
    public void passTime(int days) {
        if (this.getQuantity() > 0) {
            float newGrowth = this.growthPercentage + this.growthPerDay * days;
            if (newGrowth < 20) {
                setCropState(CropState.PLANTED);
            } else if (newGrowth < 100) {
                setCropState(CropState.GROWING);
            } else {
                newGrowth = 100;
                setCropState(CropState.READY_TO_HARVEST);
            }
            this.growthPercentage = newGrowth;
        }
    }

    public void addToFarm(Farm farm) {
        farm.addCrop(this);
    }
}