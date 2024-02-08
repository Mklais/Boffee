package com.klaisapp.bookclub.service.user.controller;

import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.model.user.UserProfile;
import com.klaisapp.bookclub.service.user.authority.AuthorityServiceImpl;
import com.klaisapp.bookclub.service.user.authority.AuthorityUpgradeService;
import com.klaisapp.bookclub.service.user.friendship.FriendshipService;
import com.klaisapp.bookclub.service.user.user.UserService;
import com.klaisapp.bookclub.service.user.userprofile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class UserControllerService {

    private final UserService userService;

    private final AuthorityServiceImpl authorityService;

    private final AuthorityUpgradeService authorityUpgradeService;

    private final AuthorityControllerService authorityControllerService;

    private final UserProfileService userProfileService;

    private final FriendshipService friendshipService;
    @Autowired
    public UserControllerService(UserService userService, AuthorityServiceImpl authorityService,
                                 UserProfileService userProfileService, AuthorityUpgradeService authorityUpgradeService, AuthorityControllerService authorityControllerService, FriendshipService friendshipService) {
        this.userService = userService;
        this.authorityService = authorityService;
        this.userProfileService = userProfileService;
        this.authorityUpgradeService = authorityUpgradeService;
        this.authorityControllerService = authorityControllerService;
        this.friendshipService = friendshipService;
    }

    /**
     * Adds a list of users to the model attribute for rendering in views.
     * @param model The Spring MVC model.
     */
    public void addAttributesToUsersList(UserDetails userDetails, Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        authorityControllerService.addUserAuthorityAttributes(model);
    }

    /**
     * Adds attributes to the model for rendering the user & profile page.
     * @param username The username of the user.
     * @param model The Spring MVC model.
     */
    public void prepareProfilePageModel(UserDetails userDetails, String username, Model model) {
        User viewedUser = retrieveUserForProfile(username, model);
        User currentUser = userService.findByUsername(userDetails.getUsername());

        populateModelWithUserProfileData(viewedUser, currentUser, model);
    }

    /**
     * Retrieves and validates the user associated with the given username, adding them to the model.
     *
     * @param username The username of the user to find.
     * @return The User entity associated with the given username.
     */
    private User retrieveUserForProfile(String username, Model model) {
        User user = userService.findByUsername(username);
        userService.findAndValidateUser(user);
        return user;
    }

    /**
     * Populates the model with attributes related to the user's profile and their relationship with the current user.
     *
     * @param viewedUser  The user being viewed.
     * @param currentUser The currently authenticated user.
     * @param model       The model to which attributes will be added.
     */
    private void populateModelWithUserProfileData(User viewedUser, User currentUser, Model model) {
        addUserAndItsProfileAttributes(viewedUser, model);

        // Evaluate and set authority and rank status for the viewed user.
        authorityUpgradeService.evaluateAndSetAuthorityUpgradeStatus(viewedUser, model);
        addAuthorityRankByName(viewedUser, model);

        // Determine and add the friendship status between the current user and the viewed user.
        determineAndAddFriendshipStatus(currentUser, viewedUser, model);
    }

    private void addAuthorityRankByName(User theUser, Model model) {
        model.addAttribute("authorityRank", authorityService.findHighestAuthorityRankByName(theUser));
    }

    private void determineAndAddFriendshipStatus(User currentUser, User viewedUser, Model model) {
        model.addAttribute("friendshipStatus", friendshipService
                .determineFriendshipStatus(currentUser, viewedUser));
    }

    private void addUserAndItsProfileAttributes(User viewedUser, Model model) {
        model.addAttribute("theUser", viewedUser);
        UserProfile userProfile = viewedUser.getUserProfile();
        model.addAttribute("theProfile", userProfile);
    }

    public void updateUserAndRelatedEntities(User theUser) {
        User existingUser = userService.findById(theUser.getUserId());
        UserProfile existingProfile = existingUser.getUserProfile();

        // Then updating the user and its profile
        userProfileService.updateUserProfile(existingProfile, theUser);
        authorityService.mergeAndSetUserAuthorities(existingUser, theUser);

        userService.save(theUser);
    }

    public void addAttributesForUserUpdate(int theId, Model model) {
        User theUser = userService.findById(theId);
        model.addAttribute("theUser", theUser);
    }

    public void toggleUserActiveStatus(String username) {
        userService.toggleUserActiveStatus(username);
    }

    public void deleteUser(int theId) {
        User userToDelete = userService.findById(theId);
        // Clear all authorities from user
        // User-profile is cascade deleted
        userToDelete.getAuthorities().clear();
        userService.save(userToDelete);
        userService.deleteById(theId);
    }
}
