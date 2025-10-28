package com.solvd.farm.dao.impl.mybatis;

import com.solvd.farm.dao.interfaces.ICountableDAO;
import com.solvd.farm.model.Countable;
import com.solvd.farm.util.MybatisSessionHolder;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
        try (SqlSession session = MybatisSessionHolder.getSession().openSession(true)) {
            ICountableDAO countableDAO = session.getMapper(ICountableDAO.class);
            return countableDAO.getCountableById(id);
        }
    }

    @Override
    public ArrayList<Countable> countableList() {
        try (SqlSession session = MybatisSessionHolder.getSession().openSession(true)) {
            ICountableDAO countableDAO = session.getMapper(ICountableDAO.class);
            return countableDAO.countableList();
        }
    }

    @Override
    public void update(int id, String name, float quantity, int farmId) {
        try (SqlSession session = MybatisSessionHolder.getSession().openSession(true)) {
            ICountableDAO countableDAO = session.getMapper(ICountableDAO.class);
            countableDAO.update(id, name, quantity, farmId);
        }
    }
}
