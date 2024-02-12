package com.klaisapp.bookclub.repository;

import com.klaisapp.bookclub.model.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewerRepository extends JpaRepository<Reviewer, Integer> {

}
