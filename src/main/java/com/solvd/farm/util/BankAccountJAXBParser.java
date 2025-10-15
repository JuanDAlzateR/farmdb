package com.solvd.farm.util;

import com.solvd.farm.model.BankAccount;
import com.solvd.farm.model.BankAccountList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class BankAccountJAXBParser {

    private static String filePath = "src/main/resources/bankaccounts.xml";

    public static BankAccountList getBankAccountList(String xmlFilePath) {
        try {
            // create context using root class
            JAXBContext context = JAXBContext.newInstance(BankAccountList.class);

            // Create Unmarshaller (XML reader)
            Unmarshaller unmarshaller = context.createUnmarshaller();

            // Read file and cast to root object
            File xmlFile = new File(filePath);
            BankAccountList bankAccountList = (BankAccountList) unmarshaller.unmarshal(xmlFile);

            return bankAccountList;

        } catch (jakarta.xml.bind.JAXBException e) {

            throw new RuntimeException("JAXB parsing error: ", e);
        }
    }
}