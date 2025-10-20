package com.solvd.farm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.farm.model.AnimalType;
import com.solvd.farm.model.AnimalTypeList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class AnimalTypeJSONParser {
    public static final Logger LOGGER = LogManager.getLogger(AnimalTypeJSONParser.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static String filePath="src/main/resources/animaltypes.json";
    private static String initFilePath="src/main/resources/animaltypes_init.json";
    private static AnimalTypeList animalTypeList;

    public static AnimalTypeList getAnimalTypeList() {
        return animalTypeList;
    }
    public static void setAnimalTypeList(AnimalTypeList animalTypeList) {
        AnimalTypeJSONParser.animalTypeList = animalTypeList;
    }

    public static void initLoad() {
        Path source = Paths.get(initFilePath);
        Path destination = Paths.get(filePath);

        try {
            // StandardCopyOption.REPLACE_EXISTING allows overwriting
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);

            LOGGER.info("succesfull initial load of: "+filePath);

        } catch (IOException e) {

            LOGGER.info("Error of initial load: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static AnimalTypeList deserealize() {
        try {
            File jsonFile = new File(filePath);

            ArrayList<AnimalType> animalTypes= MAPPER.readValue(jsonFile,new TypeReference<ArrayList<AnimalType>>(){});
            AnimalTypeList newAnimalTypeList = new AnimalTypeList();
            newAnimalTypeList.setList(animalTypes);
            animalTypeList=newAnimalTypeList;

            return animalTypeList;

        } catch (Exception e) {

            throw new RuntimeException("JSON parsing error: ", e);
        }
    }

    public static void serealize(AnimalTypeList typeList) {

        try {
            // writerWithDefaultPrettyPrinter() it's used to format output with indent and newlines.
            String jsonString= MAPPER.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(typeList.getList());

            Path path = Paths.get(filePath);
            // Creates or overwrite4s the file.
            Files.writeString(path,jsonString);

           LOGGER.info("updated: " + filePath);

        } catch (IOException e) {
            // Manejar errores de I/O (ej. permisos, ruta no válida)
            e.printStackTrace();
        }

    }




}