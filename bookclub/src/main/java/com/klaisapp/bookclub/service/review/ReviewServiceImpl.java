package com.klaisapp.bookclub.service.review;

import com.klaisapp.bookclub.common.messages.Message;
import com.klaisapp.bookclub.common.messages.MessageConstants;
import com.klaisapp.bookclub.exception.CustomApplicationException;
import com.klaisapp.bookclub.model.Review;
import com.klaisapp.bookclub.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return reviewRepository.findById(theId)
                .orElseThrow(() -> new CustomApplicationException(Message.error(MessageConstants.ENTITY_NOT_FOUND)));
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
