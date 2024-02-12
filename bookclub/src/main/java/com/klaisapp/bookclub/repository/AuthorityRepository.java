package com.klaisapp.bookclub.repository;

import com.klaisapp.bookclub.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByAuthorityName(String authorityName);
    Authority findById(int id);
}
