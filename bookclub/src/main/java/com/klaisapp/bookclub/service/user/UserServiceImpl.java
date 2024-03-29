package com.klaisapp.bookclub.service.user;

import com.klaisapp.bookclub.common.messages.Message;
import com.klaisapp.bookclub.common.messages.MessageConstants;
import com.klaisapp.bookclub.exception.CustomApplicationException;
import com.klaisapp.bookclub.model.Authority;
import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.model.UserProfile;
import com.klaisapp.bookclub.repository.AuthorityRepository;
import com.klaisapp.bookclub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    private final int DEFAULT_AUTHORITY_INDEX = 3;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           AuthorityRepository authorityRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
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
        // Encode password before saving it
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        user.setActive(1);
        user.setActivityPoints(0);
        user.setEmoji("1");

        Authority defaultAuthority = authorityRepository.findById(DEFAULT_AUTHORITY_INDEX);

        if (defaultAuthority != null) {
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
     * @throws CustomApplicationException in case of not found, throw exception
     */
    private void clearSecurityContextAndThrowUserNotFoundException() {
        SecurityContextHolder.clearContext();
        throw new CustomApplicationException(Message.error(MessageConstants.USER_NOT_FOUND));
    }
}