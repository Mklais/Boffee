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

import java.util.List;

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
        List<Review> reviews = reviewService.findAll();
        model.addAttribute("reviews", reviews);

        List<Reviewer> reviewers = reviewerService.findAll();
        model.addAttribute("reviewers", reviewers);
    }

    public void addAttributesToAddForm(Model model) {
        Review theReview = new Review();
        List<Reviewer> theReviewers = reviewerService.findAll();
        List<Book> theBooks = bookService.findAll();

        model.addAttribute("review", theReview);
        model.addAttribute("reviewers", theReviewers);
        model.addAttribute("books", theBooks);
    }

    public void addAttributesToUpdateForm(int theId, Model model) {
        Review theReview = reviewService.findById(theId);
        model.addAttribute("review", theReview);
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

        List<Reviewer> reviewers = reviewerService.findAll();
        model.addAttribute("reviewers", reviewers);

        List<Review> reviews = reviewer.getReviews();
        model.addAttribute("reviews", reviews);
    }
}
