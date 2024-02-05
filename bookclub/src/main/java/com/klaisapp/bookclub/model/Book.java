package com.klaisapp.bookclub.model;

import com.klaisapp.bookclub.model.user.UserProfile;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "user_read_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_profile_id"))
    private List<UserProfile> readers;

    @ManyToMany
    @JoinTable(
            name = "user_recommended_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_profile_id"))
    private List<UserProfile> recommenders;

    @OneToMany(mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    public Book() {

    }

    public Book(String title, Author author, List<Genre> genres, List<UserProfile> readers, List<Review> reviews) {
        this.title = title;
        this.author = author;
        this.genres = genres;
        this.readers = readers;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<UserProfile> getReaders() {
        return readers;
    }

    public void setReaders(List<UserProfile> readers) {
        this.readers = readers;
    }

    // Convenience methods for reviews & genres
    public void addReview(Review tempReview) {

        if (reviews == null) {
            reviews = new ArrayList<>();
        }
            reviews.add(tempReview);
    }

    public void addGenre(Genre tempGenre) {
        if (genres == null) {
            genres = new ArrayList<>();
        }
            genres.add(tempGenre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", genres=" + genres +
                ", readers=" + readers +
                ", reviews=" + reviews +
                '}';
    }
}
