package com.klaisapp.bookclub.service.auth;

import com.klaisapp.bookclub.common.messages.MessageConstants;
import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.model.UserProfile;
import com.klaisapp.bookclub.service.user.UserService;
import com.klaisapp.bookclub.service.userprofile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class RegistrationControllerService {

    private final UserService userService;

    private final UserProfileService userProfileService;

    @Autowired
    public RegistrationControllerService(UserService userService, UserProfileService userProfileService) {
        this.userService = userService;
        this.userProfileService = userProfileService;
    }

    public void addAttributesToForm(Model model) {
        model.addAttribute("user", new User());
    }

    public boolean isErrorInRegistrationProcess(User user, String passwordVerify, Model model) {
        if (!user.getPassword().equals(passwordVerify)) {
            model.addAttribute("error", MessageConstants.PASSWORDS_DO_NOT_MATCH);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Save user with a null UserProfile entity class associated with it
     * @param user entity to be processed and saved
     */
    public void saveUser(User user) {
        createUserAssociatedPersonalProfile(user);
    }

    private void createUserAssociatedPersonalProfile(User user) {
        // Creation of a new user-profile class and assigning it to the user
        UserProfile userProfile = new UserProfile();
        // Creation of user & and saving it
        userService.registerUser(user, userProfile);
        userProfileService.registerNewProfile(userProfile, user);
    }
}
