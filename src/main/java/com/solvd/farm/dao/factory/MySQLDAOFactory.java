package com.solvd.farm.dao.factory;

import com.solvd.farm.dao.impl.mysql.CountableDAO;
import com.solvd.farm.dao.impl.mysql.ProductDAO;
import com.solvd.farm.dao.impl.mysql.PurchasableDAO;
import com.solvd.farm.dao.impl.mysql.ToolDAO;

public class MySQLDAOFactory implements IDAOFactory {
    @Override
    public ProductDAO createProductDAO() {
        return new ProductDAO();
    }

    @Override
    public CountableDAO createCountableDAO() {
        return new CountableDAO();
    }

    public PurchasableDAO createPurchasableDAO() {
        return new PurchasableDAO();
    }

    public ToolDAO createToolDAO() {
        return new ToolDAO();
    }
}
