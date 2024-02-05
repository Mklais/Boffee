package com.klaisapp.bookclub.service.controller.model;

import com.klaisapp.bookclub.model.Reviewer;
import com.klaisapp.bookclub.service.model.reviewer.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class ReviewerControllerService {

    private final ReviewerService reviewerService;

    @Autowired
    public ReviewerControllerService(ReviewerService reviewerService) {
        this.reviewerService = reviewerService;
    }

    public void addAttributesToReviewersList(Model model) {
        List<Reviewer> theReviewers = reviewerService.findAll();
        model.addAttribute("reviewers", theReviewers);
    }

    public void addAttributesToAddForm(Model model) {
        Reviewer theReviewer = new Reviewer();
        model.addAttribute("reviewer", theReviewer);
    }

    public void addAttributesToUpdateForm(int theId, Model model) {
        Reviewer theReviewer = reviewerService.findById(theId);
        model.addAttribute("reviewer", theReviewer);
    }

    public void saveReviewer(Reviewer theReviewer) {
        theReviewer.addReview(null);
        reviewerService.save(theReviewer);
    }

    public void deleteReviewer(int theId) {
        reviewerService.deleteById(theId);
    }
}
