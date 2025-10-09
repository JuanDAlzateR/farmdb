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
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = Currency.USD;

    }

    public BankAccount(String bankName, Float balance, String nickname, String accountNumber, int currencyId) {
        this.bankName = bankName;
        this.balance = balance;
        this.accountNickname = nickname;
        this.accountNumber = accountNumber;
        this.currency = Currency.fromId(currencyId);

    }

    @Override
    public String toString() {
        return String.format("Bank: " + bankName + "\tAccount #: " + accountNumber + "  Balance: %.2f " + currency, balance);
    }
}