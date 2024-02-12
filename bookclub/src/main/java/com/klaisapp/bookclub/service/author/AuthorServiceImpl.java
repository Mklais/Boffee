package com.klaisapp.bookclub.service.author;

import com.klaisapp.bookclub.common.messages.Message;
import com.klaisapp.bookclub.common.messages.MessageConstants;
import com.klaisapp.bookclub.exception.CustomApplicationException;
import com.klaisapp.bookclub.model.Author;
import com.klaisapp.bookclub.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findById(int theId) {
        Optional<Author> result = authorRepository.findById(theId);
        return result.orElseThrow(() -> new RuntimeException("Did not find author with id: " + theId));
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    @Transactional
    public Author save(Author theAuthor) {
        boolean authorExists = doesAuthorExist(theAuthor.getFirstName(), theAuthor.getLastName());

        if (authorExists) {
            throw new CustomApplicationException(Message.error(MessageConstants.ENTITY_DUPLICATE));
        }

        theAuthor.setBooks(null);
        return authorRepository.save(theAuthor);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        authorRepository.deleteById(theId);
    }

    @Override
    public boolean doesAuthorExist(String firstName, String lastName) {
        List<Author> authorsByName = authorRepository
                .findAllByFirstNameEqualsIgnoreCaseAndLastNameEqualsIgnoreCase(firstName, lastName);
        return !authorsByName.isEmpty();
    }
}
