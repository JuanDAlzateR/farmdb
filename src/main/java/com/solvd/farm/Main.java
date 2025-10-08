package com.solvd.farm;

import com.solvd.farm.model.Countable;
import com.solvd.farm.model.Product;
import com.solvd.farm.model.Tool;
import com.solvd.farm.service.impl.CountableService;
import com.solvd.farm.service.impl.ProductService;
import com.solvd.farm.service.impl.ToolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TIP To <b>interfaces.Run</b> code, press <shortcut actionId="interfaces.Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        ProductService productService = new ProductService();

        Product product = productService.getProductById(2);
        LOGGER.info("product searched with id=2:");
        LOGGER.info(product);

        Product product2 = new Product(5F, 0F, 0.8F);
        product2.setPurchasable(4, "usd");
        product2.setCountable("Quake Product", 150, 1);

        productService.save(product2);

        productService.displayAllProducts();


        ToolService toolService = new ToolService();

        Tool tool = toolService.getToolById(2);
        LOGGER.info("tool searched with id=2:");
        LOGGER.info(tool);

        Tool tool2 = new Tool(10F, 0.1F);
        tool2.setPurchasable(75, "usd");
        tool2.setCountable("Big Pitchfork", 1, 1);

        toolService.save(tool2);

        toolService.displayAllTools();

        CountableService countableService = new CountableService();

        Countable countable = countableService.getCountableById(2);
        LOGGER.info("countable searched with id=2:");
        LOGGER.info(countable);

        Product product3 = new Product();
        product3.setCountable("Countable123", 123, 1);

        countableService.save(product3);

        countableService.displayAllCountables();

        product3.setCountable("Countable333", 333, 1);
        countableService.update(product3);

        countableService.displayAllCountables();
    }

    /* This method is just an example of the functionality in the code.
    It can be called in the main menu as EXAMPLE: TEST FARM METHODS
     */

}