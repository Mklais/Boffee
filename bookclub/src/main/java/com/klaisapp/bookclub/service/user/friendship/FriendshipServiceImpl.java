package com.klaisapp.bookclub.service.user.friendship;

import com.klaisapp.bookclub.common.FriendshipStatus;
import com.klaisapp.bookclub.common.messages.Message;
import com.klaisapp.bookclub.common.messages.MessageConstants;
import com.klaisapp.bookclub.exception.CustomApplicationException;
import com.klaisapp.bookclub.model.user.Friendship;
import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.repository.user.FriendshipRepository;
import com.klaisapp.bookclub.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FriendshipServiceImpl implements FriendshipService {

    private final FriendshipRepository friendshipRepository;

    private final UserRepository userRepository;

    @Autowired
    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Friendship> findById(int id) {
        return friendshipRepository.findById(id);
    }

    @Override
    @Transactional
    public Friendship sendFriendRequest(User sender, User receiver) {
        boolean requestExists = friendshipRepository.existsBySenderAndReceiver(sender, receiver)
                || friendshipRepository.existsBySenderAndReceiver(receiver, sender);

        if (requestExists) {
            throw new CustomApplicationException(Message.error(MessageConstants.FRIEND_REQUEST_EXISTS));
        }

        Friendship friendRequest = new Friendship();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        friendRequest.setStatus(FriendshipStatus.PENDING);
        friendRequest.setSentAt(new Date());
        // Not setting respondedAt here, since it's a new request
        return friendshipRepository.save(friendRequest);
    }

    @Override
    @Transactional
    public void acceptFriendRequest(int requestId) {
        Friendship friendRequest = friendshipRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found!"));
        friendRequest.setStatus(FriendshipStatus.ACCEPTED);
        friendRequest.setRespondedAt(new Date());
        friendshipRepository.save(friendRequest);
    }

    @Override
    @Transactional
    public void declineFriendRequest(int requestId) {
        Friendship friendRequest = friendshipRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Friend request not found!"));
        friendRequest.setStatus(FriendshipStatus.DECLINED);
        friendRequest.setRespondedAt(new Date());
        friendshipRepository.save(friendRequest);
    }

    @Override
    @Transactional
    public void declineAndDeleteFriendRequest(int requestId) {
        friendshipRepository.deleteById(requestId);
    }

    @Override
    public List<Friendship> listFriendships(User user) {
        return friendshipRepository.findAllRelevantFriendshipsByUser(user);
    }

    @Override
    public List<Friendship> listIncomingFriendRequests(User user) {
        return friendshipRepository.findAllByReceiverAndStatus(user, FriendshipStatus.PENDING);
    }

    @Override
    public FriendshipStatus determineFriendshipStatus(User user1, User user2) {
        Optional<Friendship> friendship = friendshipRepository.findByUsers(user1.getUserId(), user2.getUserId());

        if (!friendship.isPresent()) {
            return FriendshipStatus.NOT_FRIENDS;
        }

        Friendship foundFriendship = friendship.get();

        if (foundFriendship.getStatus().equals(FriendshipStatus.ACCEPTED)) {
            return FriendshipStatus.ACCEPTED;
        } else if (foundFriendship.getStatus().equals(FriendshipStatus.DECLINED)) {
            return FriendshipStatus.DECLINED;
        } else {
            return FriendshipStatus.PENDING;
        }
    }
}
