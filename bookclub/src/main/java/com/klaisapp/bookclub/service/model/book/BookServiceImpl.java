package com.klaisapp.bookclub.service.model.book;

import com.klaisapp.bookclub.model.Book;
import com.klaisapp.bookclub.repository.model.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findById(int theId) {
        Optional<Book> result = bookRepository.findById(theId);

        return result.orElseThrow(() -> new RuntimeException("Did not find book with id: " + theId));
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book save(Book theBook) {
        return bookRepository.save(theBook);
    }

    @Override
    @Transactional
    public void deleteByiD(int theId) {
        bookRepository.deleteById(theId);
    }

    @Override
    public List<Book> findByIdIn(List<Integer> bookIds) {
        return bookRepository.findByIdIn(bookIds);
    }
}
