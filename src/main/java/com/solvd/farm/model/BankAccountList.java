package com.solvd.farm.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.stream.IntStream;

@XmlRootElement(name = "bankAccounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class BankAccountList {

    public static final Logger LOGGER = LogManager.getLogger(BankAccountList.class);

    @XmlElement(name = "account")
    private final ArrayList<BankAccount> bankAccounts = new ArrayList<>();
    int defaultAccountIndex = 0;

    // JAXB needs a constructor without parameters
    public BankAccountList() {}

    /* Looks in the array for the corresponding bankAccount
       If it doesn't find it, it returns null  */
    public BankAccount getBankAccountByKey(String bankName, String accountNumber) {
        int n=IntStream.range(0, bankAccounts.size())
                .filter((i) -> bankAccounts.get(i).getAccountNumber() == accountNumber)
                .filter((i) -> bankAccounts.get(i).getBankName() == bankName)
                .findFirst()
                .orElse(-1);
        if (n>-1) {
            return bankAccounts.get(n);
        }
        return null;

    }


    /* Looks in the array for the index of item with that name
        If it doesn't find it, it returns -1  */

    public int indexOf(String accountNumber) {
        return IntStream.range(0, bankAccounts.size())
                .filter((i) -> bankAccounts.get(i).getAccountNumber() == accountNumber)
                .findFirst()
                .orElse(-1);

    }

    public void add(BankAccount bankAccount) {
        int index = indexOf(bankAccount.getAccountNumber());
        if (index > -1) {
            LOGGER.info("Error: There is an account with the same account number");
        } else {
            bankAccounts.add(bankAccount);
        }
    }

    public void display(Boolean displayIndex) {
        LOGGER.info("");
        LOGGER.info("list of all bank accounts");

        IntStream.range(0, this.bankAccounts.size())
                .forEach(i -> {
                    String accountNumber = this.bankAccounts.get(i).getAccountNumber();
                    String bankName = this.bankAccounts.get(i).getBankName();
                    String indexString = displayIndex ? i + ") " : "";
                    if (i == this.defaultAccountIndex) {
                        LOGGER.info("\t" + indexString + bankName + " - Account #: " + accountNumber + " (Default Account)");
                    } else {
                        LOGGER.info("\t" + indexString + bankName + " - Account #: " + accountNumber);
                    }
                });
    }

    public void displayWithBalance() {
        LOGGER.info("");
        LOGGER.info("list of all bank accounts");

        IntStream.range(0, this.bankAccounts.size())
                .forEach(i -> {
                    String accountNumber = this.bankAccounts.get(i).getAccountNumber();
                    String bankName = this.bankAccounts.get(i).getBankName();
                    float balance = this.bankAccounts.get(i).getBalance();
                    if (i == this.defaultAccountIndex) {
                        LOGGER.info("\t" + i + ") " + bankName + " - Account #: " + accountNumber +
                                "- Balance: " + balance + " (Default Account)");
                    } else {
                        LOGGER.info("\t" + i + ") " + bankName + " - Account #: " + accountNumber +
                                "- Balance: " + balance);
                    }
                });
    }

    public ArrayList<BankAccount> getList() {
        return this.bankAccounts;
    }

    public void setDefaultAccountIndex(int defaultAccountIndex) {
        this.defaultAccountIndex = defaultAccountIndex;
    }

    public int getDefaultAccountIndex() {
        return defaultAccountIndex;
    }

    public BankAccount setDefaultAccount() {
        return this.bankAccounts.get(this.defaultAccountIndex);
    }
}