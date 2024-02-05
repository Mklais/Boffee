package com.klaisapp.bookclub.service.model.reviewer;

import com.klaisapp.bookclub.model.Reviewer;
import com.klaisapp.bookclub.repository.model.ReviewerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewerServiceImpl implements ReviewerService {

    private final ReviewerRepository reviewerRepository;

    @Autowired
    public ReviewerServiceImpl(ReviewerRepository reviewerRepository) {
        this.reviewerRepository = reviewerRepository;
    }

    @Override
    public List<Reviewer> findAll() {
        return reviewerRepository.findAll();
    }

    @Override
    public Reviewer findById(int theId) {
        Optional<Reviewer> result = reviewerRepository.findById(theId);

        return result.orElseThrow(() -> new RuntimeException("Did not find reviewer with id: " + theId));
    }

    @Override
    @Transactional
    public Reviewer save(Reviewer review) {
        return reviewerRepository.save(review);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        reviewerRepository.deleteById(theId);
    }
}
