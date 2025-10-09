package com.solvd.farm.util;

import com.solvd.farm.model.BankAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;

public class BankAccountDOMParser {
    public static final Logger LOGGER = LogManager.getLogger(BankAccountDOMParser.class);

    private static String filePath = "src/main/resources/bankaccounts.xml";
    private static Element root = DOMParser.parse(filePath);

    public static BankAccount getBankAccountByKey(String bankName, String accountNumber) {

        try {
            // Get all nodes
            NodeList nodeList = root.getElementsByTagName("account");

            // Iterate over nodes and extract information
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String nodeBankName = getContent(element, "bank_name");
                    Float balance = Float.parseFloat(getContent(element, "abbreviation"));
                    String nickname = getContent(element, "nickname");
                    String nodeAccountNumber = getContent(element, "account_number");
                    int currencyId = Integer.parseInt(getContent(element, "abbreviation"));

                    if (bankName.equals(nodeBankName) && accountNumber.equals(nodeAccountNumber)) {
                        BankAccount bankAccount = new BankAccount(bankName, balance, nickname, accountNumber, currencyId);
                        return bankAccount;
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Finding bank account error: " + e);
        }
        return null;
    }

    public static String getContent(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }


    public static ArrayList<BankAccount> getBankAccountsList() {

        ArrayList<BankAccount> bankAccountList = new ArrayList<>();
        try {
            // Get all nodes
            NodeList nodeList = root.getElementsByTagName("account");

            // Iterate over nodes and extract information
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String bankName = getContent(element, "bank_name");
                    Float balance = Float.parseFloat(getContent(element, "balance"));
                    String nickname = getContent(element, "nickname");
                    String accountNumber = getContent(element, "account_number");
                    int currencyId = Integer.parseInt(getContent(element, "Currency_id"));

                    BankAccount bankAccount = new BankAccount(bankName, balance, nickname, accountNumber, currencyId);
                    bankAccountList.add(bankAccount);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("Parsing bank accounts error: " + e);
        }
        return bankAccountList;
    }

}