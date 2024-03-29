package com.klaisapp.bookclub.service.genre;

import com.klaisapp.bookclub.common.messages.Message;
import com.klaisapp.bookclub.common.messages.MessageConstants;
import com.klaisapp.bookclub.exception.CustomApplicationException;
import com.klaisapp.bookclub.model.Genre;
import com.klaisapp.bookclub.repository.GenreRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(int theId) {
        Optional<Genre> result = genreRepository.findById(theId);

        Genre theGenre = null;

        if (result.isPresent()) {
            theGenre = result.get();
        } else {
            throw new RuntimeException("Did not find genre with id: " + theId);
        }
        return theGenre;
    }

    @Override
    @Transactional
    public Genre save(Genre theGenre) {
        boolean genreExists = doesGenreExist(theGenre.getName());

        if (genreExists) {
            throw new CustomApplicationException(Message.error(MessageConstants.ENTITY_DUPLICATE));
        }
        theGenre.setBooks(new ArrayList<>());
        return genreRepository.save(theGenre);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        genreRepository.deleteById(theId);
    }

    @Override
    public boolean doesGenreExist(String name) {
        return genreRepository.existsByNameIgnoreCase(name);
    }
}
