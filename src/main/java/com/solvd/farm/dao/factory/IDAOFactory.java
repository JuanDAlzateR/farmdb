package com.solvd.farm.dao.factory;

import com.solvd.farm.dao.interfaces.ICountableDAO;
import com.solvd.farm.dao.interfaces.IProductDAO;

public interface IDAOFactory {
    IProductDAO createProductDAO();
    ICountableDAO createCountableDAO();
}
