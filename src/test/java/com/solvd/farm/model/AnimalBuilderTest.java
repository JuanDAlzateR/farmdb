package com.solvd.farm.model;

import com.solvd.farm.dao.impl.CountableDAOTest;
import com.solvd.farm.dao.interfaces.ICountableDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

public class AnimalBuilderTest {
    public static final Logger LOGGER = LogManager.getLogger(AnimalBuilderTest.class);
    //  private Animal testAnimal;

    @Test(testName = "Verify build of Countable fields", description = "verifies correct animal build of countable fields", dataProvider = "Countable Provider")
    public void testCountableBuild(Countable countable) {
        Animal testAnimal = new Animal.Builder(countable.getName())
                .countableId(countable.getCountableId())
                .quantity(countable.getQuantity())
                .farmId(countable.getFarmId())
                .build();

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(testAnimal.getName(), countable.getName(), "name didn't build correctly");
        sa.assertEquals(testAnimal.getCountableId(), countable.getCountableId(), "countableId didn't build correctly");
        sa.assertEquals(testAnimal.getQuantity(), countable.getQuantity(), "quantity didn't build correctly");
        sa.assertEquals(testAnimal.getFarmId(), countable.getFarmId(), "farmId didn't build correctly");

        sa.assertAll();
    }

    @Test(testName = "Verify build of Purchasable fields", description = "verifies correct animal build of purchasable fields", dataProvider = "Purchasable Provider")
    public void testPurchasableBuild(Purchasable purchasable) {
        Animal testAnimal = new Animal.Builder(purchasable.getName())
                .purchasableId(purchasable.getPurchasableId())
                .pricePerUnit(purchasable.getPrice())
                .currency(purchasable.getCurrency())
                .build();

        SoftAssert sa = new SoftAssert();
        sa.assertEquals(testAnimal.getPurchasableId(), purchasable.getPurchasableId(), "countableId didn't build correctly");
        sa.assertEquals(testAnimal.getPrice(), purchasable.getPrice(), "quantity didn't build correctly");
        sa.assertEquals(testAnimal.getCurrency(), purchasable.getCurrency(), "farmId didn't build correctly");
        sa.assertAll();
    }

    @Test(testName = "Verify build of Animal Food", description = "verifies correct animal build of Animal Food", dataProvider = "AnimalFood Provider")
    public void testAnimalFoodBuild(AnimalFood animalFood) {
        Animal testAnimal = new Animal.Builder("test")
                .animalFood(animalFood)
                .build();

        Assert.assertEquals(testAnimal.getAnimalFood(), animalFood, "animal food didn't build correctly");
    }

    @Test(testName = "Verify build of Animal Feed", description = "verifies correct animal build of Animal Feed", dataProvider = "AnimalFeed Provider")
    public void testAnimalFeedBuild(AnimalFeed animalFeed) {
        Animal testAnimal = new Animal.Builder("test")
                .animalFeed(animalFeed)
                .build();

        Assert.assertEquals(testAnimal.getAnimalFeed(), animalFeed, "animal feed didn't build correctly");
    }

    @Test(testName = "Verify build of Animal Type", description = "verifies correct animal build of Animal Feed", dataProvider = "AnimalTypeEnum Provider")
    public void testAnimalTypeBuild(AnimalTypeEnum animalTypeEnum) {
        Animal testAnimal = new Animal.Builder("test")
                .animalType(animalTypeEnum)
                .build();

        Assert.assertEquals(testAnimal.getAnimalTypeEnum(), animalTypeEnum, "animal Type didn't build correctly");
    }


    @DataProvider(name = "Countable Provider")
    public Object[][] countableData() {
        Product countable1 = new Product();
        countable1.setCountable(0, "Cow Test", 1, 1);
        Product countable2 = new Product();
        countable2.setCountable(0, "Chicken Test", 2, 1);
        return new Object[][]{
                //name, quantity, farmId
                {countable1},
                {countable2},
        };
    }

    @DataProvider(name = "Purchasable Provider")
    public Object[][] purchasableData() {
        Product purchasable1 = new Product();
        purchasable1.setPurchasable(2, 5, "usd");
        Product purchasable2 = new Product();
        purchasable2.setPurchasable(3, 100000, "cop");
        return new Object[][]{
                //id, pricePerUnit, currency
                {purchasable1},
                {purchasable2},
        };
    }

    @DataProvider(name = "AnimalFood Provider")
    public Object[][] animalFoodData() {
        AnimalFood animalFood1 = new AnimalFood("food1", 5, 2, Unit.KILOGRAM);
        AnimalFood animalFood2 = new AnimalFood("food2", 10, 1, Unit.GALLON);

        return new Object[][]{
                {animalFood1},
                {animalFood2},
        };
    }

    @DataProvider(name = "AnimalFeed Provider")
    public Object[][] animalFeedData() {
        AnimalFeed animalFeed1 = new AnimalFeed("hen", 5, 2, Unit.KILOGRAM);
        AnimalFeed animalFeed2 = new AnimalFeed("water", 10, 1, Unit.GALLON);

        return new Object[][]{
                {animalFeed1},
                {animalFeed2},
        };
    }

    @DataProvider(name = "AnimalTypeEnum Provider")
    public Object[][] animalTypeData() {

        return new Object[][]{
                {AnimalTypeEnum.CATTLE},
                {AnimalTypeEnum.AQUACULTURE},
                {AnimalTypeEnum.ALPACAS},
                {AnimalTypeEnum.DUCKS},
                {AnimalTypeEnum.OTHERS}
        };
    }


}
