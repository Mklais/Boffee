package com.klaisapp.bookclub.service.authority;

import com.klaisapp.bookclub.common.AuthorityNames;
import com.klaisapp.bookclub.model.Authority;
import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.service.user.UserService;
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
        Authority newAuthority = authorityService.findByAuthorityName(AuthorityNames.ROLE_BOOKLOVER);

        String username = userService.getLoggedInUsername();
        userService.upgradeAuthorityToUser(username, newAuthority);

        return username;
    }

    public void addUserAuthorityAttributes(Model model) {
        model.addAttribute("isUserAdmin", isUserAdminAuthority());
    }

    /**
     * Checks if the currently logged-in user has the "ROLE_ADMIN" authority.
     * This method retrieves the user's authorities based on the logged-in username
     * and uses a stream to check if any of the authorities match "ROLE_ADMIN".
     *
     * @return true if the user has "ROLE_ADMIN" authority, false otherwise.
     */
    private boolean isUserAdminAuthority() {
        String loggedInUserUsername = userService.getLoggedInUsername();

        User theUser = userService.findByUsername(loggedInUserUsername);
        Set<Authority> userAuthorities = theUser.getAuthorities();

        return userAuthorities.stream()
                .anyMatch(authority -> AuthorityNames.ROLE_ADMIN.equals(authority.getAuthorityName()));
    }
}
