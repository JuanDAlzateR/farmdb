package com.solvd.farm.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@Displayable
public class BankAccount {

    public static final Logger LOGGER = LogManager.getLogger(BankAccount.class);
    private String bankName;
    private Float balance;
    private String accountNickname;
    private String accountNumber;
    private Currency currency;

    public BankAccount(String bankName, String accountNumber, Float balance) {
        this.bankName=bankName;
        this.accountNumber=accountNumber;
        this.balance=balance;
        this.currency=Currency.USD;

    }

}