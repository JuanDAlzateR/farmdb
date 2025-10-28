package com.solvd.farm.dao.interfaces;

import com.solvd.farm.model.Product;

import java.util.ArrayList;

public interface IProductDAO {
    void save(Product product);

    Product getProductById(int id);

    ArrayList<Product> productList();
}
