package com.solvd.farm.dao.impl.mysql;

import com.solvd.farm.util.ConnectionPool;

import java.sql.Connection;

public abstract class BaseDAO {
    protected final ConnectionPool cp;

    protected BaseDAO() {
        this.cp = ConnectionPool.getInstance();
    }

    protected Connection getConnection() {
        return cp.getConnection();
    }

    protected void releaseConnection(Connection connection) {
        cp.releaseConnection(connection);
    }
}
