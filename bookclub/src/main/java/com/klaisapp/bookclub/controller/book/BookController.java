package com.klaisapp.bookclub.controller.book;

import com.klaisapp.bookclub.model.Book;
import com.klaisapp.bookclub.service.book.BookControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookControllerService bookControllerService;

    @Autowired
    public BookController(BookControllerService bookControllerService) {
        this.bookControllerService = bookControllerService;
    }

    @GetMapping("/list")
    public String listBooks(Model model) {
        bookControllerService.addAttributesToBooksList(model);
        return "book/book-list";
    }

    @GetMapping("/addForm")
    public String showFormForAdd(Model model) {
        bookControllerService.addAttributesToBookAddForm(model);
        return "book/book-form";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("bookId") int theId, Model model) {
        bookControllerService.addAttributesToEditForm(theId, model);
        return "book/book-update-form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute("book") Book theBook) {
        bookControllerService.saveBook(theBook);
        return "redirect:/books/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("bookId") int theId) {
        bookControllerService.deleteBook(theId);
        return "redirect:/books/list";
    }

    @GetMapping("/listByAuthor")
    public String listBooksByAuthor(@RequestParam("authorId") int theId, Model model) {
        bookControllerService.listBooksByAuthor(theId, model);
        return "book/author-based-list";
    }
}
