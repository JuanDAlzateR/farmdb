package com.solvd.farm.service.impl;


import com.solvd.farm.dao.impl.mysql.CountableDAO;
import com.solvd.farm.model.Countable;
import com.solvd.farm.service.interfaces.ICountableService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;


public class CountableService implements ICountableService {
    public static final Logger LOGGER = LogManager.getLogger(CountableService.class);
    public final CountableDAO countableDAO = new CountableDAO();

    @Override
    public Countable getCountableById(int id) {
        Countable countable = countableDAO.getCountableById(id);
        return countable;

    }

    @Override
    public void save(Countable countable) {
        countableDAO.save(countable);

    }

    @Override
    public void update(Countable c) {
        countableDAO.update(c.getCountableId(), c.getName(), c.getQuantity(), c.getFarmId());

    }

    public void displayAllCountables() {
        ArrayList<Countable> countableList = countableDAO.countableList();
        LOGGER.info(" ");
        LOGGER.info("List of all countables:");
        countableList.stream().forEach(LOGGER::info);
    }

}
