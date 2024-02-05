package com.klaisapp.bookclub.service.controller.model;

import com.klaisapp.bookclub.exception.DuplicateEntityException;
import com.klaisapp.bookclub.model.Author;
import com.klaisapp.bookclub.service.model.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class AuthorControllerService {

    private final AuthorService authorService;

    @Autowired
    public AuthorControllerService(AuthorService authorService) {
        this.authorService = authorService;
    }

    public void addAttributesToAuthorsList(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
    }

    public void addAttributesToAddForm(Model model) {
        Author theAuthor = new Author();
        model.addAttribute("author", theAuthor);
    }

    public void addAttributesToEditForm(int theId, Model model) {
        Author theAuthor = authorService.findById(theId);
        model.addAttribute("author", theAuthor);
    }

    public void saveAuthor(Author theAuthor) {
        authorService.save(theAuthor);
    }

    public void deleteAuthor(int theId) {
        authorService.deleteById(theId);
    }
}
