package com.solvd.farm.dao.impl.mysql;

import com.solvd.farm.dao.interfaces.IPurchasableDAO;

import com.solvd.farm.model.Product;
import com.solvd.farm.model.Purchasable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class PurchasableDAO extends BaseDAO implements IPurchasableDAO {
    public static final Logger LOGGER = LogManager.getLogger(PurchasableDAO.class);

    //-- Insert purchasable (name, quantity, price_per_unit, Farms_id)
    private final static String INSERT_PURCHASABLE_SQL = "CALL InsertPurchasable(?,?,?,?);";

    private final static String FIND_PURCHASABLE_SQL = "SELECT Pble.*, Cble.name, Cble.quantity, Cble.Farms_id, Cble.id AS Countable_id, C.abbreviation as currency " +
            "FROM Purchasable Pble " +
            "INNER JOIN Countable Cble ON Pble.Countable_id = Cble.id " +
            "LEFT JOIN Currency C ON Pble.Currency_id = C.id " +
            "WHERE Pble.id=?;";

    private final static String ALL_PURCHASABLE_SQL = "SELECT Pble.*, Cble.name, Cble.quantity, Cble.Farms_id, Cble.id AS Countable_id, C.abbreviation as currency " +
            "FROM Purchasable Pble " +
            "INNER JOIN Countable Cble ON Pble.Countable_id = Cble.id " +
            "LEFT JOIN Currency C ON Pble.Currency_id = C.id;";


//    private final static String LAST_PURCHASABLE_SQL = "SELECT Cble.* " +
//            "FROM Purchasable Cble " +
//            "WHERE Cble.id=LAST_INSERT_ID();";

    private final static String UPDATE_PURCHASABLE_SQL = "UPDATE Purchasable Pble " +
            "LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id " +
            "LEFT JOIN Currency C ON Pble.Currency_id = C.id " +
            "SET Cble.id=?, Cble.name = ?, Cble.quantity = ?, Cble.Farms_id = ?, " +
            "Pble.price_per_unit = ?, Pble.Countable_id = ?, Pble.Currency_id = ? " +
            "WHERE Pble.id=?;";


    @Override
    public void save(Purchasable purchasable) {
        Connection connection = null;
        CallableStatement cs1 = null;
        int countableId = 0;
        int purchasableId = 0;

        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            cs1 = connection.prepareCall(INSERT_PURCHASABLE_SQL);
            cs1.setString(1, purchasable.getName());
            cs1.setFloat(2, purchasable.getQuantity());
            cs1.setFloat(3, purchasable.getPrice());
            cs1.setInt(4, purchasable.getFarmId());

            //Result set
            boolean hasResults = cs1.execute();

            if (hasResults) {
                try (ResultSet rs = cs1.getResultSet()) {

                    rs.next();
                    countableId = rs.getInt("countable_id");
                    purchasableId = rs.getInt("purchasable_id");

                    purchasable.setCountableId(countableId);
                    purchasable.setPurchasableId(purchasableId);

                } catch (Exception e) {
                }
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
    public Purchasable getPurchasableById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            ps = connection.prepareStatement(FIND_PURCHASABLE_SQL);
            ps.setInt(1, id);

            //Result set
            rs = ps.executeQuery();
            rs.next();

            return purchasableFromRS(rs);

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

    private Purchasable purchasableFromRS(ResultSet rs) throws SQLException {

        int countableId = rs.getInt("Countable_id");
        String name = rs.getString("name");
        float quantity = rs.getFloat("quantity");
        int farmId = rs.getInt("Farms_id");

        int purchasableId = rs.getInt("id");
        float pricePerUnit = rs.getFloat("price_per_unit");
        String currencyAbbreviation = rs.getString("currency");

        Product product = new Product();
        product.setCountable(countableId, name, quantity, farmId);
        product.setPurchasable(purchasableId, pricePerUnit, currencyAbbreviation);

        return product;
    }

    public ArrayList<Purchasable> purchasableList() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Purchasable> purchasableList = new ArrayList<>();
        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            ps = connection.prepareStatement(ALL_PURCHASABLE_SQL);

            //Result set
            rs = ps.executeQuery();

            while (rs.next()) {
                purchasableList.add(purchasableFromRS(rs));
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
        return purchasableList;
    }

    public void update(Purchasable purchasable) {
        Connection connection = null;
        CallableStatement cs1 = null;

        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            cs1 = connection.prepareCall(UPDATE_PURCHASABLE_SQL);
            cs1.setInt(1, purchasable.getCountableId());
            cs1.setString(2, purchasable.getName());
            cs1.setFloat(3, purchasable.getQuantity());
            cs1.setInt(4, purchasable.getFarmId());

            cs1.setFloat(5, purchasable.getPrice());
            cs1.setInt(6, purchasable.getCountableId());
            cs1.setInt(7, 1);
            cs1.setInt(8, purchasable.getPurchasableId());

            //Result set
            int rowsAffected1 = cs1.executeUpdate();

            if (rowsAffected1 > 0) {
                LOGGER.debug("Purchasable updated to DB successfully");
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

}

