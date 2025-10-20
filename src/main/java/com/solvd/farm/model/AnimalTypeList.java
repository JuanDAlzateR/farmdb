package com.solvd.farm.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class AnimalTypeList {

    public static final Logger LOGGER = LogManager.getLogger(AnimalTypeList.class);


    private ArrayList<AnimalType> animalTypes = new ArrayList<>();

    /* Looks in the array for the corresponding bankAccount
       If it doesn't find it, it returns null  */
    public AnimalType getAnimalTypeBySubcategory(String subcategory) {
        int n = IntStream.range(0, animalTypes.size())
                .filter((i) -> animalTypes.get(i).getSubcategory().equalsIgnoreCase(subcategory))
                .findFirst()
                .orElse(-1);
        if (n > -1) {
            return animalTypes.get(n);
        }
        return null;

    }

    /* Looks in the array for the index of item with that name
        If it doesn't find it, it returns -1  */
    public int indexOf(String subcategory) {
        return IntStream.range(0, animalTypes.size())
                .filter((i) -> animalTypes.get(i).getSubcategory().equalsIgnoreCase(subcategory))
                .findFirst()
                .orElse(-1);

    }

    public void add(AnimalType animalType) {
        int index = indexOf(animalType.getSubcategory());
        if (index > -1) {
            LOGGER.info("Error: There is an animalType with the same subcategory");
        } else {
            animalTypes.add(animalType);
        }
    }

    public void remove(AnimalType animalType) {
        int index = indexOf(animalType.getSubcategory());
        if (index == -1) {
            LOGGER.info("Error: animalType doesn't exist");
        } else {
            animalTypes.remove(index);
        }
    }

    public void remove(int id) {
        int index = IntStream.range(0, animalTypes.size())
                .filter((i) -> animalTypes.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (index == -1) {
            LOGGER.info("Error: animalType with id " + id + " doesn't exist");
        } else {
            animalTypes.remove(index);
        }
    }

    public void display() {
        LOGGER.info("");
        LOGGER.info("list of all animal types");

        animalTypes.stream()
                .forEach(LOGGER::info);
    }

    public ArrayList<AnimalType> getList() {
        return this.animalTypes;
    }

    public void setList(ArrayList<AnimalType> animalTypes) {
        this.animalTypes = animalTypes;
    }

}