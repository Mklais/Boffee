package com.klaisapp.bookclub.service.user.friendship;

import com.klaisapp.bookclub.common.FriendshipStatus;
import com.klaisapp.bookclub.model.user.Friendship;
import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.repository.user.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;

    @Autowired
    public FriendshipServiceImpl(FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    @Override
    public List<Friendship> findAll() {
        return friendshipRepository.findAll();
    }

    @Override
    public Friendship findFriendShipById(int friendshipId) {
        return friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new RuntimeException("Friendship not found with ID: " + friendshipId));
    }

    @Override
    public List<Friendship> findFriendshipsForUser(int userId) {
        return friendshipRepository.findFriendshipsForUser(userId);
    }

    @Override
    public FriendshipStatus determineFriendshipStatus(User loggedInUser, User profileUser) {
        Friendship friendship = friendshipRepository.findByUser1AndUser2(loggedInUser, profileUser);

        if (friendship != null) {
            return friendship.getStatus();
        } else if (friendshipRepository.existsByUser1AndUser2AndStatus(profileUser, loggedInUser, FriendshipStatus.REQUEST_SENT)) {
            return FriendshipStatus.REQUEST_SENT;
        } else {
            return FriendshipStatus.NOT_FRIENDS;
        }
    }

    @Override
    public List<Friendship> findPendingFriendshipsByReceiver(int userId, FriendshipStatus status) {
        return friendshipRepository.findPendingFriendshipsByReceiver(userId, status);
    }

    @Override
    public Friendship save(Friendship friendship) {
        return friendshipRepository.save(friendship);
    }

    @Override
    public ResponseEntity<String> sendFriendRequest(User sender, User receiver) {
        try {
            Friendship friendshipRequest = new Friendship(sender, receiver, FriendshipStatus.PENDING);
            friendshipRepository.save(friendshipRequest);

            return ResponseEntity.ok("Friend request sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending friend request");
        }
    }
}
