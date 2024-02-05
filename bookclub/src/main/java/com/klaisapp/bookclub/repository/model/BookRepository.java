package com.klaisapp.bookclub.repository.model;

import com.klaisapp.bookclub.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByIdIn(List<Integer> bookIds);
}
