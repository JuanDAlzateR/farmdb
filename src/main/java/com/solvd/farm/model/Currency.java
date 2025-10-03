package com.solvd.farm.model;

public enum Currency {
    USD("dolars", "USD"),
    EUR("euros", "EUR"),
    COP("colombian pesos", "COP");

    private final String name;
    private final String abbreviation;

    Currency(String name, String abbreviation) {
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}
