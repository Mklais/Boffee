package com.klaisapp.bookclub.repository;

import com.klaisapp.bookclub.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    boolean existsByNameIgnoreCase(String name);
}
