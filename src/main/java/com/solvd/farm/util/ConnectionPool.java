package com.solvd.farm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ConnectionPool {

    // --- DB configuration ---
    private static final String URL = "jdbc:mysql://localhost:3306/farmdb";
    private static final String USER = "your_user";
    private static final String PASSWORD = "your_password";
    private static final int INITIAL_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    // -------------------------------------------------------------------

    // Singleton
    private static ConnectionPool instance = null;


    private List<Connection> availableConnections = new LinkedList<>();
    private List<Connection> usedConnections = new LinkedList<>();

    //Private constructor, to avoid extaernal instance (Singleton)
    private ConnectionPool() {
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
            throw new RuntimeException("Error al crear la conexión a la base de datos.", e);
        }
    }

    /**
     * Returns a pool connection
     */
    public synchronized Connection getConnection() {
        // If there is an avaiable connection, it returns it.
        if (!availableConnections.isEmpty()) {
            Connection connection = availableConnections.remove(0);
            usedConnections.add(connection);
            return connection;
        }

        // If there are not avaiable connections, it creates a new one if the max isn't reached.
        if (usedConnections.size() < MAX_POOL_SIZE) {
            Connection connection = createConnection();
            usedConnections.add(connection);
            return connection;
        }

        // If the max it's reached, returns an exception
        throw new RuntimeException("Límite máximo del pool de conexiones alcanzado.");
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