package com.klaisapp.bookclub.service.book;

import com.klaisapp.bookclub.common.messages.Message;
import com.klaisapp.bookclub.common.messages.MessageConstants;
import com.klaisapp.bookclub.exception.CustomApplicationException;
import com.klaisapp.bookclub.model.Book;
import com.klaisapp.bookclub.repository.BookRepository;
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
        boolean bookExists = doesBookWithTitleExist(theBook.getTitle());

        if (bookExists) {
            throw new CustomApplicationException(Message.error(MessageConstants.ENTITY_DUPLICATE));
        }

        return bookRepository.save(theBook);
    }

    private boolean doesBookWithTitleExist(String title) {
        return bookRepository.existsByTitleIgnoreCase(title);
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
