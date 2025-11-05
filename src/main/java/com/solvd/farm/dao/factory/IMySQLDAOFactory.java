package com.solvd.farm.dao.factory;

import com.solvd.farm.dao.impl.mysql.PurchasableDAO;
import com.solvd.farm.dao.impl.mysql.ToolDAO;

public interface IMySQLDAOFactory {
    PurchasableDAO createPurchasableDAO();

    ToolDAO createToolDAO();
}
