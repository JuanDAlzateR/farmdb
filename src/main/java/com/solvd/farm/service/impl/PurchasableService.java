package com.solvd.farm.service.impl;

import com.solvd.farm.dao.factory.DAOFactoryProducer;
import com.solvd.farm.dao.factory.IMySQLDAOFactory;
import com.solvd.farm.dao.impl.mysql.PurchasableDAO;
import com.solvd.farm.model.Purchasable;
import com.solvd.farm.service.interfaces.IPurchasableService;
import com.solvd.farm.util.DAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class PurchasableService implements IPurchasableService {
    public static final Logger LOGGER = LogManager.getLogger(PurchasableService.class);
    private final IMySQLDAOFactory factory;
    public final PurchasableDAO purchasableDAO;

    public PurchasableService() {
        factory = (IMySQLDAOFactory) DAOFactoryProducer.getFactory(DAOImpl.MYSQL);
        purchasableDAO = factory.createPurchasableDAO();
    }

    public PurchasableService(DAOImpl type) {
        factory = (IMySQLDAOFactory) DAOFactoryProducer.getFactory(type);
        purchasableDAO = factory.createPurchasableDAO();
    }

    @Override
    public Purchasable getPurchasableById(int id) {
        Purchasable purchasable = purchasableDAO.getPurchasableById(id);
        return purchasable;

    }

    @Override
    public void save(Purchasable purchasable) {
        purchasableDAO.save(purchasable);

    }

    @Override
    public void update(Purchasable purchasable) {
        purchasableDAO.update(purchasable);

    }

    public void displayAllPurchasables() {
        ArrayList<Purchasable> purchasableList = purchasableDAO.purchasableList();
        LOGGER.info(" ");
        LOGGER.info("List of all purchasables:");
        purchasableList.stream().forEach(LOGGER::info);
    }

}

