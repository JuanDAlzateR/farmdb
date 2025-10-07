package com.solvd.farm.service.impl;


import com.solvd.farm.dao.impl.mysql.ProductDAO;
import com.solvd.farm.dao.interfaces.IProductDAO;
import com.solvd.farm.model.Product;
import com.solvd.farm.service.interfaces.IProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


public class ProductService implements IProductService {
    public static final Logger LOGGER = LogManager.getLogger(ProductService.class);

    @Override
    public Product getProductById(int id) {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(id);
        return product;

    }

    @Override
    public void save(Product product) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.save(product);

    }

    public void displayAllProducts() {
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> productList = productDAO.productList();
        productList.stream().forEach(LOGGER::info);
    }

}
