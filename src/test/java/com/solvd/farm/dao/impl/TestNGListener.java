package com.solvd.farm.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IExecutionListener;

public class TestNGListener implements IExecutionListener {
    public static final Logger LOGGER = LogManager.getLogger(TestNGListener.class);

    @Override
    public void onExecutionStart() {
        LOGGER.info("TestNG is commencing execution");
    }

    @Override
    public void onExecutionFinish() {
        LOGGER.info("TestNG is finished execution");
    }
}
