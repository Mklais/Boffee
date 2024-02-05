package com.klaisapp.bookclub.repository.model;

import com.klaisapp.bookclub.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
