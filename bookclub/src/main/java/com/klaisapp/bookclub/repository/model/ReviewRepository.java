package com.klaisapp.bookclub.repository.model;

import com.klaisapp.bookclub.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
