package com.solvd.farm.service.impl;

import com.solvd.farm.dao.impl.mybatis.ProductDAO;
import com.solvd.farm.model.Product;
import com.solvd.farm.service.interfaces.IProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


public class ProductMybatisService implements IProductService {
    public static final Logger LOGGER = LogManager.getLogger(ProductMybatisService.class);
    private final ProductDAO productDAO;

    public ProductMybatisService() {
        this.productDAO = new ProductDAO();
    }

    public ProductMybatisService(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product getProductById(int id) {
        Product product = productDAO.getProductById(id);
        return product;

    }

    @Override
    public void save(Product product) {
        productDAO.save(product);

    }

    public void displayAllProducts() {
        ArrayList<Product> productList = productDAO.productList();
        LOGGER.info(" ");
        LOGGER.info("List of all products:");
        productList.stream().forEach(LOGGER::info);
    }

}
