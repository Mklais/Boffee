package com.klaisapp.bookclub.service.genre;

import com.klaisapp.bookclub.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre findById(int theId);

    Genre save(Genre theGenre);

    void deleteById(int theId);

    boolean doesGenreExist(String name);

}
