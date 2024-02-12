package com.klaisapp.bookclub.repository;

import com.klaisapp.bookclub.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    UserProfile findByUserUserId(int userId);

}