package com.klaisapp.bookclub.service.model.author;

import com.klaisapp.bookclub.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(int theId);

    Author save(Author theAuthor);

    void deleteById(int theId);

    boolean doesAuthorExist(String firstName, String lastName);
}
