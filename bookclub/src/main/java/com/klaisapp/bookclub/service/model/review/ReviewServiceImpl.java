package com.klaisapp.bookclub.service.model.review;

import com.klaisapp.bookclub.model.Review;
import com.klaisapp.bookclub.repository.model.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(int theId) {
        Optional<Review> result = reviewRepository.findById(theId);

        return result.orElseThrow(() -> new RuntimeException("Did not find review with id: " + theId));
    }

    @Override
    @Transactional
    public Review save(Review theReview) {
        return reviewRepository.save(theReview);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        reviewRepository.deleteById(theId);
    }
}
