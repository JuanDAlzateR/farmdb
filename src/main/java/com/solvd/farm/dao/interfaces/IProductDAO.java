package com.solvd.farm.dao.interfaces;

import com.solvd.farm.model.Product;

public interface IProductDAO {
    void save(Product product);
    Product getProductById(int id);
}
