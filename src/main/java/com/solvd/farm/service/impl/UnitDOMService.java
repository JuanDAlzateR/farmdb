package com.solvd.farm.service.impl;

import com.solvd.farm.model.Tool;
import com.solvd.farm.model.UnitOfMeasurement;
import com.solvd.farm.util.DOMParser;
import com.solvd.farm.util.UnitDOMParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class UnitDOMService {
    public static final Logger LOGGER = LogManager.getLogger(UnitDOMService.class);

    public UnitOfMeasurement getUnitById(int id) {
        return UnitDOMParser.getUnitById(id);
    }

    public void displayAllUnits() {
        ArrayList<UnitOfMeasurement> unitsList = UnitDOMParser.getUnitsList();
        LOGGER.info(" ");
        LOGGER.info("List of all units:");
        unitsList.stream().forEach(LOGGER::info);
    }
}
