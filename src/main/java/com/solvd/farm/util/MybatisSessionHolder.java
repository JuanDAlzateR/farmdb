package com.solvd.farm.util;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

public class MybatisSessionHolder {
    public static final Logger LOGGER = LogManager.getLogger(MybatisSessionHolder.class);

    public static SqlSessionFactory getSession() {
        InputStream inputStream;
        try {
            inputStream= Resources.getResourceAsStream("mybatis-configuration.xml");
        } catch (IOException e) {
            LOGGER.info("Error loading mybatis config");
            throw new RuntimeException("Mybatis error: ", e);
        }
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory =builder.build(inputStream);
        return sessionFactory;
    }

}