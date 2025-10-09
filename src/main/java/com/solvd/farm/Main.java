package com.solvd.farm;

import com.solvd.farm.model.*;
import com.solvd.farm.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TIP To <b>interfaces.Run</b> code, press <shortcut actionId="interfaces.Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        //Services from DAO
        useServices();

        //Services from DOM
        UnitDOMService unitDOMService = new UnitDOMService();

        LOGGER.info("unit with id=3");
        LOGGER.info(unitDOMService.getUnitById(3));
        LOGGER.info("");
        //unitDOMService.displayAllUnits();

    }

    private static void useServices() {

        //-------------ProductService
        ProductService productService = new ProductService();

        LOGGER.info("product searched with id=2:");
        LOGGER.info(productService.getProductById(2));
        LOGGER.info("");

        Product product2 = new Product(5F, 0F, 0.8F);
        product2.setPurchasable(4, "usd");
        product2.setCountable("Quake Product", 150, 1);

        productService.save(product2);

        //-------------ToolService
        ToolService toolService = new ToolService();

        LOGGER.info("tool searched with id=2:");
        LOGGER.info(toolService.getToolById(2));
        LOGGER.info("");
        Tool tool2 = new Tool(10F, 0.1F);
        tool2.setPurchasable(75, "usd");
        tool2.setCountable("Big Pitchfork", 1, 1);

        toolService.save(tool2);

        //-------------CountableService
        CountableService countableService = new CountableService();

        LOGGER.info("countable searched with id=2:");
        LOGGER.info(countableService.getCountableById(2));
        LOGGER.info("");
        Product product3 = new Product();
        product3.setCountable("Countable123", 123, 1);

        countableService.save(product3);

        product3.setCountable("Countable333", 333, 1);
        countableService.update(product3);

        //countableService.displayAllCountables();

        PurchasableService purchasableService = new PurchasableService();

        //-------------PurchasableService

        LOGGER.info("purchasable searched with id=3:");
        LOGGER.info(purchasableService.getPurchasableById(3));
        LOGGER.info("");
        Product product4 = new Product();
        product4.setCountable("Purchasable456", 456, 1);
        product4.setPurchasable(5, "usd");

        purchasableService.save(product4);

        product4.setPurchasable(6, "eur");
        product4.setCountable("Purchasable777", 500, 1);
        purchasableService.update(product4);

        //purchasableService.displayAllPurchasables();
    }

}