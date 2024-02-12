package com.klaisapp.bookclub.service.userprofile;

import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.model.UserProfile;
import com.klaisapp.bookclub.repository.UserProfileRepository;
import com.klaisapp.bookclub.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository, UserRepository userRepository) {
        this.userProfileRepository = userProfileRepository;
        this.userRepository = userRepository;
    }


    @Override
    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile findById(int theId) {
        Optional<UserProfile> result = userProfileRepository.findById(theId);
        return result.orElseThrow(() ->
                new RuntimeException("Did not find user profile with the id of: " + theId));
    }

    @Override
    @Transactional
    public UserProfile save(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    @Transactional
    public void updateUserProfile(UserProfile exisitingUserProfile, User theUser) {
        exisitingUserProfile.setUser(theUser);
        theUser.setUserProfile(exisitingUserProfile);
        userProfileRepository.save(exisitingUserProfile);
    }

    @Override
    @Transactional
    public void registerNewProfile(UserProfile userProfile, User user) {
        userProfile.setUser(user);
    }
}
