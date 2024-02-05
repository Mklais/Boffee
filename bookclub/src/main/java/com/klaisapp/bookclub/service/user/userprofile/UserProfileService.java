package com.klaisapp.bookclub.service.user.userprofile;

import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.model.user.UserProfile;

import java.util.List;

public interface UserProfileService {

    List<UserProfile> findAll();

    UserProfile findById(int theId);

    UserProfile save(UserProfile userProfile);

    void updateUserProfile(UserProfile exisitingUserProfile, User theUser);

    void registerNewProfile(UserProfile userProfile, User user);
}
