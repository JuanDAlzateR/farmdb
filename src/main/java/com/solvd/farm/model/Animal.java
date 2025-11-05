package com.solvd.farm.model;

public class Animal extends Purchasable {

    private AnimalFood animalFood;
    private AnimalFeed animalFeed;
    private AnimalTypeEnum animalTypeEnum;


    public Animal() {
        super("New animal", 0);
        this.animalFood = new AnimalFood();
        this.animalFeed = new AnimalFeed();
    }

    public Animal(String name, int quantity, AnimalFood animalFood, AnimalFeed animalFeed, AnimalTypeEnum animalTypeEnum) {
        super(name, quantity);
        this.animalFood = animalFood;
        this.animalFeed = animalFeed;
        this.animalTypeEnum = animalTypeEnum;
    }

    public Animal(Builder builder) {
        this.setCountable(builder.getCountableId(), builder.getName(), builder.getQuantity(), builder.getFarmId());
        this.setPurchasableId(builder.getPurchasableId());
        this.setPrice(builder.getPrice());
        this.setCurrency(builder.getCurrency());
        this.animalFood = builder.animalFood;
        this.animalFeed = builder.animalFeed;
        this.animalTypeEnum = builder.animalTypeEnum;
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

    public static class Builder extends Purchasable {
        private AnimalFood animalFood = new AnimalFood();
        private AnimalFeed animalFeed = new AnimalFeed();
        private AnimalTypeEnum animalTypeEnum = AnimalTypeEnum.OTHERS;

        public Builder(String name) {
            this.setCountable(name, 1, 1);
        }

        //Countable
        public Builder countableId(int id) {
            this.setCountableId(id);
            return this;
        }

        public Builder quantity(float quantity) {
            this.setQuantity(quantity);
            return this;
        }

        public Builder farmId(int id) {
            this.setFarmId(id);
            return this;
        }

        //Purchasable
        public Builder purchasableId(int id) {
            this.setPurchasableId(id);
            return this;
        }

        public Builder pricePerUnit(float price) {
            this.setPrice(price);
            return this;
        }

        public Builder currency(Currency currency) {
            this.setCurrency(currency);
            return this;
        }

        //Animal
        public Builder animalFood(AnimalFood animalFood) {
            this.animalFood = animalFood;
            return this;
        }

        public Builder animalFeed(AnimalFeed animalFeed) {
            this.animalFeed = animalFeed;
            return this;
        }

        public Builder animalType(AnimalTypeEnum animalTypeEnum) {
            this.animalTypeEnum = animalTypeEnum;
            return this;
        }

        public Animal build() {
            return new Animal(this);
        }

    }

}