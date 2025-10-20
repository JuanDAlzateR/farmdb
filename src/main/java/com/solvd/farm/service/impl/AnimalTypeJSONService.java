package com.solvd.farm.service.impl;

import com.solvd.farm.model.AnimalType;
import com.solvd.farm.model.AnimalTypeList;
import com.solvd.farm.util.AnimalTypeJSONParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimalTypeJSONService {
    public static final Logger LOGGER = LogManager.getLogger(AnimalTypeJSONService.class);

    public AnimalTypeJSONService() {
        AnimalTypeJSONParser.deserealize();
    }

    public AnimalType getAnimalTypeBySubcategory(String subcategory) {
        AnimalTypeList animalTypeList = AnimalTypeJSONParser.getAnimalTypeList();
        return animalTypeList.getAnimalTypeBySubcategory(subcategory);
    }

    public void addAnimalType(AnimalType animalType) {
        AnimalTypeList animalTypeList = AnimalTypeJSONParser.getAnimalTypeList();
        animalTypeList.add(animalType);
        AnimalTypeJSONParser.serealize(animalTypeList);
    }

    public void removeAnimalType(AnimalType animalType) {
        AnimalTypeList animalTypeList = AnimalTypeJSONParser.getAnimalTypeList();
        animalTypeList.remove(animalType);
        AnimalTypeJSONParser.serealize(animalTypeList);
    }

    public void display() {
        AnimalTypeList animalTypeList = AnimalTypeJSONParser.getAnimalTypeList();
        animalTypeList.display();
    }

    public void init() {
        AnimalTypeJSONParser.initLoad();
    }
}
