package com.klaisapp.bookclub.service.user;

import com.klaisapp.bookclub.model.Authority;
import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.model.UserProfile;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findByUsername(String username);

    User findById(int theId);

    User findByUserProfile_ProfileId(int profileId);

    User save(User theUser);

    boolean doesUserExist(String username);

    void deleteById(int theId);

    String getLoggedInUsername();

    void registerUser(User user, UserProfile userProfile);

    void upgradeAuthorityToUser(String username, Authority newAuthority);

    User findAndValidateUser(User theUser);

    void toggleUserActiveStatus(String username);

}