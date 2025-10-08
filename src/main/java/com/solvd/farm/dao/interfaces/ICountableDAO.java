package com.solvd.farm.dao.interfaces;

import com.solvd.farm.model.Countable;
import com.solvd.farm.model.Product;

public interface ICountableDAO {
    void save(Countable countable);

    Countable getCountableById(int id);
}
