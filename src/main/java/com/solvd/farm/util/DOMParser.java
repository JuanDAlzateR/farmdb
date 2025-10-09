package com.solvd.farm.util;

import com.solvd.farm.model.UnitOfMeasurement;
import com.solvd.farm.service.impl.CountableService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DOMParser {
    public static final Logger LOGGER = LogManager.getLogger(DOMParser.class);

    public static Element parse(String filePath) {
        try {
            // Step 1: Create a DocumentBuilderFactory and DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Step 2: Parse the XML file
            File file = new File(filePath);
            Document document = builder.parse(file);

            // Normalize the document
            document.getDocumentElement().normalize();

            // Step 3: Get the root element
            return document.getDocumentElement();

        } catch (Exception e) {
            throw new RuntimeException("Parsing XML error: " + filePath, e);
        }
    }

}