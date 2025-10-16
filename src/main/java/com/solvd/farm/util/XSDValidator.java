package com.solvd.farm.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XSDValidator {

    public static final Logger LOGGER = LogManager.getLogger(XSDValidator.class);
    private SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    private Schema schema;
    private Validator validator;

    public XSDValidator(String XSDPath) {
        try {
            File xsdFile = new File(XSDPath);
            schema = factory.newSchema(xsdFile);
            validator = schema.newValidator();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validate(String filePath) {
        File xmlFile = new File(filePath);
        try {
            validator.validate(new StreamSource(xmlFile));
            LOGGER.info("XML document is valid against the XSD schema.");
            return true;
        } catch (SAXException e) {
            LOGGER.info("XML document is NOT valid: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.info("Error reading files: " + e.getMessage());
        }
        return false;
    }
}
