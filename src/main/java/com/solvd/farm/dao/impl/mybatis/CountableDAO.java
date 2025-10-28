package com.solvd.farm.dao.impl.mybatis;


import com.solvd.farm.dao.interfaces.ICountableDAO;
import com.solvd.farm.model.Countable;
import com.solvd.farm.model.Product;
import com.solvd.farm.util.MybatisSessionHolder;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class CountableDAO implements ICountableDAO {
    public static final Logger LOGGER = LogManager.getLogger(CountableDAO.class);

    @Override
    public void save(Countable countable) {

        try (SqlSession session = MybatisSessionHolder.getSession().openSession(true)) {
            ICountableDAO countableDAO = session.getMapper(ICountableDAO.class);
            countableDAO.save(countable);
        }

    }

    @Override
    public Countable getCountableById(int id) {
        return null;
    }

    /* It returns a default product, but with the fields of a Countable
     */
    private Countable countableFromRS(ResultSet rs) throws SQLException {
        return null;

    }

    public ArrayList<Countable> countableList() {
        return null;
    }

    public void update(int id, String name, float quantity, int farmId) {

    }
}
