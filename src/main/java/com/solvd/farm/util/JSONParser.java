package com.solvd.farm.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class JSONParser {
    public static final Logger LOGGER = LogManager.getLogger(JSONParser.class);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> T parse(Class<T> clazz, String filePath) {
        try {
            // Read file and cast to root object
            File jsonFile = new File(filePath);

            return MAPPER.readValue(jsonFile, clazz);

        } catch (Exception e) {

            throw new RuntimeException("JSN parsing error: ", e);
        }
    }

}