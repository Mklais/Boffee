package com.klaisapp.bookclub.service.user.controller;

import com.klaisapp.bookclub.model.user.Authority;
import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.service.user.authority.AuthorityService;
import com.klaisapp.bookclub.service.user.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Set;

@Service
public class AuthorityControllerService {

    private final AuthorityService authorityService;

    private final UserService userService;

    @Autowired
    public AuthorityControllerService(AuthorityService authorityService, UserService userService) {
        this.authorityService = authorityService;
        this.userService = userService;
    }

    /**
     * Upgrades the user authority
     * @return The username of the upgraded user.
     */
    public String addUpgradeToUserAuthority() {
        // Retrieving the new authority from the database
        Authority newAuthority = authorityService.findById(2);

        // Retrieving user, who is eligible for an upgrade
        String username = userService.getLoggedInUsername();
        userService.upgradeAuthorityToUser(username, newAuthority);

        return username;
    }

    public void addUserAuthorityAttributes(Model model) {
        model.addAttribute("isUserAdmin", isUserAdminAuthority());
    }

    private boolean isUserAdminAuthority() {
        String loggedInUserUsername = userService.getLoggedInUsername();

        // Find the user entity by username
        User theUser = userService.findByUsername(loggedInUserUsername);
        Set<Authority> userAuthorities = theUser.getAuthorities();

        // Target authority = "ROLE_ADMIN" & "1"
        int targetAuthorityIndex = 1;

        for (Authority authority : userAuthorities) {
            int authorityId = authority.getAuthorityId();
            if (authorityId == targetAuthorityIndex) {
                return true;
            }
        }
        return false;
    }
}
