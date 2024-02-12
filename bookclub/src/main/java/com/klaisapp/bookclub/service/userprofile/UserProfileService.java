package com.klaisapp.bookclub.service.userprofile;

import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.model.UserProfile;

import java.util.List;

public interface UserProfileService {

    List<UserProfile> findAll();

    UserProfile findById(int theId);

    UserProfile save(UserProfile userProfile);

    void updateUserProfile(UserProfile exisitingUserProfile, User theUser);

    void registerNewProfile(UserProfile userProfile, User user);
}
