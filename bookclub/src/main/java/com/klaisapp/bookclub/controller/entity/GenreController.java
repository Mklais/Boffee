package com.klaisapp.bookclub.controller.entity;

import com.klaisapp.bookclub.exception.DuplicateEntityException;
import com.klaisapp.bookclub.model.Genre;
import com.klaisapp.bookclub.service.controller.model.GenreControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genres")
public class GenreController {

    private final GenreControllerService genreControllerService;

    @Autowired
    public GenreController(GenreControllerService genreControllerService) {
        this.genreControllerService = genreControllerService;
    }

    @GetMapping("/list")
    public String listGenres(Model model) {
        genreControllerService.addAttributesToGenreList(model);
        return "genre/genre-list";
    }

    @GetMapping("/addForm")
    public String showFormForAdd(
            @RequestParam(name = "redirectToBooks", required = false, defaultValue = "false") boolean redirectToBooks,
            @RequestParam(name = "bookTitle", required = false) String bookTitle,
            @RequestParam(name = "authorId", required = false) Integer authorId,
            Model model) {
        genreControllerService.addAttributesToAddForm(redirectToBooks, bookTitle, authorId, model);
        return "genre/genre-form";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("genreId") int theId, Model model) {
        genreControllerService.addAttributesToEditForm(theId, model);
        return "genre/genre-form";
    }

    @PostMapping("/save")
    public String saveGenre(@ModelAttribute("genre") Genre theGenre, Model model) {
        try {
            genreControllerService.saveGenre(theGenre);
        } catch (DuplicateEntityException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "genre/genre-form";
        }
        return "redirect:/genres/list";
    }

//    @PostMapping("/save")
//    public void saveGenre(
//            @ModelAttribute("genre") Genre theGenre,
//            @RequestParam(name = "redirectToBooks", required = false, defaultValue = "false") boolean redirectToBooks,
//            @RequestParam(name = "bookTitle", required = false) String bookTitle,
//            @RequestParam(name = "authorId", required = false) Integer authorId,
//            Model model) {
//        genreControllerService.saveGenre(theGenre, redirectToBooks, bookTitle, authorId, model);
//    }

    @GetMapping("/delete")
    public String delete(@RequestParam("genreId") int theId) {
        genreControllerService.deleteGenre(theId);
        return "redirect:/genres/list";
    }
}
