package com.klaisapp.bookclub.repository;


import com.klaisapp.bookclub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    User findByUserProfile_ProfileId(int profileId);

    boolean existsByUsername(String username);

}
