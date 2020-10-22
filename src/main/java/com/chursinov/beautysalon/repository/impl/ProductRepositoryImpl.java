package com.chursinov.beautysalon.repository.impl;

import com.chursinov.beautysalon.constants.Query;
import com.chursinov.beautysalon.entity.product.Product;
import com.chursinov.beautysalon.exception.DataAccessException;
import com.chursinov.beautysalon.repository.ProductRepository;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl extends AbstractRepository implements ProductRepository {

    private static final Logger logger = Logger.getLogger(ProductRepositoryImpl.class);

    @Override
    public List<Product> getAllProducts() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Query.GET_ALL_PRODUCTS);
            return extractProductFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    public BigDecimal getRatingByMaster(int master_id) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_RATING_FOR_MASTER)) {
            int counter = 1;
            statement.setInt(counter++, master_id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getBigDecimal("raiting");
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    @Override
    public List<Product> getProductsByMaster (String masterName){
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_PRODUCTS_BY_MASTER)) {
            int counter = 1;
            statement.setString(counter++, masterName.toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            return extractProductFromResultSet(resultSet);
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }

    }
    @Override
    public List<Product> getProductsByName (String productName){
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_PRODUCTS_BY_NAME)) {
            int counter = 1;
            statement.setString(counter++, productName.toLowerCase());
            ResultSet resultSet = statement.executeQuery();
            return extractProductFromResultSet(resultSet);
        }catch (SQLException e){
            logger.error(e.getMessage());
            throw new DataAccessException(e.getMessage(), e);
        }

    }



    private List<Product> extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            String master = resultSet.getString("master");
            String name = resultSet.getString("name");
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            master = Character.toUpperCase(master.charAt(0)) + master.substring(1);

            Product product = new Product();
            product.setId(resultSet.getInt("id"));
            product.setName(name);
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setDuration(resultSet.getInt("duration"));
            product.setMasterId(resultSet.getInt("master_id"));
            product.setMaster(master);
            product.setRaiting(getRatingByMaster(resultSet.getInt("master_id")));

            products.add(product);
        }
        return products;
    }
}
