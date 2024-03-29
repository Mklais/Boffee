package com.klaisapp.bookclub.repository;

import com.klaisapp.bookclub.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    List<Author> findAllByOrderByLastNameAsc();

    List<Author> findAllByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(String firstName, String lastName);
}
