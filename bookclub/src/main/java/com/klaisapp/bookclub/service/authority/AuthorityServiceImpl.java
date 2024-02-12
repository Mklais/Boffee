package com.klaisapp.bookclub.service.authority;

import com.klaisapp.bookclub.model.Authority;
import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.repository.AuthorityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }


    @Override
    public Authority findById(int authorityId) {
        return authorityRepository.findById(authorityId);
    }

    @Override
    public Authority findByAuthorityName(String authorityName) {
        return authorityRepository.findByAuthorityName(authorityName);
    }

    /**
     * Find the highest rank by authority name
     * Uses authority name's associated id to find the name
     *
     * @param theUser entity
     * @return authority name without the prefix for display
     */
    @Override
    public String findHighestAuthorityRankByName(User theUser) {
        Set<Authority> authorities = theUser.getAuthorities();
        // Low index equals to high rank
        int lowAuthorityIndex = 3;

        for (Authority authority : authorities) {
            int authorityId = authority.getAuthorityId();
            if (authorityId < lowAuthorityIndex) {
                lowAuthorityIndex = authorityId;
            }
        }
        Authority authority = authorityRepository.findById(lowAuthorityIndex);
        return formatAuthorityName(authority);
    }

    /**
     * Format the authority names for display
     *
     * @param authority entity
     * @return authority name without the prefix and capital first letter
     */
    private static String formatAuthorityName(Authority authority) {
        String authorityName = authority.getAuthorityName();
        String formattedName = authorityName.replace("ROLE_", "").toLowerCase();
        formattedName = formattedName.substring(0, 1).toUpperCase() + formattedName.substring(1);

        return formattedName;
    }

    @Override
    @Transactional
    public void mergeAndSetUserAuthorities(User exisitingUser, User theUser) {
        Set<Authority> existingAuthorities = exisitingUser.getAuthorities();
        theUser.setAuthorities(existingAuthorities);
    }
}
