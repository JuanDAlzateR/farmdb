package com.solvd.farm.dao.impl.mybatis;

import com.solvd.farm.dao.interfaces.IProductDAO;
import com.solvd.farm.model.Product;
import com.solvd.farm.util.MybatisSessionHolder;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class ProductDAO implements IProductDAO {
    public static final Logger LOGGER = LogManager.getLogger(ProductDAO.class);

    @Override
    public void save(Product product) {

        try (SqlSession session = MybatisSessionHolder.getSession().openSession(true)) {
            IProductDAO productDAO = session.getMapper(IProductDAO.class);
            productDAO.save(product);
        }

    }

    @Override
    public Product getProductById(int id) {
        try (SqlSession session = MybatisSessionHolder.getSession().openSession(true)) {
            IProductDAO productDAO = session.getMapper(IProductDAO.class);
            return productDAO.getProductById(id);
        }
    }

    @Override
    public ArrayList<Product> productList() {
        try (SqlSession session = MybatisSessionHolder.getSession().openSession(true)) {
            IProductDAO productDAO = session.getMapper(IProductDAO.class);
            return productDAO.productList();
        }
    }

}
