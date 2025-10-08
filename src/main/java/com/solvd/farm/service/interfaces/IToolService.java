package com.solvd.farm.service.interfaces;

import com.solvd.farm.model.Product;
import com.solvd.farm.model.Tool;

public interface IToolService {
    void save(Tool tool);

    Tool getToolById(int id);
}
