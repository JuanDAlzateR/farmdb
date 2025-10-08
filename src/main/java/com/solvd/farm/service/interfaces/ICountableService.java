package com.solvd.farm.service.interfaces;

import com.solvd.farm.model.Countable;
import com.solvd.farm.model.Tool;

public interface ICountableService {
    void save(Countable countable);

    Countable getCountableById(int id);

    void update(Countable countable);
}
