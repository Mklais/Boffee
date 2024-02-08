package com.klaisapp.bookclub.service.controller.page;

import com.klaisapp.bookclub.common.messages.Message;
import com.klaisapp.bookclub.common.messages.MessageConstants;
import com.klaisapp.bookclub.model.Book;
import com.klaisapp.bookclub.model.Genre;
import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.model.user.UserProfile;
import com.klaisapp.bookclub.repository.model.BookRepository;
import com.klaisapp.bookclub.repository.model.GenreRepository;
import com.klaisapp.bookclub.repository.user.UserRepository;
import com.klaisapp.bookclub.common.messages.LiteraryQuotesGenerator;
import com.klaisapp.bookclub.service.user.user.UserService;
import com.klaisapp.bookclub.service.user.userprofile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class HomeControllerService {

    private final BookRepository bookRepository;

    private final GenreRepository genreRepository;

    private final UserRepository userRepository;

    private final UserService userService;

    private final LiteraryQuotesGenerator literaryQuotesGenerator;

    private final UserProfileService userProfileService;

    @Autowired
    public HomeControllerService(BookRepository bookRepository, GenreRepository genreRepository, UserRepository userRepository, UserService userService, LiteraryQuotesGenerator literaryQuotesGenerator, UserProfileService userProfileService) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.literaryQuotesGenerator = literaryQuotesGenerator;
        this.userProfileService = userProfileService;
    }

    public void addFormattedDate(Model model) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        String formattedDate = dateFormat.format(currentDate);
        model.addAttribute("formattedDate", formattedDate);
    }

    public void addDayOfWeek(Model model) {
        Date currentDate = new Date();
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE");
        String dayOfWeek = dayFormat.format(currentDate);
        model.addAttribute("dayOfWeek", dayOfWeek);
    }

    public void addUserData(Model model) {
        String username = userService.getLoggedInUsername();
        Optional<User> loggedInUser = (username != null) ? userRepository.findByUsername(username) : Optional.empty();
        model.addAttribute("loggedInUser", loggedInUser);
    }

    public void addPopularBook(Model model) {
        List<Book> books = bookRepository.findAll();

        Book popularBook = null;
        int maxReviewCount = 0;

        for (Book book : books) {
            int reviewCount = book.getReviews().size();

            if (reviewCount > maxReviewCount) {
                maxReviewCount = reviewCount;
                popularBook = book;
            }
        }
        model.addAttribute("popularBook", popularBook);
    }

    public void addRecentBook(Model model) {
        List<Book> books = bookRepository.findAll();
        Book recentBook = null;

        int recentCount = 0;

        for (Book book : books) {
            int bookId = book.getId();

            if (bookId > recentCount) {
                recentCount = bookId;
                recentBook = book;
            }
        }
        model.addAttribute("recentBook", recentBook);
    }

    public void addPopularGenre(Model model) {
        List<Genre> genres = genreRepository.findAll();

        Genre popularGenre = null;
        int maxBookCount = 0;

        for (Genre genre : genres) {
            int bookCountInGenre = genre.getBooks().size();

            if (bookCountInGenre > maxBookCount) {
                maxBookCount = bookCountInGenre;
                popularGenre = genre;
            }
        }
        model.addAttribute("popularGenre", popularGenre);
    }

    public void addFeelingCard(Model model) {
        List<Genre> genres = genreRepository.findAll();
        model.addAttribute("genres", genres);
    }

    /**
     * Greets a user that **is not** a logged-in user
     * @param model render
     */
    public void addGreetingCard(Model model) {
        List<User> users = userRepository.findAll();
        String loggedInUsername = userService.getLoggedInUsername();

        List<User> activeUsers = users.stream()
                .filter(user -> user.getActive() == 1)
                .collect(Collectors.toList());

        if (activeUsers.size() <= 1) {
            Message.info(MessageConstants.USER_TOO_FEW_REGISTERED);
            return;
        }

        User greetUser;
        do {
            int randomIndex = new Random().nextInt(activeUsers.size());
            greetUser = activeUsers.get(randomIndex);
        } while (greetUser.getUsername().equals(loggedInUsername));

        model.addAttribute("greetUser", greetUser);

        int profileIdOfUser = greetUser.getUserProfile().getProfileId();
        UserProfile userProfile = userProfileService.findById(profileIdOfUser);

        model.addAttribute("userProfile", userProfile);
    }

    public void addLiteraryQuote(Model model) {
        model.addAttribute("quote", literaryQuotesGenerator.getRandomQuote());
    }
}
