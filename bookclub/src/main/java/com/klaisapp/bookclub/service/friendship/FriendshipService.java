package com.klaisapp.bookclub.service.friendship;

import com.klaisapp.bookclub.common.FriendshipStatus;
import com.klaisapp.bookclub.model.Friendship;
import com.klaisapp.bookclub.model.User;

import java.util.List;
import java.util.Optional;

public interface FriendshipService {
    Optional<Friendship> findById(int friendshipId);
    Friendship sendFriendRequest(User sender, User receiver);
    void acceptFriendRequest(int requestId);
    void declineFriendRequest(int requestId);
    void declineAndDeleteFriendRequest(int requestId);
    List<Friendship> listFriendships(User user);
    List<Friendship> listIncomingFriendRequests(User user);

    FriendshipStatus determineFriendshipStatus(User currentUser, User otherUser);
}
