package com.solvd.farm.util;

import com.solvd.farm.model.BankAccount;
import com.solvd.farm.model.BankAccountList;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class BankAccountJAXBParser {

    private static String filePath = "src/main/resources/bankaccounts.xml";
    private static BankAccountList bankAccountList;

    public BankAccountJAXBParser(){
        this.bankAccountList=JAXBParser.parse(BankAccountList.class, filePath);
    }

    public static BankAccountList getBankAccountList() {
        return  bankAccountList;
    }

    public static BankAccount getBankAccountByKey(String bankName, String accountNumber) {
        return  bankAccountList.getBankAccountByKey(bankName,accountNumber);
    }

    public void reloadBankAccountList() {
        this.bankAccountList=JAXBParser.parse(BankAccountList.class, filePath);
    }
}