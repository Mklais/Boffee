package com.klaisapp.bookclub.repository.user;

import com.klaisapp.bookclub.model.user.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByAuthorityName(String authorityName);

    Authority findById(int id);
}
