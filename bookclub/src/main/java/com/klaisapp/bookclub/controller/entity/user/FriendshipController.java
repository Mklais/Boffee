package com.klaisapp.bookclub.controller.entity.user;

import com.klaisapp.bookclub.common.messages.Message;
import com.klaisapp.bookclub.common.messages.MessageConstants;
import com.klaisapp.bookclub.exception.CustomApplicationException;
import com.klaisapp.bookclub.service.user.controller.FriendshipControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/friends")
public class FriendshipController {

    private final FriendshipControllerService friendshipControllerService;

    @Autowired
    public FriendshipController(FriendshipControllerService friendshipControllerService) {
        this.friendshipControllerService = friendshipControllerService;
    }

    @GetMapping("/list/{username}")
    public String listFriendships(@PathVariable String username, Model model) {
        friendshipControllerService.addAttributesToProfileFriendshipPage(username, model);
        return "friendship/user-friendship-list";
    }

    @PostMapping("/accept")
    public String acceptFriendRequest(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("friendshipId") int friendshipId,
            RedirectAttributes redirectAttributes) {
        try {
            friendshipControllerService.handleAcceptFriendRequest(userDetails, friendshipId);
            redirectAttributes.addFlashAttribute(
                    "message", Message.success(MessageConstants.FRIEND_REQUEST_ACCEPTED));
            return "redirect:/friends/list/" + userDetails.getUsername();
        } catch (CustomApplicationException e) {
            redirectAttributes.addFlashAttribute(
                    "message", e.getCustomMessage());
            return "redirect:/friends/list/" + userDetails.getUsername();
        }
    }

    @PostMapping("/decline")
    public String declineFriendRequest(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("friendshipId") int friendshipId,
            RedirectAttributes redirectAttributes) {
        try {
            friendshipControllerService.handleDeclineFriendRequest(userDetails, friendshipId);
            redirectAttributes.addFlashAttribute(
                    "message", Message.success(MessageConstants.FRIEND_REQUEST_DECLINED_SUCCESSFULLY));
            return "redirect:/friends/list/" + userDetails.getUsername();
        } catch (CustomApplicationException e) {
            redirectAttributes.addFlashAttribute("message", e.getCustomMessage());
        }
        return "redirect:/friends/list/" + userDetails.getUsername();
    }

    @PostMapping("/send")
    public String sendFriendRequest(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("receiverId") int receiverId,
            RedirectAttributes redirectAttributes) {
        try {
            friendshipControllerService.sendFriendRequest(userDetails, receiverId);
            String username = friendshipControllerService.getViewableProfileUsername(receiverId);
            redirectAttributes.addFlashAttribute(
                    "message", Message.success(MessageConstants.FRIEND_REQUEST_SENT_SUCCESSFULLY));
            return "redirect:/users/profile/" + username;
        } catch (CustomApplicationException e) {
            redirectAttributes.addFlashAttribute(
                    "message", e.getCustomMessage());
            return "redirect:/";
        }
    }
}
