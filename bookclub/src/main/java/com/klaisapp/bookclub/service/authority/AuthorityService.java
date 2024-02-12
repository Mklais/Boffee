package com.klaisapp.bookclub.service.authority;

import com.klaisapp.bookclub.model.Authority;
import com.klaisapp.bookclub.model.User;

public interface AuthorityService {

    Authority findById(int authorityId);

    Authority findByAuthorityName(String authorityName);

    String findHighestAuthorityRankByName(User theUser);

    void mergeAndSetUserAuthorities(User exisitingUser, User theUser);
}
