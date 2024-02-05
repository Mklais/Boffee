package com.klaisapp.bookclub.service.user.controller;

import com.klaisapp.bookclub.common.FriendshipStatus;
import com.klaisapp.bookclub.model.user.Friendship;
import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.service.user.friendship.FriendshipService;
import com.klaisapp.bookclub.service.user.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendshipControllerService {

    private final FriendshipService friendshipService;

    private final UserService userService;

    @Autowired
    public FriendshipControllerService(FriendshipService friendshipService, UserService userService) {
        this.friendshipService = friendshipService;
        this.userService = userService;
    }

    /**
     * Attributes to the model for displaying the relationships between the logged-in user and its friendships.
     * @param username The username of the user whose friendships are to be displayed.
     * @param model    The model to which attributes are added.
     */
    public void addAttributesToProfileFriendshipPage(String username, Model model) {
        User theUser = userService.findByUsername(username);
        addAttributesToFriendsList(theUser, model);
        addAttributesToPendingRequests(theUser, model);
    }

    private void addAttributesToFriendsList(User theUser, Model model) {
        List<Friendship> friendships = friendshipService.findFriendshipsForUser(theUser.getUserId());

        model.addAttribute("theUser", theUser);
        model.addAttribute("friendships", friendships);
    }


    private void addAttributesToPendingRequests(User theUser, Model model) {
        int userId = theUser.getUserId();
        // Query for pending friendships where the logged-in user is the receiver (user2)
        List<Friendship> pendingFriendships = friendshipService.findPendingFriendshipsByReceiver(userId, FriendshipStatus.PENDING);
        model.addAttribute("pendingFriendships", pendingFriendships);
    }

    /**
     * Handles a friend request by sending it from the logged-in user to the specified profile user.
     *
     * @param userDetails The UserDetails object representing the logged-in user.
     * @param receiverId   The ID of the user to whom the friend request is sent.
     * @return A ResponseEntity containing a status message indicating the result of the friend request.
     */
    public ResponseEntity<String> sendFriendRequest(UserDetails userDetails, int receiverId) {
        try {
            User loggedInUser = userService.findByUsername(userDetails.getUsername());
            User profileUser = userService.findById(receiverId);

            return friendshipService.sendFriendRequest(loggedInUser, profileUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending friend request");
        }
    }

    @Transactional
    public void handleAcceptFriendRequest(UserDetails userDetails, int friendshipId) {
        User loggedInUser = userService.findByUsername(userDetails.getUsername());
        Friendship friendship = friendshipService.findFriendShipById(friendshipId);

        // Ensure the logged-in user is part of the friendship
        if (friendship.getUser1().equals(loggedInUser) || friendship.getUser2().equals(loggedInUser)) {
            // Update the friendship status to ACCEPTED
            friendship.setStatus(FriendshipStatus.ACCEPTED);
            friendshipService.save(friendship);
        } else {
            // Handle error: Logged-in user is not part of the friendship
            throw new RuntimeException("User is not authorized to accept this friend request.");
        }
    }

    public String getViewableProfileUsername(int receiverId) {
        User theUser = userService.findById(receiverId);
        return theUser.getUsername();
    }

//    public void handleDeclineFriendRequest(UserDetails userDetails, int friendshipId) {
//        User loggedInUser = userService.findByUsername(userDetails.getUsername());
//        Friendship friendship = friendshipService.findFriendshipById(friendshipId);
//
//        if (friendship != null && friendship.getUser2().equals(loggedInUser)) {
//            // Decline the friend request
//            friendship.setStatus(FriendshipStatus.DECLINED);
//            friendshipService.saveFriendship(friendship);
//        }
//    }
}
