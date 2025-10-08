package com.solvd.farm.service.impl;


import com.solvd.farm.dao.impl.mysql.ToolDAO;
import com.solvd.farm.model.Product;
import com.solvd.farm.model.Tool;
import com.solvd.farm.service.interfaces.IProductService;
import com.solvd.farm.service.interfaces.IToolService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


public class ToolService implements IToolService {
    public static final Logger LOGGER = LogManager.getLogger(ToolService.class);

    @Override
    public Tool getToolById(int id) {
        ToolDAO toolDAO = new ToolDAO();
        Tool tool = toolDAO.getToolById(id);
        return tool;

    }

    @Override
    public void save(Tool tool) {
        ToolDAO toolDAO = new ToolDAO();
        toolDAO.save(tool);

    }

    public void displayAllTools() {
        ToolDAO toolDAO = new ToolDAO();
        ArrayList<Tool> toolList = toolDAO.toolList();
        toolList.stream().forEach(LOGGER::info);
    }

}