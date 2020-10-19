package com.chursinov.beautysalon.repository.impl;

import com.chursinov.beautysalon.constants.Query;
import com.chursinov.beautysalon.entity.Review;
import com.chursinov.beautysalon.exception.DataAccessException;
import com.chursinov.beautysalon.repository.ReviewRepository;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepositoryImpl extends AbstractRepository implements ReviewRepository {

    private static final Logger logger = Logger.getLogger(ProductRepositoryImpl.class);

    @Override
    public List<Review> getAllReviews() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Query.GET_ALL_REVIEWS);
            return extractReviewsFromResultSet(resultSet);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<String> getAllMastersName() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Query.GET_ALL_MASTERS_NAME);
            List<String> masters = new ArrayList<>();

            while (resultSet.next()){
                String master = resultSet.getString("name");
                master = Character.toUpperCase(master.charAt(0)) + master.substring(1);
                masters.add(master);
            }
            return masters;
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void AddReview(String message, int evaluation, int masterId, String userName) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.ADD_REVIEW)) {
            int counter = 1;
            statement.setString(counter++, message);
            statement.setInt(counter++, evaluation);
            statement.setInt(counter++, masterId);
            statement.setString(counter, userName);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public int getMasterNameById(String masterName) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.GET_MASTER_ID_BY_NAME)) {
            int counter = 1;
            masterName.toLowerCase();
            statement.setString(counter++, masterName);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("id");
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new DataAccessException(e.getMessage(), e);
        }
    }


    private List<Review> extractReviewsFromResultSet(ResultSet resultSet) throws SQLException {

        List<Review> reviews = new ArrayList<>();
        while (resultSet.next()) {
            String master = resultSet.getString("master");
            master = Character.toUpperCase(master.charAt(0)) + master.substring(1);
            String user = resultSet.getString("user_name");
            user = Character.toUpperCase(user.charAt(0)) + user.substring(1);

            Review review = new Review();
            review.setEvaluation(resultSet.getInt("evaluation"));
            review.setId(resultSet.getInt("id"));
            review.setMessage(resultSet.getString("message"));
            review.setUser(user);
            review.setMaster(master);

            reviews.add(review);
        }
        return reviews;
    }

}
