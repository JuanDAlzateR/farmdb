package com.solvd.farm.dao.impl.mysql;

import com.solvd.farm.dao.interfaces.IProductDAO;
import com.solvd.farm.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO extends BaseDAO implements IProductDAO {
    public static final Logger LOGGER = LogManager.getLogger(ProductDAO.class);
    //-- Insert purchasable (name, quantity, price_per_unit, Farms_id)
    private final static String INSERT_PURCHASABLE_SQL="CALL InsertPurchasable(?,?,?,?);";
    //-- Insert product (sell_price, rotten_percentage, rot_per_day)
    private final static String INSERT_PRODUCT_SQL="CALL InsertProduct(?,?,?);";

    private final static String FIND_PRODUCT_SQL ="SELECT P.*, Cble.name, Cble.quantity, Cble.Farms_id, Pble.price_per_unit, Pble.price_per_unit, C.abbreviation as currency "+
        "FROM Products P "+
        "LEFT JOIN Purchasable Pble ON P.Purchasable_id = Pble.id "+
        "LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id "+
        "LEFT JOIN Currency C ON Pble.Currency_id = C.id "+
        "WHERE P.id=?;";

    private final static String ALL_PRODUCT_SQL ="SELECT P.*, Cble.name, Cble.quantity, Cble.Farms_id, Pble.price_per_unit, Pble.price_per_unit, C.abbreviation as currency "+
            "FROM Products P "+
            "LEFT JOIN Purchasable Pble ON P.Purchasable_id = Pble.id "+
            "LEFT JOIN Countable Cble ON Pble.Countable_id = Cble.id "+
            "LEFT JOIN Currency C ON Pble.Currency_id = C.id;";


    @Override
    public void save(Product product){
        Connection connection=null;
        CallableStatement cs1 =null;
        CallableStatement cs2 =null;

        try{
            //get connection
            connection=super.getConnection();
            //prepare statement
            cs1 = connection.prepareCall(INSERT_PURCHASABLE_SQL);
            cs1.setString(1, product.getName());
            cs1.setFloat(2, product.getQuantity());
            cs1.setFloat(3, product.getPrice());
            cs1.setFloat(4, product.getFarmId());

            cs2 = connection.prepareCall(INSERT_PRODUCT_SQL);
            cs2.setFloat(1, product.getSellPrice());
            cs2.setFloat(2, product.getRottenPercentage());
            cs2.setFloat(3, product.getRottenPerDay());

            //Result set
            int rowsAffected1=cs1.executeUpdate();
            int rowsAffected2=cs2.executeUpdate();

            if((rowsAffected1>0) && (rowsAffected2>0)){
                LOGGER.debug("Product added to DB successfully");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try{cs1.close();
                cs2.close();
            }catch (SQLException e){}
            releaseConnection(connection);

        }

    }

//    @Override
//    public void save(Product product){
//        Connection connection=null;
//        PreparedStatement ps=null;
//
//        try{
//            //get connection
//            connection=super.getConnection();
//            //prepare statement
//            ps= connection.prepareStatement(saveSql);
//            setProductStatement(product,ps);
//            //Result set
//            int rowsAffected=ps.executeUpdate();
//            if(rowsAffected>0){
//                LOGGER.debug("Product added to DB successfully");
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }finally {
//            try{ps.close();}
//            catch (SQLException e){}
//            releaseConnection(connection);
//
//        }
//
//    }

    @Override
    public Product getProductById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            ps = connection.prepareStatement(FIND_PRODUCT_SQL);
            ps.setInt(1,id);

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
            product.setCountable(name,quantity,farmId);

            return product;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try{
                ps.close();
                rs.close();
            }catch (SQLException e){}

            releaseConnection(connection);

        }
    }

    public ArrayList<Product> productList() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Product> productList=new ArrayList<>();
        try {
            //get connection
            connection = super.getConnection();
            //prepare statement
            ps = connection.prepareStatement(ALL_PRODUCT_SQL);

            //Result set
            rs = ps.executeQuery();

            while(rs.next()) {
                String name = rs.getString("name");
                float quantity = rs.getFloat("quantity");
                int farmId = rs.getInt("Farms_id");
                float pricePerUnit = rs.getFloat("price_per_unit");
                float rottenPercentage = rs.getFloat("rotten_percentage");
                float rotPerDay = rs.getFloat("rot_per_day");
                float sellPrice = rs.getFloat("sell_price");
                String abbreviation = rs.getString("currency");

                Product product = new Product(sellPrice, rottenPercentage, rotPerDay);
                product.setPurchasable(pricePerUnit, abbreviation);
                product.setCountable(name, quantity, farmId);

                productList.add(product);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try{
                ps.close();
                rs.close();
            }catch (SQLException e){}
            releaseConnection(connection);

        }
        return productList;
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
