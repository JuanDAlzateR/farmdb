package com.solvd.farm.model;

public enum Currency {
    USD(1, "dolars", "USD"),
    EUR(2, "euros", "EUR"),
    COP(3, "colombian pesos", "COP");

    private final int id;
    private final String name;
    private final String abbreviation;

    Currency(int id, String name, String abbreviation) {
        this.id = id;
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static Currency fromAbbreviation(String abbreviation) {
        for (Currency currency : Currency.values()) {
            if (currency.abbreviation.equalsIgnoreCase(abbreviation)) {
                return currency;
            }
        }
        throw new IllegalArgumentException("No currency found with abbreviation: " + abbreviation);
    }

    public static Currency fromId(int id) {
        for (Currency currency : Currency.values()) {
            if (currency.id == id) {
                return currency;
            }
        }
        throw new IllegalArgumentException("No currency found with id: " + id);
    }
}
