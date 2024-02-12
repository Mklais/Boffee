package com.klaisapp.bookclub.repository;

import com.klaisapp.bookclub.common.FriendshipStatus;
import com.klaisapp.bookclub.model.Friendship;
import com.klaisapp.bookclub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<Friendship, Integer> {
    @Query("SELECT CASE WHEN COUNT(f) > 0 " +
            "THEN true ELSE false END " +
            "FROM Friendship f " +
            "WHERE (f.sender = :sender AND f.receiver = :receiver) " +
            "OR (f.sender = :receiver AND f.receiver = :sender)")
    boolean existsBySenderAndReceiver(@Param("sender") User sender, @Param("receiver") User receiver);

    @Query("SELECT f FROM Friendship f " +
            "WHERE ((f.sender = :user AND f.status = 'PENDING') " +
            "OR (f.receiver = :user AND f.status = 'ACCEPTED') " +
            "OR (f.sender = :user AND f.status = 'ACCEPTED'))")
    List<Friendship> findAllRelevantFriendshipsByUser(@Param("user") User user);

    @Query("SELECT f FROM Friendship f " +
            "WHERE (f.receiver = :user) AND f.status = :status")
    List<Friendship> findAllByReceiverAndStatus(@Param("user") User user, @Param("status") FriendshipStatus status);

    @Query("SELECT f FROM Friendship f WHERE " +
            "(f.sender.id = :userId1 AND f.receiver.id = :userId2) OR " +
            "(f.sender.id = :userId2 AND f.receiver.id = :userId1)")
    Optional<Friendship> findByUsers(@Param("userId1") int userId1, @Param("userId2") int userId2);
}
