package com.solvd.farm.util;

import com.solvd.farm.model.UnitOfMeasurement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class UnitDOMParser {
    public static final Logger LOGGER = LogManager.getLogger(UnitDOMParser.class);

    private static String filePath = "src/main/resources/units.xml";
    private static Element root = DOMParser.parse(filePath);

    public static UnitOfMeasurement getUnitById(int unitId) {
        String stringId = String.valueOf(unitId);
        try {
            // Get all unitOfMeasure nodes
            NodeList nodeList = root.getElementsByTagName("unitOfMeasure");

            // Iterate over unitOfMeasure nodes and extract information
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getAttribute("id");
                    String unit = element.getElementsByTagName("unit").item(0).getTextContent();
                    String abbreviation = element.getElementsByTagName("abbreviation").item(0).getTextContent();

                    if (stringId.equals(id)) {
                        UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement(Integer.parseInt(id), unit, abbreviation);
                        return unitOfMeasurement;
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Finding unit id error: " + e);
        }
        return null;
    }

    public static ArrayList<UnitOfMeasurement> getUnitsList() {

        ArrayList<UnitOfMeasurement> unitsList = new ArrayList<>();
        try {
            // Get all unitOfMeasure nodes
            NodeList nodeList = root.getElementsByTagName("unitOfMeasure");

            // Iterate over unitOfMeasure nodes and extract information
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String id = element.getAttribute("id");
                    String unit = element.getElementsByTagName("unit").item(0).getTextContent();
                    String abbreviation = element.getElementsByTagName("abbreviation").item(0).getTextContent();

                    UnitOfMeasurement unitOfMeasurement = new UnitOfMeasurement(Integer.parseInt(id), unit, abbreviation);
                    unitsList.add(unitOfMeasurement);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Finding unit id error: " + e);
        }
        return unitsList;
    }

}