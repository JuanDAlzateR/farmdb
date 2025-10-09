package com.solvd.farm.model;

public class UnitOfMeasurement {
    private int unitsId;
    private String unit;
    private String abbreviation;

    public UnitOfMeasurement(int id, String unit, String abbreviation) {
        this.unitsId = id;
        this.unit = unit;
        this.abbreviation = abbreviation;
    }

    public int getUnitsId() {
        return unitsId;
    }

    public String getUnit() {
        return unit;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setUnitsId(int unitsId) {
        this.unitsId = unitsId;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return "id: " + unitsId + " | unit: " + unit + " | abbreviation: " + abbreviation;
    }
}
