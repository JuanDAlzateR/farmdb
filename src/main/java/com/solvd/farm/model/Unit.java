package com.solvd.farm.model;

public enum Unit {
    KILOGRAM("kilogram", "kg"),
    GRAM("gram", "g"),
    MILLIGRAM("milligram", "mg"),
    TON("ton", "t"),
    POUND("pound", "lb"),
    OUNCE("ounce", "oz"),
    LITER("liter", "L"),
    MILLILITER("milliliter", "mL"),
    GALLON("gallon", "gal"),
    PINT("pint", "pt"),
    QUART("quart", "qt");

    private final String name;
    private final String abbreviation;

    Unit(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Unit fromAbbreviation(String abbreviation) {
        for (Unit unit : Unit.values()) {
            if (unit.abbreviation.equalsIgnoreCase(abbreviation)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("No unit found with abbreviation: " + abbreviation);
    }
}
