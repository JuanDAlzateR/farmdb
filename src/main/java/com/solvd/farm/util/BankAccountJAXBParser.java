package com.solvd.farm.util;

import com.solvd.farm.model.BankAccount;
import com.solvd.farm.model.BankAccountList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankAccountJAXBParser {
    public static final Logger LOGGER = LogManager.getLogger(BankAccountJAXBParser.class);
    private static String filePath = "src/main/resources/bankaccounts.xml";
    private static BankAccountList bankAccountList;
    private static XSDValidator xsdValidator = new XSDValidator("src/main/resources/bankaccount_schema.xsd");

    public BankAccountJAXBParser() {
        reloadBankAccountList();
    }

    public static BankAccountList getBankAccountList() {
        return bankAccountList;
    }

    public static BankAccount getBankAccountByKey(String bankName, String accountNumber) {
        return bankAccountList.getBankAccountByKey(bankName, accountNumber);
    }

    public void reloadBankAccountList() {
        if (xsdValidator.validate(filePath)) {
            this.bankAccountList = JAXBParser.parse(BankAccountList.class, filePath);
            //After unmarshall the xml, it's necessary to load the currency field from currencyId.
            bankAccountList.reloadCurrencies();
        } else {
            LOGGER.info(filePath + " doesn't pass the validator test");
        }
    }
}