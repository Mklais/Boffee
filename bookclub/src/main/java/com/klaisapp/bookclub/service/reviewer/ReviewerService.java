package com.klaisapp.bookclub.service.reviewer;

import com.klaisapp.bookclub.model.Reviewer;

import java.util.List;

public interface ReviewerService {

    List<Reviewer> findAll();

    Reviewer findById(int theId);

    Reviewer save(Reviewer review);

    void deleteById(int theId);

}
