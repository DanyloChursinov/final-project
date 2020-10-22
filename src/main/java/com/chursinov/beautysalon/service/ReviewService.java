package com.chursinov.beautysalon.service;

import com.chursinov.beautysalon.entity.review.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews();
    List<String> getAllMastersName();
    void AddReview (String message, int evaluation, int masterId, String userName);
    int getMasterNameById(String masterName);
}
