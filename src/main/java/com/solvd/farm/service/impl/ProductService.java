package com.solvd.farm.service.impl;

import com.solvd.farm.dao.factory.DAOFactoryProducer;
import com.solvd.farm.dao.factory.IDAOFactory;
import com.solvd.farm.dao.interfaces.IProductDAO;
import com.solvd.farm.model.Product;
import com.solvd.farm.service.interfaces.IProductService;
import com.solvd.farm.util.DAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ProductService implements IProductService {
    public static final Logger LOGGER = LogManager.getLogger(ProductService.class);
    private final IDAOFactory factory;
    private final IProductDAO productDAO;

    public ProductService(DAOImpl type) {
        factory = DAOFactoryProducer.getFactory(type);
        productDAO = factory.createProductDAO();
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
