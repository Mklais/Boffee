package com.klaisapp.bookclub.repository.user;

import com.klaisapp.bookclub.model.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    UserProfile findByUserUserId(int userId);

}