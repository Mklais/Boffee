package com.klaisapp.bookclub.model.user;

import com.klaisapp.bookclub.model.Author;
import com.klaisapp.bookclub.model.Book;
import com.klaisapp.bookclub.model.Genre;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private int profileId;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "interests")
    private String interests;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @ManyToMany
    @JoinTable(
            name = "user_read_books",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> readBooks;

    @ManyToMany
    @JoinTable(
            name = "user_recommended_books",
            joinColumns = @JoinColumn(name = "user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> recommendedBooks;

    @ManyToOne
    @JoinColumn(name = "favorite_genre_id")
    private Genre favoriteGenre;

    @ManyToOne
    @JoinColumn(name = "favorite_author_id")
    private Author favoriteAuthor;

    @OneToOne(mappedBy = "userProfile", fetch = FetchType.LAZY)
    private User user;

    public UserProfile() {

    }

    public UserProfile(String hobbies,
                       String interests,
                       LocalDate birthdate,
                       List<Book> readBooks,
                       List<Book> recommendedBooks,
                       Genre favoriteGenre,
                       Author favoriteAuthor,
                       User user) {
        this.hobbies = hobbies;
        this.interests = interests;
        this.birthdate = birthdate;
        this.readBooks = readBooks;
        this.recommendedBooks = recommendedBooks;
        this.favoriteGenre = favoriteGenre;
        this.favoriteAuthor = favoriteAuthor;
        this.user = user;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public List<Book> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(List<Book> readBooks) {
        this.readBooks = readBooks;
    }

    public List<Book> getRecommendedBooks() {
        return recommendedBooks;
    }

    public void setRecommendedBooks(List<Book> recommendedBooks) {
        this.recommendedBooks = recommendedBooks;
    }

    public Genre getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(Genre favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public Author getFavoriteAuthor() {
        return favoriteAuthor;
    }

    public void setFavoriteAuthor(Author favoriteAuthor) {
        this.favoriteAuthor = favoriteAuthor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean hasReadBooks() {
        return readBooks != null && !readBooks.isEmpty();
    }

    public boolean hasRecommendedBooks() {
        return recommendedBooks != null && !recommendedBooks.isEmpty();
    }

    public String getProfileSummary() {
        String favoriteGenreName = (favoriteGenre != null) ? favoriteGenre.getName() : "None";
        String favoriteAuthorName = (favoriteAuthor != null) ?
                favoriteAuthor.getFirstName() + " " + favoriteAuthor.getLastName() : "None";
        String userSummary = (user != null) ? "User{id=" + user.getUserId() + ", username='" + user.getUsername() + "'}" : "None";

        return "UserProfile{" +
                "profileId=" + profileId +
                ", hobbies='" + hobbies + '\'' +
                ", interests='" + interests + '\'' +
                ", birthdate=" + birthdate +
                ", favoriteGenre='" + favoriteGenreName + '\'' +
                ", favoriteAuthor='" + favoriteAuthorName + '\'' +
                ", user=" + userSummary +
                '}';
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "profileId=" + profileId +
                ", hobbies='" + hobbies + '\'' +
                ", interests='" + interests + '\'' +
                ", birthdate=" + birthdate +
                ", readBooks=" + readBooks +
                ", recommendedBooks=" + recommendedBooks +
                ", favoriteGenre=" + favoriteGenre +
                ", favoriteAuthor=" + favoriteAuthor +
                ", user=" + user +
                '}';
    }
}
