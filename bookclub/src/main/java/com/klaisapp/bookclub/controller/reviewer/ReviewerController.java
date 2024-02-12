package com.klaisapp.bookclub.controller.reviewer;

import com.klaisapp.bookclub.model.Reviewer;
import com.klaisapp.bookclub.service.reviewer.ReviewerControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviewers")
public class ReviewerController {

    private final ReviewerControllerService reviewerControllerService;

    @Autowired
    public ReviewerController(ReviewerControllerService reviewerControllerService) {
        this.reviewerControllerService = reviewerControllerService;
    }

    @GetMapping("/list")
    public String listReviewers(Model model) {
        reviewerControllerService.addAttributesToReviewersList(model);
        return "reviewer/list";
    }

    @GetMapping("/addForm")
    public String addReviewer(Model model) {
        reviewerControllerService.addAttributesToAddForm(model);
        return "reviewer/reviewer-form";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("reviewerId") int theId, Model model) {
        reviewerControllerService.addAttributesToUpdateForm(theId, model);
        return "reviewer/reviewer-form";
    }

    @PostMapping("/save")
    public String saveReviewer(@ModelAttribute("reviewer") Reviewer theReviewer) {
        reviewerControllerService.saveReviewer(theReviewer);
        return "redirect:/reviewers/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("reviewerId") int theId) {
        reviewerControllerService.deleteReviewer(theId);
        return "redirect:/reviewers/list";
    }
}
