package com.klaisapp.bookclub.service.book;

import com.klaisapp.bookclub.model.Author;
import com.klaisapp.bookclub.model.Book;
import com.klaisapp.bookclub.service.author.AuthorService;
import com.klaisapp.bookclub.service.genre.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
@Service
public class BookControllerService {

    private final BookService bookService;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Autowired
    public BookControllerService(BookService bookService,
                                 AuthorService authorService,
                                 GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    public void addAttributesToBooksList(Model model) {
        model.addAttribute("books", bookService.findAll());
        model.addAttribute("authors", authorService.findAll());
    }

    public void addAttributesToBookAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
    }

    public void addAttributesToEditForm(int theId, Model model) {
        model.addAttribute("book", bookService.findById(theId));
        model.addAttribute("authors", authorService.findAll());
        model.addAttribute("genres", genreService.findAll());
    }

    /**
     * Saves a book, ensuring data consistency for reviews.
     * If the book is a new entity (ID: 0) - method sets reviews to null
     * In case of existing book, retrieve current state from the database
     * and update the book
     *
     * @param theBook The book entity to be saved, either new or existing
     */
    public void saveBook(Book theBook) {
        if (theBook.getId() == 0) {
            theBook.setReviews(null);
        } else {
            Book existingBook = bookService.findById(theBook.getId());
            theBook.setReviews(existingBook.getReviews());
        }
        bookService.save(theBook);
    }

    public void deleteBook(int theId) {
        bookService.deleteByiD(theId);
    }

    public void listBooksByAuthor(int theId, Model model) {
        Author author = authorService.findById(theId);
        model.addAttribute("author", author);

        List<Book> books = author.getBooks();
        model.addAttribute("books", books);

        model.addAttribute("authors", authorService.findAll());
    }
}
