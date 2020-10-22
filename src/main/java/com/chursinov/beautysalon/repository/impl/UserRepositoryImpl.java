package com.chursinov.beautysalon.repository.impl;

import com.chursinov.beautysalon.constants.Query;

import com.chursinov.beautysalon.entity.user.Role;
import com.chursinov.beautysalon.entity.user.User;
import com.chursinov.beautysalon.exception.DataAccessException;
import com.chursinov.beautysalon.repository.UserRepository;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserRepositoryImpl extends AbstractRepository implements UserRepository {
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
            connectionSetAutoCommit(connection, true);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            rollback(connection, e);
            throw new DataAccessException("Cannot add user at date base!", e);
        } finally {
            close(connection);
        }
    }

    public List<User> GetUsersEmailForSendMessage (String date){
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_USERS_EMAIL_FOR_SEND_MESSAGE)) {
            int counter = 1;
            statement.setString(counter++, date);
            ResultSet resultSet = statement.executeQuery();
            return extractUserEmails(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }
    private List<User> extractUserEmails (ResultSet resultSet) throws SQLException{
        List<User> usersEmails = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setEmail(resultSet.getString("email"));
            usersEmails.add(user);
        }
        return usersEmails;
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
