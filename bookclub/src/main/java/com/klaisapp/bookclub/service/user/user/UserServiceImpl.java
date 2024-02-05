package com.klaisapp.bookclub.service.user.user;

import com.klaisapp.bookclub.exception.UserNotFoundException;
import com.klaisapp.bookclub.model.user.Authority;
import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.model.user.UserProfile;
import com.klaisapp.bookclub.repository.user.AuthorityRepository;
import com.klaisapp.bookclub.repository.user.UserRepository;
import com.klaisapp.bookclub.service.messageservice.errormessage.ErrorMessageService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final int DEFAULT_AUTHORITY_INDEX = 3;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        return result.orElseThrow(() ->
                new RuntimeException("Did not find user with username: " + username));
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);
        return result.orElseThrow(() ->
                new RuntimeException("Did not find user with id: " + theId));
    }

    @Override
    public User findByUserProfile_ProfileId(int profileId) {
        return userRepository.findByUserProfile_ProfileId(profileId);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User theUser) {
        return userRepository.save(theUser);
    }

    @Override
    public boolean doesUserExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null && authentication.isAuthenticated()) ? authentication.getName() : null;
    }

    @Override
    @Transactional
    public void registerUser(User user, UserProfile userProfile) {
        // Set default data
        user.setPassword("{noop}" + user.getPassword());
        user.setActive(1);
        user.setActivityPoints(0);
        user.setEmoji("1");

        // Set authority
        Authority defaultAuthority = authorityRepository.findById(DEFAULT_AUTHORITY_INDEX);

        if (defaultAuthority != null) {
            // Set authority for the user
            user.setAuthorities(Set.of(defaultAuthority));
        }

        user.setUserProfile(userProfile);

        // Save the user
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void upgradeAuthorityToUser(String username, Authority newAuthority) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User theUser = optionalUser.get();
            theUser.getAuthorities().add(newAuthority);
        } else {
            throw new RuntimeException("Did not find user with username: " + username);
        }
    }

    /**
     * Finds and validates a user by their username.
     *
     * @param theUser entity
     * @return The validated User object.
     */
    @Override
    public User findAndValidateUser(User theUser) {
        if (theUser == null) {
            clearSecurityContextAndThrowUserNotFoundException();
        }
        return theUser;
    }

    @Override
    @Transactional
    public void toggleUserActiveStatus(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User toggleUser = optionalUser.get();
            toggleUser.setActive(
                    toggleUser.getActive() == 0 ? (byte) 1 : (byte) 0);
            userRepository.save(toggleUser);
        } else {
            throw new RuntimeException("Did not find user with username: " + username);
        }
    }

    /**
     * Clears the security context and throws a UserNotFoundException.
     * This is used when a user is not found during validation.
     *
     * @throws UserNotFoundException in case of not found, throw exception
     */
    private void clearSecurityContextAndThrowUserNotFoundException() {
        SecurityContextHolder.clearContext();
        throw new
                UserNotFoundException(
                ErrorMessageService.getNoUserFoundExceptionErrorMessage());
    }

}