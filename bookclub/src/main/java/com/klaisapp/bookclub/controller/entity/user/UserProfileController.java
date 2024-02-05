package com.klaisapp.bookclub.controller.entity.user;

import com.klaisapp.bookclub.model.user.UserProfile;
import com.klaisapp.bookclub.service.user.controller.UserProfileControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/about")
public class UserProfileController {
    private final UserProfileControllerService userProfileControllerService;

    @Autowired
    public UserProfileController(UserProfileControllerService userProfileControllerService) {
        this.userProfileControllerService = userProfileControllerService;
    }

    @GetMapping("/edit")
    public String showProfileForm(@RequestParam("userId") int profileId, Model model) {
        // Entering in the form through user_id and then adding to the model the associated user profile to be edited
        userProfileControllerService.addAttributesToEditForm(profileId, model);
        return "userprofile/userprofile-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("theProfile") UserProfile userProfile, Model model) {
        userProfileControllerService.saveUserProfile(userProfile);
        String username = userProfileControllerService.getUsername(userProfile);
        return "redirect:/users/profile/" + username;
    }
}
