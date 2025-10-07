package com.solvd.farm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConnectionPool {

    // --- DB configuration ---
    private static final String URL = "jdbc:mysql://localhost:3306/farmdb";
    private static final String USER = "farm_user";
    private static final String PASSWORD = "farm.0000";
    private static final int INITIAL_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    // -------------------------------------------------------------------

    // Singleton
    private static ConnectionPool instance = null;


    private List<Connection> availableConnections = new LinkedList<>();
    private List<Connection> usedConnections = new LinkedList<>();

    //Private constructor, to avoid external instance (Singleton)
    private ConnectionPool() {
        // Run static block in Driver class, just once, before create a connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Initialize pool
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            availableConnections.add(createConnection());
        }
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * Creates a returns a new JDBC connection
     */
    private Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error trying to create the data base connection.", e);
        }
    }

    /**
     * Returns a pool connection
     */
    public synchronized Connection getConnection() {
        // If there is an available connection, it returns it.
        if (!availableConnections.isEmpty()) {
            Connection connection = availableConnections.remove(0);
            usedConnections.add(connection);
            return connection;
        }

        // If there are no available connections, it creates a new one if the max isn't reached.
        if (usedConnections.size() < MAX_POOL_SIZE) {
            Connection connection = createConnection();
            usedConnections.add(connection);
            return connection;
        }

        // If the max it's reached, returns an exception
        throw new RuntimeException("Max limit of pool connections reached.");
    }

    public synchronized boolean releaseConnection(Connection connection) {
        if (connection == null) {
            return false;
        }

        if (usedConnections.remove(connection)) {
            availableConnections.add(connection);
            return true;
        }
        return false;
    }
}