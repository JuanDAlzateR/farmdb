package com.solvd.farm.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimalFeed extends Purchasable {

    public static final Logger LOGGER = LogManager.getLogger(AnimalFeed.class);
    private float consumptionRatePerDay;
    private Unit feedUnit;

    public AnimalFeed() {
        super("New animal feed", 0);
        this.consumptionRatePerDay = 0.5F;
        this.feedUnit = Unit.KILOGRAM;
    }

    public AnimalFeed(String name, float quantity, float consumptionRatePerDay, Unit feedUnit) {
        super(name, quantity);
        this.consumptionRatePerDay = consumptionRatePerDay;
        this.feedUnit = feedUnit;
    }

    @Override
    public String toString() {
        String line = (this.getName() + " | quantity %.2f " + this.feedUnit + " | " + this.consumptionRatePerDay + " consume per day%n");
        return String.format(line, this.getQuantity());
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AnimalFeed)) {
            return false;
        } else {
            return (((AnimalFeed) object).getName().equals(this.getName()));
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    public void setConsumptionRatePerDay(Float Rate) {
        this.consumptionRatePerDay = Rate;
    }

    public void setFeedUnit(Unit unit) {
        this.feedUnit = unit;
    }

    public float getConsumptionRatePerDay() {
        return this.consumptionRatePerDay;
    }

    public Unit getFeedUnit() {
        return this.feedUnit;
    }

    public void consume(int days) {
        this.addQuantity(-consumptionRatePerDay * days);
        if (this.getQuantity() < 0) {
            LOGGER.info("Warning: Not enough " + getName());
            LOGGER.info(this);
            this.setQuantity(0);
        }
    }


}