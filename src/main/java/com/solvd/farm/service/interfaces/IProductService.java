package com.solvd.farm.service.interfaces;

import com.solvd.farm.model.Product;

public interface IProductService {
   void save(Product product);
    Product getProductById(int id);
}
