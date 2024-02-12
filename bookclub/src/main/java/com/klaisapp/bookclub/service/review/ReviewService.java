package com.klaisapp.bookclub.service.review;

import com.klaisapp.bookclub.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findAll();

    Review findById(int theId);

    Review save(Review review);

    void deleteById(int theId);

}
