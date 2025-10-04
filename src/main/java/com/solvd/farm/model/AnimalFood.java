package com.solvd.farm.model;


import com.sun.source.doctree.UnknownInlineTagTree;

public class AnimalFood extends Countable {

    private float productionRatePerDay;
    private Unit productionUnit;

    public AnimalFood() {
        super("New animal food", 0);
        this.productionRatePerDay = 0.5F;
        this.productionUnit = Unit.KILOGRAM;
    }

    public AnimalFood(String name, float quantity, float productionRatePerDay, Unit productionUnit) {
        super(name, quantity);
        this.productionRatePerDay = productionRatePerDay;
        this.productionUnit = productionUnit;
    }

    @Override
    public String toString() {
        return (this.getName() + " | quantity " + this.getQuantity() + " " + this.productionUnit + " | " + this.productionRatePerDay + " produce per day");
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof AnimalFood)) {
            return false;
        } else {
            return (((AnimalFood) object).getName().equals(this.getName()));
        }

    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    public void setProductionRatePerDay(Float Rate) {
        this.productionRatePerDay = Rate;
    }

    public void setProductionUnit(Unit unit) {
        this.productionUnit = unit;
    }

    public float getProductionRatePerDay() {
        return this.productionRatePerDay;
    }

    public Unit getProductionUnit() {
        return this.productionUnit;
    }

    public void produce(int days) {
        this.addQuantity(productionRatePerDay * days);
    }

}