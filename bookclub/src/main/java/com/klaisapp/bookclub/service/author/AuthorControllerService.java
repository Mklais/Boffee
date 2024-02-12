package com.klaisapp.bookclub.service.author;

import com.klaisapp.bookclub.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
@Service
public class AuthorControllerService {

    private final AuthorService authorService;

    @Autowired
    public AuthorControllerService(AuthorService authorService) {
        this.authorService = authorService;
    }

    public void addAttributesToAuthorsList(Model model) {
        model.addAttribute("authors", authorService.findAll());
    }

    public void addAttributesToAddForm(Model model) {
        model.addAttribute("author", new Author());
    }

    public void addAttributesToEditForm(int theId, Model model) {
        model.addAttribute("author", authorService.findById(theId));
    }

    public void saveAuthor(Author theAuthor) {
        authorService.save(theAuthor);
    }

    public void deleteAuthor(int theId) {
        authorService.deleteById(theId);
    }
}
