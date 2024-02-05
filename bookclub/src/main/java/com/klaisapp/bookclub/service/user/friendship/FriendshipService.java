package com.klaisapp.bookclub.service.user.friendship;

import com.klaisapp.bookclub.common.FriendshipStatus;
import com.klaisapp.bookclub.model.user.Friendship;
import com.klaisapp.bookclub.model.user.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FriendshipService {

    List<Friendship> findAll();

    Friendship findFriendShipById(int friendshipId);

    List<Friendship> findFriendshipsForUser(int userId);

    FriendshipStatus determineFriendshipStatus(User loggedInUser, User profileUser);

    List<Friendship> findPendingFriendshipsByReceiver(int userId, FriendshipStatus friendshipStatus);

    Friendship save(Friendship friendship);

    ResponseEntity<String> sendFriendRequest(User sender, User receiver);

}
