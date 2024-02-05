package com.klaisapp.bookclub.controller.entity;

import com.klaisapp.bookclub.model.Review;
import com.klaisapp.bookclub.service.controller.model.ReviewControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewControllerService reviewControllerService;

    @Autowired
    public ReviewController(ReviewControllerService reviewControllerService) {
        this.reviewControllerService = reviewControllerService;
    }

    @GetMapping("/list")
    public String listReviews(Model model) {
        reviewControllerService.addAttributesToReviewsList(model);
        return "review/review-list";
    }

    @GetMapping("/addForm")
    public String showFormForAdd(Model model) {
        reviewControllerService.addAttributesToAddForm(model);
        return "review/review-form";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("reviewId") int theId, Model model) {
        reviewControllerService.addAttributesToUpdateForm(theId, model);
        return "review/review-update-form";
    }

    @PostMapping("/save")
    public String saveReview(@ModelAttribute("review") Review theReview,
                             @RequestParam(name = "bookId") int bookId) {
        reviewControllerService.saveReview(theReview, bookId);
        return "redirect:/reviews/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("reviewId") int theId) {
        reviewControllerService.deleteReview(theId);
        return "redirect:/reviews/list";
    }

    @GetMapping("/listByReviewer")
    public String listReviewerWork(@RequestParam("reviewerId") int theId, Model model) {
        reviewControllerService.addAttributesToListReviewsByReviewer(theId, model);
        return "review/reviewer-based-list";
    }
}
