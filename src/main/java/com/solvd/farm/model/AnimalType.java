package com.solvd.farm.model;

public class AnimalType {
    private int id;
    private String category;
    private String subcategory;

    public AnimalType() {
    }

    public AnimalType(int id, String category, String subcategory) {
        this.id = id;
        this.category = category;
        this.subcategory = subcategory;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    @Override
    public String toString() {
        return "id: " + id + "  category: " + category + "  subcategory: " + subcategory;
    }
}
