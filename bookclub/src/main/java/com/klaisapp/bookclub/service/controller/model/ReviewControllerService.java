package com.klaisapp.bookclub.service.controller.model;

import com.klaisapp.bookclub.model.Book;
import com.klaisapp.bookclub.model.Review;
import com.klaisapp.bookclub.model.Reviewer;
import com.klaisapp.bookclub.service.model.book.BookService;
import com.klaisapp.bookclub.service.model.review.ReviewService;
import com.klaisapp.bookclub.service.model.reviewer.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
@Service
public class ReviewControllerService {

    private final ReviewService reviewService;

    private final ReviewerService reviewerService;

    private final BookService bookService;

    @Autowired
    public ReviewControllerService(ReviewService reviewService, ReviewerService reviewerService, BookService bookService) {
        this.reviewService = reviewService;
        this.reviewerService = reviewerService;
        this.bookService = bookService;
    }

    public void addAttributesToReviewsList(Model model) {
        model.addAttribute("reviews", reviewService.findAll());
        model.addAttribute("reviewers", reviewerService.findAll());
    }

    public void addAttributesToAddForm(Model model) {
        model.addAttribute("review", new Review());
        model.addAttribute("reviewers", reviewerService.findAll());
        model.addAttribute("books", bookService.findAll());
    }

    public void addAttributesToUpdateForm(int theId, Model model) {
        model.addAttribute("review", reviewService.findById(theId));
    }

    public void saveReview(Review theReview, int bookId) {
        Book selectedBook = bookService.findById(bookId);
        theReview.setBook(selectedBook);
        reviewService.save(theReview);
    }

    public void deleteReview(int theId) {
        reviewService.deleteById(theId);
    }

    public void addAttributesToListReviewsByReviewer(int theId, Model model) {
        Reviewer reviewer = reviewerService.findById(theId);
        model.addAttribute("reviewer", reviewer);
        model.addAttribute("reviews", reviewer.getReviews());

        model.addAttribute("reviewers", reviewerService.findAll());
    }
}
