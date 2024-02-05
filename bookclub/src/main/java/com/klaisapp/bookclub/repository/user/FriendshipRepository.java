package com.klaisapp.bookclub.repository.user;

import com.klaisapp.bookclub.common.FriendshipStatus;
import com.klaisapp.bookclub.model.user.Friendship;
import com.klaisapp.bookclub.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {

    Friendship findByUser1AndUser2(User user1, User user2);

    boolean existsByUser1AndUser2AndStatus(User user1, User user2, FriendshipStatus status);

    @Query("SELECT f FROM Friendship f WHERE " +
            "(f.user1.userId = :userId AND (f.status = 'ACCEPTED' OR (f.status = 'PENDING' AND f.user2.userId = :userId))) " +
            "OR (f.user2.userId = :userId AND (f.status = 'ACCEPTED' OR (f.status = 'PENDING' AND f.user1.userId = :userId)))")
    List<Friendship> findFriendshipsForUser(@Param("userId") int userId);

    @Query("SELECT f FROM Friendship f WHERE (f.user1.id = :userId OR f.user2.id = :userId) AND f.status = :status")
    List<Friendship> findPendingFriendshipsByReceiver(
            @Param("userId") int userId, @Param("status") FriendshipStatus status);
}
