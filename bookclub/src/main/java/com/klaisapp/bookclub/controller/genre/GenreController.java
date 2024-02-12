package com.klaisapp.bookclub.controller.genre;

import com.klaisapp.bookclub.exception.CustomApplicationException;
import com.klaisapp.bookclub.model.Genre;
import com.klaisapp.bookclub.service.genre.GenreControllerService;
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
    public String showFormForAdd(Model model) {
        genreControllerService.addAttributesToAddForm(model);
        return "genre/genre-form";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("genreId") int theId, Model model) {
        genreControllerService.addAttributesToEditForm(theId, model);
        return "genre/genre-form";
    }

    @PostMapping("/save")
    public String saveGenre(@ModelAttribute("genre") Genre genre, Model model) {
        try {
            genreControllerService.saveGenre(genre);
        } catch (CustomApplicationException e) {
            model.addAttribute("message", e.getMessage());
            return "genre/genre-form";
        }
        return "redirect:/genres/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("genreId") int theId) {
        genreControllerService.deleteGenre(theId);
        return "redirect:/genres/list";
    }
}
