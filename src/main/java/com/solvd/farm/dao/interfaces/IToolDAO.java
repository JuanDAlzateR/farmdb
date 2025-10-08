package com.solvd.farm.dao.interfaces;

import com.solvd.farm.model.Tool;

public interface IToolDAO {
    void save(Tool tool);

    Tool getToolById(int id);
}
