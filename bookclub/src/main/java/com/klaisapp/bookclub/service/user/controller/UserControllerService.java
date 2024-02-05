package com.klaisapp.bookclub.service.user.controller;

import com.klaisapp.bookclub.common.FriendshipStatus;
import com.klaisapp.bookclub.model.user.Friendship;
import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.model.user.UserProfile;
import com.klaisapp.bookclub.service.user.authority.AuthorityServiceImpl;
import com.klaisapp.bookclub.service.user.authority.AuthorityUpgradeService;
import com.klaisapp.bookclub.service.user.friendship.FriendshipService;
import com.klaisapp.bookclub.service.user.user.UserService;
import com.klaisapp.bookclub.service.user.userprofile.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addAttributesToUsersList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        authorityControllerService.addUserAuthorityAttributes(model);
        addLoggedInUsername(model);
    }

    /**
     * Adds attributes to the model for rendering the user & profile page.
     * @param username The username of the user.
     * @param model The Spring MVC model.
     */
    public void addAttributesToProfilePage(String username, Model model) {
        // Model names inserted are:
        // theUser
        // theProfile

        // Finding and validating the user that is being viewed
        User theUser = findAndProcessUserForProfilePage(username, model);

        // Adding user & it's profile attributes to the model
        addUserAttributes(theUser, model);
        addUserProfileAttributes(theUser, model);
    }

    private User findAndProcessUserForProfilePage(String username, Model model) {
        User theUser = userService.findByUsername(username);
        // Validate and add logged in user
        userService.findAndValidateUser(theUser);
        model.addAttribute("theUser", theUser);

        return theUser;
    }

    /**
     * Private methods for profile page displaying
     */
    private void addUserAttributes(User theUser, Model model) {
        addLoggedInUsername(model);
        authorityUpgradeService.evaluateAndSetAuthorityUpgradeStatus(theUser, model);
        addAuthorityRankByName(theUser, model);
        addFriendshipAttributes(theUser, model);
    }

    private void addFriendshipAttributes(User theUser, Model model) {
        String loggedInUsername = userService.getLoggedInUsername();
        User loggedInUser = userService.findByUsername(loggedInUsername);

        FriendshipStatus friendshipStatus = friendshipService.determineFriendshipStatus(loggedInUser, theUser);

        model.addAttribute("friendshipStatus", friendshipStatus);
    }

//    private FriendshipStatus determineFriendshipStatus(String loggedInUsername, String profileUsername) {
//        Friendship friendship = friendshipService.findFriendshipsByUsernames(loggedInUsername, profileUsername);
//
//        if (friendship != null) {
//            return friendship.getStatus();
//        } else {
//            // Friendship doesn't exist, check if a friend request has been sent
//            // or if they are not friends at all
//            if (friendshipService.isFriendRequestSent(loggedInUsername, profileUsername)) {
//                return FriendshipStatus.REQUEST_SENT;
//            } else {
//                return FriendshipStatus.NOT_FRIENDS;
//            }
//        }
//    }

    /**
     * Adding profile attributes to the model
     * @param theUser for whom to add profile attributes to
     * @param model to which profile attributes will be added
     */
    private void addUserProfileAttributes(User theUser, Model model) {
        UserProfile userProfile = theUser.getUserProfile();
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

    // Private helper methods

    private void addAuthorityRankByName(User theUser, Model model) {
        String authorityRank = authorityService.findHighestAuthorityRankByName(theUser);
        model.addAttribute("authorityRank", authorityRank);
    }

    private void addLoggedInUsername(Model model) {
        String loggedInUsername = userService.getLoggedInUsername();
        model.addAttribute("loggedInUser", loggedInUsername);
    }
}
