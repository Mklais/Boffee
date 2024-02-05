package com.klaisapp.bookclub.service.user.authority;

import com.klaisapp.bookclub.model.user.Authority;
import com.klaisapp.bookclub.model.user.User;

public interface AuthorityService {

    Authority findById(int authorityId);

    Authority findByAuthorityName(String authorityName);

    String findHighestAuthorityRankByName(User theUser);

    void mergeAndSetUserAuthorities(User exisitingUser, User theUser);
}
