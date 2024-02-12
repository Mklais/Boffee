package com.klaisapp.bookclub.service.friendship;

import com.klaisapp.bookclub.common.messages.Message;
import com.klaisapp.bookclub.common.messages.MessageConstants;
import com.klaisapp.bookclub.exception.CustomApplicationException;
import com.klaisapp.bookclub.model.Friendship;
import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.service.friendship.FriendshipService;
import com.klaisapp.bookclub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class FriendshipControllerService {

    private final FriendshipService friendshipService;

    private final UserService userService;

    @Autowired
    public FriendshipControllerService(FriendshipService friendshipService, UserService userService) {
        this.friendshipService = friendshipService;
        this.userService = userService;
    }

    public void addAttributesToProfileFriendshipPage(String username, Model model) {
        User theUser = userService.findByUsername(username);
        model.addAttribute("theUser", theUser);
        model.addAttribute("friendships", friendshipService.listFriendships(theUser));
        model.addAttribute("incomingRequests", friendshipService.listIncomingFriendRequests(theUser));
    }

    public void handleAcceptFriendRequest(UserDetails userDetails, int friendshipId) {
        try {
            User currentUser = userService.findByUsername(userDetails.getUsername());
            Friendship friendship = friendshipService.findById(friendshipId)
                    .orElseThrow(() -> new CustomApplicationException(
                            Message.error(MessageConstants.FRIEND_REQUEST_NOT_FOUND))
                    );

            if (!friendship.getReceiver().equals(currentUser)) {
                throw new CustomApplicationException(
                        Message.error(MessageConstants.FRIEND_REQUEST_UNAUTHORIZED)
                );
            }

            friendshipService.acceptFriendRequest(friendshipId);
        } catch (CustomApplicationException e) {
            throw new CustomApplicationException(
                    Message.error(MessageConstants.UNEXPECTED_ERROR + e.getCustomMessage())
            );
        }
    }

    public void handleDeclineFriendRequest(UserDetails userDetails, int friendshipId) {
        try {
            User currentUser = userService.findByUsername(userDetails.getUsername());
            Friendship friendship = friendshipService.findById(friendshipId)
                    .orElseThrow(() -> new CustomApplicationException(
                            Message.error(MessageConstants.FRIEND_REQUEST_NOT_FOUND)
                    ));

            if (!friendship.getReceiver().equals(currentUser)) {
                throw new CustomApplicationException(
                        Message.error(MessageConstants.FRIEND_REQUEST_DECLINE_UNAUTHORIZED));
            }

            friendshipService.declineAndDeleteFriendRequest(friendshipId);
        } catch (CustomApplicationException e) {
            throw new CustomApplicationException(
                    Message.error(MessageConstants.UNEXPECTED_ERROR + e.getCustomMessage())
            );
        }
    }

    public void sendFriendRequest(UserDetails userDetails, int receiverId) {
        User sender = userService.findByUsername(userDetails.getUsername());
        User receiver = userService.findById(receiverId);
        friendshipService.sendFriendRequest(sender, receiver);
    }

    public String getViewableProfileUsername(int receiverId) {
        User profileUsername = userService.findById(receiverId);
        return profileUsername.getUsername();
    }
}
