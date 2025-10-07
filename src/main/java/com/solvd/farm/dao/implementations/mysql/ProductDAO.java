package com.solvd.farm.dao.implementations.mysql;

import com.solvd.farm.dao.interfaces.IProductDAO;
import com.solvd.farm.model.Farm;
import com.solvd.farm.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO extends BaseDAO implements IProductDAO {
    public static final Logger LOGGER = LogManager.getLogger(ProductDAO.class);
    //-- Insert purchasable (name, quantity, price_per_unit, Farms_id)
    //-- Insert product (sell_price, rotten_percentage, rot_per_day)
    private final static String saveSql="CALL InsertPurchasable(\"?\",?,?,?);\n" +
        "CALL InsertProduct(?,?,?);";

    private final static String findProductSql="SELECT P.*, Cble.name, Cble.quantity, Cble.Farms_id, Pble.price_per_unit, Pble.price_per_unit, C.abreviation as currency "+
        "FROM Products P "+
        "LEFT JOIN Purchasable Pble ON P.Purchasable_id = Pble.id "+
        "LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id "+
        "LEFT JOIN Currency C ON Pble.Currency_id = C.id;";

    @Override
    public void save(Product product){
        Connection connection=null;
        PreparedStatement ps=null;

        try{
            //get connection
            connection=super.getConnection();
            //prepare statement
            ps= connection.prepareStatement(saveSql);
            setProductStatement(product,ps);
            //Result set
            int rowsAffected=ps.executeUpdate();
            if(rowsAffected>0){
                LOGGER.debug("Product added to DB successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            ps.close();
            releaseConnection(connection);

        }

    }

    @Override
    public Product getProductById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            ps = connection.prepareStatement(findProductSql);

            //Result set
            rs = ps.executeQuery();
            rs.next();

            String name = rs.getString("name");
            float quantity = rs.getFloat("quantity");
            int farmId=rs.getInt("Farms_id");
            float pricePerUnit= rs.getFloat("price_per_unit");
            float rottenPercentage=rs.getFloat("rotten_percentage");
            float rotPerDay=rs.getFloat("rot_per_day");
            float sellPrice=rs.getFloat("sell_price");
            String abbreviation =rs.getString("currency");

            Product product=new Product(sellPrice,rottenPercentage,rotPerDay);
            product.setPurchasable(pricePerUnit,abbreviation);


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ps.close();
            releaseConnection(connection);


            return null;
        }
    }

    private void setProductStatement(Product p, PreparedStatement ps) throws SQLException {
        //-- Insert purchasable (name, quantity, price_per_unit, Farms_id)
        //-- Insert product (sell_price, rotten_percentage, rot_per_day)
        ps.setString(1,p.getName());
        ps.setFloat(2,p.getQuantity());
        ps.setFloat(3,p.getPrice());
        ps.setInt(4,p.getFarmId());
        ps.setFloat(5,p.getSellPrice());
        ps.setFloat(6,p.getRottenPercentage());
        ps.setFloat(7,p.getRottenPerDay());

    }

    private void getProductStatement(Product p, PreparedStatement ps) throws SQLException {
        //-- Insert purchasable (name, quantity, price_per_unit, Farms_id)
        //-- Insert product (sell_price, rotten_percentage, rot_per_day)
        ps.setString(1,p.getName());
        ps.setFloat(2,p.getQuantity());
        ps.setFloat(3,p.getPrice());
        ps.setInt(4,p.getFarmId());
        ps.setFloat(5,p.getSellPrice());
        ps.setFloat(6,p.getRottenPercentage());
        ps.setFloat(7,p.getRottenPerDay());

    }

}
