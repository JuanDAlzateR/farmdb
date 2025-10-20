package com.solvd.farm.model;

public enum AnimalTypeEnum {
    FISH("Aquaculture", "Fish"),
    SHELLFISH("Aquaculture", "Shellfish"),
    AQUACULTURE("Aquaculture", "Undefined Aquaculture"),
    HORSES("Equines", "Horses"),
    DONKEYS("Equines", "Donkeys"),
    MULES("Equines", "Mules"),
    EQUINES("Equines", "Undefined Equines"),
    CATTLE("Livestock", "Cattle"),
    PIGS("Livestock", "Pigs"),
    SHEEP("Livestock", "Sheep"),
    GOATS("Livestock", "Goats"),
    LIVESTOCK("Livestock", "Undefined Livestock"),
    CHICKENS("Poultry", "Chickens"),
    TURKEYS("Poultry", "Turkeys"),
    DUCKS("Poultry", "Ducks"),
    GEESE("Poultry", "Geese"),
    POULTRY("Poultry", "Undefined Poultry"),
    RABBITS("Others", "Rabbits"),
    BEES("Others", "Bees"),
    ALPACAS("Others", "Alpacas"),
    OTHERS("Others", "Undefined Others");

    private final String category;
    private final String subcategory;

    AnimalTypeEnum(String category, String subcategory) {
        this.category = category;
        this.subcategory = subcategory;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public static AnimalTypeEnum fromSubcategory(String subcategory) {
        for (AnimalTypeEnum unit : AnimalTypeEnum.values()) {
            if (unit.subcategory.equalsIgnoreCase(subcategory)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("No unit found with subcategory: " + subcategory);
    }
}
