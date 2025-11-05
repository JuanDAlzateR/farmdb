package com.solvd.farm.dao.interfaces;

import com.solvd.farm.model.Tool;

import java.util.ArrayList;

public interface IToolDAO {
    void save(Tool tool);

    Tool getToolById(int id);

    ArrayList<Tool> toolList();

}
