package com.klaisapp.bookclub.service.controller.model;

import com.klaisapp.bookclub.exception.DuplicateEntityException;
import com.klaisapp.bookclub.model.Genre;
import com.klaisapp.bookclub.service.model.genre.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class GenreControllerService {

    private final GenreService genreService;

    @Autowired
    public GenreControllerService(GenreService genreService) {
        this.genreService = genreService;
    }

    public void addAttributesToGenreList(Model model) {
        List<Genre> genres = genreService.findAll();
        model.addAttribute("genres", genres);
    }

    /**
     * Creation of new genre
     * In case of creation request through book creation form, add variables to model, to deal with redirection
     * @param redirectToBooks did creation request come through book creation form
     * @param bookTitle Save the book title and then later pre-populate the book form
     * @param authorId Save the author ID and then later pre-populate the book form
     * @param model render
     */
    public void addAttributesToAddForm(boolean redirectToBooks, String bookTitle, Integer authorId, Model model) {
        Genre theGenre = new Genre();
        model.addAttribute("genre", theGenre);

        model.addAttribute("redirectToBooks", redirectToBooks);
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("authorId", authorId);
    }

    public void addAttributesToEditForm(int theId, Model model) {
        Genre theGenre = genreService.findById(theId);
        model.addAttribute("genre", theGenre);
    }

    public void saveGenre(Genre theGenre) {
        genreService.save(theGenre);
//        if (redirectToBooks) {
//            // If the genre was added through the book-form, redirect back there with book data
//            return "redirect:/books/addForm?redirectToBooks=true" +
//                    "&bookTitle=" + bookTitle +
//                    "&authorId=" + authorId;
//        } else {
//            return "redirect:/genres/list";
//        }
    }

    public void deleteGenre(int theId) {
        genreService.deleteById(theId);
    }
}
