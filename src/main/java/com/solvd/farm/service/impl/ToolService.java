package com.solvd.farm.service.impl;


import com.solvd.farm.dao.factory.DAOFactoryProducer;
import com.solvd.farm.dao.factory.IMySQLDAOFactory;
import com.solvd.farm.dao.impl.mysql.ToolDAO;
import com.solvd.farm.model.Product;
import com.solvd.farm.model.Tool;
import com.solvd.farm.service.interfaces.IProductService;
import com.solvd.farm.service.interfaces.IToolService;
import com.solvd.farm.util.DAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


public class ToolService implements IToolService {
    public static final Logger LOGGER = LogManager.getLogger(ToolService.class);
    private final IMySQLDAOFactory factory= (IMySQLDAOFactory) DAOFactoryProducer.getFactory(DAOImpl.MYSQL);
    public final ToolDAO toolDAO = factory.createToolDAO();

    @Override
    public Tool getToolById(int id) {
        Tool tool = toolDAO.getToolById(id);
        return tool;

    }

    @Override
    public void save(Tool tool) {
        toolDAO.save(tool);

    }

    public void displayAllTools() {
        ArrayList<Tool> toolList = toolDAO.toolList();
        LOGGER.info(" ");
        LOGGER.info("List of all tools:");
        toolList.stream().forEach(LOGGER::info);
    }

}