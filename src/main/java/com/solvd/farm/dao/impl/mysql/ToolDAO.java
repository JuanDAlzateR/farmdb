package com.solvd.farm.dao.impl.mysql;

import com.solvd.farm.dao.interfaces.IToolDAO;
import com.solvd.farm.model.Product;
import com.solvd.farm.model.Tool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class ToolDAO extends BaseDAO implements IToolDAO {
    public static final Logger LOGGER = LogManager.getLogger(ToolDAO.class);

    //-- Insert purchasable (name, quantity, price_per_unit, Farms_id)
    private final static String INSERT_PURCHASABLE_SQL = "CALL InsertPurchasable(?,?,?,?);";

    //-- Insert tool (wear_and_tear_percentage, wear_and_tear_per_day)
    private final static String INSERT_TOOL_SQL = "CALL InsertTool(?,?);";

    private final static String FIND_TOOL_SQL = "SELECT T.*, Cble.name, Cble.quantity, Cble.Farms_id, Pble.price_per_unit, Pble.price_per_unit, C.abbreviation as currency " +
            "FROM Tools T " +
            "LEFT JOIN Purchasable Pble ON T.Purchasable_id = Pble.id " +
            "LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id " +
            "LEFT JOIN Currency C ON Pble.Currency_id = C.id " +
            "WHERE T.id=?;";

    private final static String ALL_TOOL_SQL = "SELECT T.*, Cble.name, Cble.quantity, Cble.Farms_id, Pble.price_per_unit, Pble.price_per_unit, C.abbreviation as currency " +
            "FROM Tools T " +
            "LEFT JOIN Purchasable Pble ON T.Purchasable_id = Pble.id " +
            "LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id " +
            "LEFT JOIN Currency C ON Pble.Currency_id = C.id;";


    @Override
    public void save(Tool tool) {
        Connection connection = null;
        CallableStatement cs1 = null;
        CallableStatement cs2 = null;

        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            cs1 = connection.prepareCall(INSERT_PURCHASABLE_SQL);
            cs1.setString(1, tool.getName());
            cs1.setFloat(2, tool.getQuantity());
            cs1.setFloat(3, tool.getPrice());
            cs1.setInt(4, tool.getFarmId());

            cs2 = connection.prepareCall(INSERT_TOOL_SQL);
            cs2.setFloat(1, tool.getWearAndTearPercentage());
            cs2.setFloat(2, tool.getWearAndTearPerDay());

            //Result set
            int rowsAffected1 = cs1.executeUpdate();
            int rowsAffected2 = cs2.executeUpdate();

            if ((rowsAffected1 > 0) && (rowsAffected2 > 0)) {
                LOGGER.debug("Tools added to DB successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                cs1.close();
                cs2.close();
            } catch (SQLException e) {
            }
            releaseConnection(connection);

        }

    }

    @Override
    public Tool getToolById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            ps = connection.prepareStatement(FIND_TOOL_SQL);
            ps.setInt(1, id);

            //Result set
            rs = ps.executeQuery();
            rs.next();

            return toolFromRS(rs);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
            }

            releaseConnection(connection);

        }
    }

    private Tool toolFromRS(ResultSet rs) throws SQLException {

        String name = rs.getString("name");
        float quantity = rs.getFloat("quantity");
        int farmId = rs.getInt("Farms_id");
        float pricePerUnit = rs.getFloat("price_per_unit");
        String abbreviation = rs.getString("currency");

        float wearAndTearPercentage = rs.getFloat("wear_and_tear_percentage");
        float wearAndTearPerDay = rs.getFloat("wear_and_tear_per_day");

        Tool tool = new Tool(wearAndTearPercentage, wearAndTearPerDay);
        tool.setPurchasable(pricePerUnit, abbreviation);
        tool.setCountable(name, quantity, farmId);

        return tool;

    }

    public ArrayList<Tool> toolList() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Tool> toolList = new ArrayList<>();
        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            ps = connection.prepareStatement(ALL_TOOL_SQL);

            //Result set
            rs = ps.executeQuery();

            while (rs.next()) {
                toolList.add(toolFromRS(rs));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
            }
            releaseConnection(connection);

        }
        return toolList;
    }

}