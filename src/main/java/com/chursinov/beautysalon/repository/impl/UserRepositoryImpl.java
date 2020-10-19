package com.chursinov.beautysalon.repository.impl;

import com.chursinov.beautysalon.constants.Query;
import com.chursinov.beautysalon.entity.Role;
import com.chursinov.beautysalon.entity.User;
import com.chursinov.beautysalon.exception.DataAccessException;
import com.chursinov.beautysalon.repository.UserRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepositoryImpl extends AbstractRepository<User, Integer> implements UserRepository {
    private static final Logger logger = Logger.getLogger(UserRepositoryImpl.class);


    @Override
    public User getUserByEmail(String email) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_USER_BY_EMAIL)) {
            int counter = 1;
            statement.setString(counter++, email);
            ResultSet resultSet = statement.executeQuery();
            return extractUserFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_USER_BY_EMAIL_AND_PASSWORD)) {
            int counter = 1;
            statement.setString(counter++, email);
            statement.setString(counter++, password);
            ResultSet resultSet = statement.executeQuery();
            return extractUserFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    @Override
    public void addUser(String name, String email, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try  {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(Query.ADD_USER);
            int counter = 1;
            statement.setString(counter++, name.toLowerCase());
            statement.setString(counter++, email);
            statement.setString(counter++, password);
            statement.setInt(counter++, Role.CLIENT.getId());
            statement.executeUpdate();
            // comit

        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            connectionRollback(connection);
            throw new DataAccessException(e.getMessage(), e);//TODO our message
        } finally {
            connectionSetAutoCommit(connection, true);
            close(resultSet, statement, connection);
        }
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            name = Character.toUpperCase(name.charAt(0)) + name.substring(1, name.length());
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(name);
            user.setEmail(resultSet.getString("email"));
            user.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
        }
        return user;
    }
}
