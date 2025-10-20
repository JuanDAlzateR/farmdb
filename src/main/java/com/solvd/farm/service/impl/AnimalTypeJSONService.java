package com.solvd.farm.service.impl;

import com.solvd.farm.model.BankAccount;
import com.solvd.farm.model.BankAccountList;
import com.solvd.farm.util.BankAccountJAXBParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnimalTypeJSONService {
    public static final Logger LOGGER = LogManager.getLogger(AnimalTypeJSONService.class);
    public static BankAccountJAXBParser bankJAXBParser = new BankAccountJAXBParser();

    public BankAccount getBankAccountByKey(String bankName, String accountNumber) {
        return BankAccountJAXBParser.getBankAccountByKey(bankName, accountNumber);
    }

    public void displayAllBankAccounts() {
        BankAccountList bankAccountsList = BankAccountJAXBParser.getBankAccountList();
        bankAccountsList.displayWithBalance();
    }
}
