package com.solvd.farm.dao.factory;

import com.solvd.farm.dao.impl.mybatis.CountableDAO;
import com.solvd.farm.dao.impl.mybatis.ProductDAO;

public class MyBatisDAOFactory implements IDAOFactory {
    @Override
    public ProductDAO createProductDAO() {
        return new ProductDAO();
    }

    @Override
    public CountableDAO createCountableDAO() {
        return new CountableDAO();
    }

}
