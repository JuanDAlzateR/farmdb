package com.solvd.farm.util;

import com.solvd.farm.model.BankAccountList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class JAXBParser {
    public static final Logger LOGGER = LogManager.getLogger(JAXBParser.class);

    public static <T> T parse(Class<T> clazz,String filePath) {
        try {
            // create context using root class
            JAXBContext context = JAXBContext.newInstance(BankAccountList.class);

            // Create Unmarshaller (XML reader)
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Read file and cast to root object
            File xmlFile = new File(filePath);

            return (T) unmarshaller.unmarshal(xmlFile);

        } catch (jakarta.xml.bind.JAXBException e) {

            throw new RuntimeException("JAXB parsing error: ", e);
        }
    }

}