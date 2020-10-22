package com.chursinov.beautysalon.repository.impl;

import com.chursinov.beautysalon.constants.Constants;
import com.chursinov.beautysalon.exception.DataAccessException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public abstract class AbstractRepository<T, E> {
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

    protected void connectionSetAutoCommit(Connection connection, boolean value) {
        try {
            connection.setAutoCommit(value);
        } catch (SQLException e) {
            logger.error("Cannot set autoCommit in connection: " + e);
        }
    }

    protected static void close(ResultSet resultSet) {
        try {
            resultSet.close();
        } catch (SQLException e) {
            logger.error(Constants.Errors.ERR_CANNOT_CLOSE_RESULT_SET);
            throw new DataAccessException(Constants.Errors.ERR_CANNOT_CLOSE_RESULT_SET);
        }
    }

    protected static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error(Constants.Errors.ERR_CANNOT_CLOSE_CONNECTION);
            throw new DataAccessException(Constants.Errors.ERR_CANNOT_CLOSE_CONNECTION);
        }
    }

    protected static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error(Constants.Errors.ERR_CANNOT_CLOSE_STATEMENT);
            throw new DataAccessException(Constants.Errors.ERR_CANNOT_CLOSE_STATEMENT);
        }
    }

    protected static void rollback(Connection connection, Throwable t) {
        try {
            if (connection != null) {
                connection.rollback();
                close(connection);
            }
        } catch (SQLException e) {
            logger.error(Constants.Errors.ERR_CANNOT_ROLLBACK_CONNECTION, t);
            throw new DataAccessException(Constants.Errors.ERR_CANNOT_ROLLBACK_CONNECTION, t);
        }
    }
}
