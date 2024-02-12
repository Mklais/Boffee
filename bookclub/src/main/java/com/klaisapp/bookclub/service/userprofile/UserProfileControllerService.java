package com.klaisapp.bookclub.service.userprofile;

import com.klaisapp.bookclub.model.Author;
import com.klaisapp.bookclub.model.Book;
import com.klaisapp.bookclub.model.Genre;
import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.model.UserProfile;
import com.klaisapp.bookclub.service.author.AuthorService;
import com.klaisapp.bookclub.service.book.BookService;
import com.klaisapp.bookclub.service.genre.GenreService;
import com.klaisapp.bookclub.service.user.UserService;
import com.klaisapp.bookclub.service.userprofile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class UserProfileControllerService {

    private final UserProfileService userProfileService;

    private final UserService userService;

    private final BookService bookService;

    private final GenreService genreService;

    private final AuthorService authorService;


    @Autowired
    public UserProfileControllerService(UserProfileService userProfileService, UserService userService, BookService bookService, GenreService genreService, AuthorService authorService) {
        this.userProfileService = userProfileService;
        this.userService = userService;
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    public void addAttributesToEditForm(int userId, Model model) {
        User theUser = userService.findById(userId);
        UserProfile theProfile = theUser.getUserProfile();

        addUserAttributes(theUser, theProfile, model);

        addAssociatedEntityAttributes(model);
    }

    private void addUserAttributes(User theUser, UserProfile theProfile, Model model) {
        model.addAttribute("theUser", theUser);
        model.addAttribute("theProfile", theProfile);

        List<Book> readBooks = theProfile.getReadBooks();
        model.addAttribute("readBooks", readBooks);

        List<Book> recommendedBooks = theProfile.getRecommendedBooks();
        model.addAttribute("recommendedBooks", recommendedBooks);

        addBirthdateToModel(theProfile, model);
    }

    private void addBirthdateToModel(UserProfile theProfile, Model model) {
        LocalDate birthdate = theProfile.getBirthdate();

        if (birthdate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Date.from(birthdate.atStartOfDay(ZoneId.systemDefault()).toInstant()));

            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH) + 1;
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            model.addAttribute("birthYear", year);
            model.addAttribute("birthMonth", month);
            model.addAttribute("birthDay", day);
        }
    }

    private void addAssociatedEntityAttributes(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);

        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);

        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
    }

    public void saveUserProfile(UserProfile userProfile) {
        userProfileService.save(userProfile);
    }

    public String getUsername(UserProfile userProfile) {
        int profileId = userProfile.getProfileId();
        User user = userService.findByUserProfile_ProfileId(profileId);
        return user.getUsername();
    }
}
