package com.solvd.farm.model;

public abstract class Animal extends Purchasable {

    private AnimalFood animalFood;
    private AnimalFeed animalFeed;
    private AnimalType animalType;


    public Animal() {
        super("New animal", 0);
        this.animalFood = new AnimalFood();
        this.animalFeed = new AnimalFeed();
    }

    public Animal(String name, int quantity, AnimalFood animalFood, AnimalFeed animalFeed, AnimalType animalType) {
        super(name, quantity);
        this.animalFood = animalFood;
        this.animalFeed = animalFeed;
        this.animalType = animalType;
    }

    @Override
    public String toString() {
        String line = (this.getQuantity() + " " + this.getName() + " | " +
                this.animalFeed.getName() + ": %.2f " + this.animalFeed.getFeedUnit() + " | " +
                this.animalFood.getName() + ": %.1f " + this.animalFood.getProductionUnit());
        return String.format(line, this.animalFeed.getQuantity(), this.animalFood.getQuantity());
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Animal)) {
            return false;
        } else {
            return (((Animal) object).getName().equals(this.getName()));
        }

    }

    @Override
    public int hashCode() {
        return this.getName().hashCode();
    }

    public void setAnimalFood(AnimalFood Rate) {
        this.animalFood = Rate;
    }

    public void setAnimalFeed(AnimalFeed Percentage) {
        this.animalFeed = Percentage;
    }

    public void setAnimal(String name, int quantity, AnimalFood animalFood, AnimalFeed animalFeed) {
        this.setName(name);
        this.setQuantity(quantity);
        this.animalFood = animalFood;
        this.animalFeed = animalFeed;
    }

    public AnimalFood getAnimalFood() {
        return this.animalFood;
    }

    public AnimalFeed getAnimalFeed() {
        return this.animalFeed;
    }

//    @Override
//    public void passTime(int days) {
//        this.animalFood.produce(days);
//        this.animalFeed.consume(days);
//    }
//
//    @Override
//    public void addToFarm(Farm farm) {
//        farm.addAnimal(this);
//    }

}