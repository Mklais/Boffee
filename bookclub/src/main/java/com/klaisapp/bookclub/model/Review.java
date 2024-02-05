package com.klaisapp.bookclub.model;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Reviewer reviewer;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Review() {

    }

    public Review(String comment, Reviewer reviewer, Book book) {
        this.comment = comment;
        this.reviewer = reviewer;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", reviewer=" + reviewer +
                ", book=" + book +
                '}';
    }
}
