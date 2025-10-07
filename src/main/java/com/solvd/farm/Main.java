package com.solvd.farm;

import com.solvd.farm.model.Product;
import com.solvd.farm.service.impl.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//TIP To <b>interfaces.Run</b> code, press <shortcut actionId="interfaces.Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ProductService productService=new ProductService();

        Product product=productService.getProductById(2);
        LOGGER.info("product searched with id=2:");
        LOGGER.info(product);

        Product product2=new Product(5F, 0F, 0.8F);
        product2.setPurchasable(4,"usd");
        product2.setCountable("Quake Product",150,1);

        productService.save(product2);
        LOGGER.info(" ");
        LOGGER.info("List of all products:");
        productService.displayAllProducts();

    }

    /* This method is just an example of the functionality in the code.
    It can be called in the main menu as EXAMPLE: TEST FARM METHODS
     */

}