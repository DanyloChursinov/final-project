package com.chursinov.beautysalon.repository.impl;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.exception.DataAccessException;
import com.chursinov.beautysalon.repository.CrudRepository;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public abstract class AbstractRepository<T, E> implements CrudRepository<T, E> {
    private static final Logger logger = Logger.getLogger(com.chursinov.beautysalon.repository.impl.AbstractRepository.class);
    private DataSource dataSource;

    protected AbstractRepository() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/beauty_salon");
        } catch (NamingException ex) {
            ex.printStackTrace();
            logger.error(Constants.Errors.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DataAccessException(Constants.Errors.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(Constants.Errors.ERR_CANNOT_OBTAIN_CONNECTION, e);
            throw new DataAccessException(Constants.Errors.ERR_CANNOT_OBTAIN_CONNECTION, e);
        }
        return connection;
    }

    @Override
    public long count(String sql) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int counter = 0;
        if (resultSet.next()) {
            counter = resultSet.getInt("count");
            close(resultSet, statement, connection);
            return counter;
        } else {
            close(resultSet, statement, connection);
            logger.warn("SQL statement { " + sql + " } is not valid");
        }
        return counter;
    }

    @Override
    public void deleteById(int id, String sql) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int count = 1;
            preparedStatement.setInt(count++, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot delete entity by id: " + e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public boolean existById(int id, String sql) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            int count = 1;
            preparedStatement.setInt(count++, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean isExist = resultSet.next();
            resultSet.close();
            return isExist;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    protected void connectionRollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error("Cannot rollback connection: " + e);
        }
    }

    protected void connectionSetAutoCommit(Connection connection, boolean value) {
        try {
            connection.setAutoCommit(value);
        } catch (SQLException e) {
            logger.error("Cannot set autoCommit in connection: " + e);
        }
    }

    protected void close(AutoCloseable... autoCloseables) {
        for (AutoCloseable autoCloseable : autoCloseables) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                logger.error("Cannot close Autoclosable:" + e);
            }
        }
    }
}
