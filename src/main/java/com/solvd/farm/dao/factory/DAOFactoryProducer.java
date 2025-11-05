package com.solvd.farm.dao.factory;

import com.solvd.farm.util.DAOImpl;

public class DAOFactoryProducer {
    public static IDAOFactory getFactory(DAOImpl type) {

        return switch (type) {
            case DAOImpl.MYSQL -> new MySQLDAOFactory();
            case DAOImpl.MYBATIS -> new MyBatisDAOFactory();
            default -> throw new IllegalArgumentException("unsupported database type: " + type);
        };
    }
}
