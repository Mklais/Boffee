package com.klaisapp.bookclub.service.controller.model;

import com.klaisapp.bookclub.model.Genre;
import com.klaisapp.bookclub.service.model.genre.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class GenreControllerService {

    private final GenreService genreService;

    @Autowired
    public GenreControllerService(GenreService genreService) {
        this.genreService = genreService;
    }

    public void addAttributesToGenreList(Model model) {
        model.addAttribute("genres", genreService.findAll());
    }

    public void addAttributesToAddForm(Model model) {
        model.addAttribute("genre", new Genre());
    }

    public void addAttributesToEditForm(int theId, Model model) {
        model.addAttribute("genre", genreService.findById(theId));
    }

    public void saveGenre(Genre theGenre) {
        genreService.save(theGenre);
    }

    public void deleteGenre(int theId) {
        genreService.deleteById(theId);
    }
}
