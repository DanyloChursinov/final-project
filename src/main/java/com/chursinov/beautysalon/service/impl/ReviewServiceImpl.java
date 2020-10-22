package com.chursinov.beautysalon.service.impl;

import com.chursinov.beautysalon.entity.review.Review;
import com.chursinov.beautysalon.repository.ReviewRepository;
import com.chursinov.beautysalon.service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    ReviewRepository repository;

    public ReviewServiceImpl(ReviewRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Review> getAllReviews() {
        return repository.getAllReviews();
    }

    @Override
    public List<String> getAllMastersName() {
        return repository.getAllMastersName();
    }

    @Override
    public void AddReview(String message, int evaluation, int masterId, String userName) {
        repository.AddReview(message, evaluation, masterId, userName);
    }

    @Override
    public int getMasterNameById(String masterName) {
        return repository.getMasterNameById(masterName);
    }
}
