package com.solvd.farm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;

public class JSONParser {
    public static final Logger LOGGER = LogManager.getLogger(JSONParser.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T,U> T parse(Class<T> clazzT,Class<U> clazzU, String filePath) {
        try {
            // Read file and cast to root object
            File jsonFile = new File(filePath);


            ArrayList<Class<U>> objects= MAPPER.readValue(jsonFile,new TypeReference<ArrayList<Class<U>>>(){});

            T objectList = clazzT.getDeclaredConstructor().newInstance();
            //objectList.setList(objects);

            return objectList;

        } catch (Exception e) {

            throw new RuntimeException("JSON parsing error: ", e);
        }
    }

}