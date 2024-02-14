package com.klaisapp.bookclub.service.book;

import com.klaisapp.bookclub.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(int theId);

    Book save(Book theBook);

    void deleteByiD(int theId);

    List<Book> findByIdIn(List<Integer> bookIds);
}