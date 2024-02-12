package com.klaisapp.bookclub.service.reviewer;

import com.klaisapp.bookclub.model.Reviewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
@Service
public class ReviewerControllerService {

    private final ReviewerService reviewerService;

    @Autowired
    public ReviewerControllerService(ReviewerService reviewerService) {
        this.reviewerService = reviewerService;
    }

    public void addAttributesToReviewersList(Model model) {
        model.addAttribute("reviewers", reviewerService.findAll());
    }

    public void addAttributesToAddForm(Model model) {
        model.addAttribute("reviewer", new Reviewer());
    }

    public void addAttributesToUpdateForm(int theId, Model model) {
        model.addAttribute("reviewer", reviewerService.findById(theId));
    }

    public void saveReviewer(Reviewer theReviewer) {
        theReviewer.addReview(null);
        reviewerService.save(theReviewer);
    }

    public void deleteReviewer(int theId) {
        reviewerService.deleteById(theId);
    }
}
