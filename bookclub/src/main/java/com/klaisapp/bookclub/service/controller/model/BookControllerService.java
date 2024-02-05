package com.klaisapp.bookclub.service.controller.model;

import com.klaisapp.bookclub.model.Author;
import com.klaisapp.bookclub.model.Book;
import com.klaisapp.bookclub.model.Genre;
import com.klaisapp.bookclub.service.model.author.AuthorService;
import com.klaisapp.bookclub.service.model.book.BookService;
import com.klaisapp.bookclub.service.model.genre.GenreService;
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
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);

        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
    }

    public void addAttributesToAddForm(String bookTitle, Model model) {
        Book theBook = new Book();

        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();

        if (bookTitle != null) {
            theBook.setTitle(bookTitle);
        }

        model.addAttribute("book", theBook);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
    }

    public void addAttributesToEditForm(int theId, Model model) {
        Book theBook = bookService.findById(theId);
        List<Author> authors = authorService.findAll();
        List<Genre> genres = genreService.findAll();

        model.addAttribute("book", theBook);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
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

        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);

        List<Book> books = author.getBooks();
        model.addAttribute("books", books);
    }
}