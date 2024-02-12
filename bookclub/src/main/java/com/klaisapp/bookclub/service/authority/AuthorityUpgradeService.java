package com.klaisapp.bookclub.service.authority;

import com.klaisapp.bookclub.common.AuthorityNames;
import com.klaisapp.bookclub.model.Authority;
import com.klaisapp.bookclub.model.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class AuthorityUpgradeService {

    private final AuthorityService authorityService;

    public static final int UPGRADE_ACTIVITY_POINTS_THRESHOLD = 200;

    public AuthorityUpgradeService(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    /**
     * Checks if the user is eligible for an authority upgrade and adds this information to the model.
     *
     * @param theUser The user whose authority upgrade eligibility is to be checked.
     * @param model   The Spring MVC model to which the eligibility information is added.
     */

    public void evaluateAndSetAuthorityUpgradeStatus(User theUser, Model model) {
        boolean eligibleForUpgrade = hasEnoughActivityPointsForUpgrade(theUser);
        model.addAttribute("eligibleForUpgrade", eligibleForUpgrade);

        boolean alreadyUpgraded = alreadyHasTargetAuthority(theUser);
        model.addAttribute("alreadyUpgraded", alreadyUpgraded);
    }

    /**
     * Checks if the user has enough activity points for an upgrade.
     * @param theUser The user to be evaluated.
     * @return true if the user has enough points, false otherwise.
     */
    private boolean hasEnoughActivityPointsForUpgrade(User theUser) {
        return theUser.getActivityPoints() >= UPGRADE_ACTIVITY_POINTS_THRESHOLD;
    }

    /**
     * Checks if the user already possesses the target authority level.
     * @param theUser The user to be evaluated.
     * @return true if the user already has the authority, false otherwise.
     */
    private boolean alreadyHasTargetAuthority(User theUser) {
        Authority adminAuthority = authorityService.findByAuthorityName(AuthorityNames.ROLE_ADMIN);
        Authority bookLoverAuthority = authorityService.findByAuthorityName(AuthorityNames.ROLE_BOOKLOVER);
        return theUser.getAuthorities().contains(adminAuthority) || theUser.getAuthorities().contains(bookLoverAuthority);
    }
}
