package com.solvd.farm.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.farm.Main;
import com.solvd.farm.model.AnimalType;
import com.solvd.farm.model.AnimalTypeList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ex {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException {

        AnimalTypeList animalTypeList=AnimalTypeJSONParser.deserealize();
        AnimalType animalType=new AnimalType(25,"new","new sub");
        animalTypeList.remove(25);
        animalTypeList.remove(20);
        AnimalTypeJSONParser.serealize(animalTypeList);
        AnimalTypeJSONParser.initLoad();

    }
}
