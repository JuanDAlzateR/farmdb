package com.solvd.farm.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//@Displayable
@XmlRootElement(name = "account") // Aunque no es el root del XML, es el root de la estructura
@XmlAccessorType(XmlAccessType.FIELD) // Indica que JAXB debe usar los campos/atributos
public class BankAccount {

    public static final Logger LOGGER = LogManager.getLogger(BankAccount.class);
    @XmlElement(name = "bank_name")
    private String bankName;
    @XmlElement(name = "balance")
    private Float balance;
    @XmlElement(name = "nickname")
    private String accountNickname;
    @XmlElement(name = "account_number")
    private String accountNumber;
    @XmlElement(name = "Currency_id")
    private int currencyId;
    private Currency currency;

    // JAXB needs a constructor without parameters
    public BankAccount() {
    }

    public BankAccount(String bankName, String accountNumber, Float balance) {
        this.bankName = bankName;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = Currency.fromId(this.currencyId);

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

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public Float getBalance() {
        return balance;
    }

    public void reloadCurrency() {
        currency = Currency.fromId(currencyId);
    }
}