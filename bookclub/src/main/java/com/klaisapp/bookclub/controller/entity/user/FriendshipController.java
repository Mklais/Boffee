package com.klaisapp.bookclub.controller.entity.user;

import com.klaisapp.bookclub.service.user.controller.FriendshipControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/friends")
public class FriendshipController {

    private final FriendshipControllerService friendshipControllerService;

    @Autowired
    public FriendshipController(FriendshipControllerService friendshipControllerService) {
        this.friendshipControllerService = friendshipControllerService;
    }

    @GetMapping("/list/profile/{username}")
    public String listFriendships(@PathVariable String username, Model model) {
        friendshipControllerService.addAttributesToProfileFriendshipPage(username, model);
        return "friendship/user-friendship-list";
    }

    @PostMapping("/send")
    public String sendFriendRequest(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("receiverId") int receiverId) {
        friendshipControllerService.sendFriendRequest(userDetails, receiverId);
        String username = friendshipControllerService.getViewableProfileUsername(receiverId);
        return "redirect:/users/profile/" + username;
    }

    @PostMapping("/accept")
    public String acceptFriendRequest(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("friendshipId") int friendshipId) {
        friendshipControllerService.handleAcceptFriendRequest(userDetails, friendshipId);
        return "redirect:/friends/list/profile/" + userDetails.getUsername();
    }
//
//    @PostMapping("/decline")
//    public String declineFriendRequest(
//            @AuthenticationPrincipal UserDetails userDetails,
//            @RequestParam("friendshipId") int friendshipId) {
//        friendshipControllerService.handleDeclineFriendRequest(userDetails, friendshipId);
//        return "redirect:/friends/list/profile/" + userDetails.getUsername();
//    }
}
