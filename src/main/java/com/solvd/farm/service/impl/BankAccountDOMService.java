package com.solvd.farm.service.impl;

import com.solvd.farm.model.BankAccount;
import com.solvd.farm.model.UnitOfMeasurement;
import com.solvd.farm.util.BankAccountDOMParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class BankAccountDOMService {
    public static final Logger LOGGER = LogManager.getLogger(BankAccountDOMService.class);

    public BankAccount getBankAccountByKey(String bankName, String accountNumber) {
        return BankAccountDOMParser.getBankAccountByKey(bankName, accountNumber);
    }

    public void displayAllBankAccounts() {
        ArrayList<BankAccount> bankAccountsList = BankAccountDOMParser.getBankAccountsList();
        LOGGER.info(" ");
        LOGGER.info("List of all bank accounts:");
        bankAccountsList.stream().forEach(LOGGER::info);
    }
}
