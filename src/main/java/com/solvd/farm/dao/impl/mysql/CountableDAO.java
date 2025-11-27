package com.solvd.farm.dao.impl.mysql;

import com.solvd.farm.dao.interfaces.ICountableDAO;
import com.solvd.farm.model.Countable;
import com.solvd.farm.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountableDAO extends BaseDAO implements ICountableDAO {
    public static final Logger LOGGER = LogManager.getLogger(CountableDAO.class);

    //-- Insert countable (name, quantity, Farms_id)
    private final static String INSERT_INTO_COUNTABLE_SQL = "INSERT INTO Countable (name, quantity, Farms_id) VALUES (?, ?, ?);";

    private final static String FIND_COUNTABLE_SQL = "SELECT Cble.* " +
            "FROM Countable Cble " +
            "WHERE Cble.id=?;";

    private final static String LAST_COUNTABLE_SQL = "SELECT Cble.* " +
            "FROM Countable Cble " +
            "WHERE Cble.id=LAST_INSERT_ID();";

    private final static String ALL_COUNTABLE_SQL = "SELECT * FROM Countable;";

    private final static String UPDATE_COUNTABLE_SQL = "UPDATE Countable " +
            "SET name = ?, quantity = ?, Farms_id = ? " +
            "WHERE id=?;";

    private final static String DELETE_COUNTABLE_SQL = "DELETE FROM Countable " +
            "WHERE id=?;";


    @Override
    public void save(Countable countable) {
        Connection connection = null;
        CallableStatement cs1 = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            ps = connection.prepareStatement(INSERT_INTO_COUNTABLE_SQL, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, countable.getName());
            ps.setFloat(2, countable.getQuantity());
            ps.setInt(3, countable.getFarmId());
            //Result set
            ps.executeUpdate();
            // Get Id
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int newId = rs.getInt(1);
                countable.setCountableId(newId);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }

            } catch (SQLException e) {
            }
            releaseConnection(connection);

        }

    }

    @Override
    public Countable getCountableById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            ps = connection.prepareStatement(FIND_COUNTABLE_SQL);
            ps.setInt(1, id);

            //Result set
            rs = ps.executeQuery();
            //rs.next(); //countableFromRSorNull moves the cursor.

            return countableFromRSorNull(rs);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
            }

            releaseConnection(connection);
        }
    }

    /* It returns a default product, but with the fields of a Countable
     */
    private Countable countableFromRS(ResultSet rs) throws SQLException {

        String name = rs.getString("name");
        float quantity = rs.getFloat("quantity");
        int farmId = rs.getInt("Farms_id");
        int id = rs.getInt("id");

        Product product = new Product();
        product.setCountable(id, name, quantity, farmId);

        return product;
    }

    public ArrayList<Countable> countableList() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Countable> countableList = new ArrayList<>();
        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            ps = connection.prepareStatement(ALL_COUNTABLE_SQL);

            //Result set
            rs = ps.executeQuery();

            while (rs.next()) {
                countableList.add(countableFromRS(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
            }
            releaseConnection(connection);

        }
        return countableList;
    }

    public void update(int id, String name, float quantity, int farmId) {
        Connection connection = null;
        CallableStatement cs1 = null;

        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            cs1 = connection.prepareCall(UPDATE_COUNTABLE_SQL);
            cs1.setString(1, name);
            cs1.setFloat(2, quantity);
            cs1.setInt(3, farmId);
            cs1.setInt(4, id);

            //Result set
            int rowsAffected1 = cs1.executeUpdate();

            if (rowsAffected1 > 0) {
                LOGGER.debug("Countable updated to DB successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                cs1.close();
            } catch (SQLException e) {
            }
            releaseConnection(connection);

        }

    }

    @Override
    public void update(Countable countable) {
        int id = countable.getCountableId();
        String name = countable.getName();
        float quantity = countable.getQuantity();
        int farmId = countable.getFarmId();
        update(id, name, quantity, farmId);
    }

    @Override
    public void delete(int id) {
        Connection connection = null;
        CallableStatement cs1 = null;

        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            cs1 = connection.prepareCall(DELETE_COUNTABLE_SQL);
            cs1.setInt(1, id);

            //Result set
            int rowsAffected1 = cs1.executeUpdate();

            if (rowsAffected1 > 0) {
                LOGGER.debug("Countable deleted from DB successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                cs1.close();
            } catch (SQLException e) {
            }
            releaseConnection(connection);

        }

    }


    /**
     * Verifies if the result set it's empty.
     * If it is not empty, moves the cursor to the first line.
     *
     * @param rs ResultSet
     * @return boolean
     */
    public boolean isResultSetEmpty(ResultSet rs) throws SQLException {
        if (rs == null) {
            return true;
        }

        // Tries to move the cursor to the first line.
        // If returns false, there are not lines in the rs.
        boolean hasNext = rs.next();

        if (!hasNext) {
            // the sr is empty
            return true;
        } else {
            // The rs has at least one line (cursor is allready on the first line)
            return false;
        }
    }

    /**
     * It returns a default product, but with the fields of a Countable.
     * If the result set is empty, it returns a "No match" countable.
     */
    private Countable countableFromRSorNull(ResultSet rs) throws SQLException {
        Boolean rsEmpty = isResultSetEmpty(rs);
        if (rsEmpty) {
            return null;
        } else {
            return countableFromRS(rs);
        }
    }


}
