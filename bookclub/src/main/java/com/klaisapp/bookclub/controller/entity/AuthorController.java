package com.klaisapp.bookclub.controller.entity;

import com.klaisapp.bookclub.exception.DuplicateEntityException;
import com.klaisapp.bookclub.model.Author;
import com.klaisapp.bookclub.service.controller.model.AuthorControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorControllerService authorControllerService;

    @Autowired
    public AuthorController(AuthorControllerService authorControllerService) {
        this.authorControllerService = authorControllerService;
    }

    @GetMapping("/list")
    public String listAuthors(Model model) {
        authorControllerService.addAttributesToAuthorsList(model);
        return "author/list";
    }

    @GetMapping("/addForm")
    public String showFormForAdd(Model model) {
        authorControllerService.addAttributesToAddForm(model);
        return "author/author-form";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("authorId") int theId, Model model) {
        authorControllerService.addAttributesToEditForm(theId, model);
        return "author/author-form";
    }

    @PostMapping("/save")
    public String saveAuthor(@ModelAttribute("author") Author theAuthor, Model model) {
        try {
            authorControllerService.saveAuthor(theAuthor);
        } catch (DuplicateEntityException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "author/author-form";
        }
        return "redirect:/authors/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("authorId") int theId) {
        authorControllerService.deleteAuthor(theId);
        return "redirect:/authors/list";
    }
}
