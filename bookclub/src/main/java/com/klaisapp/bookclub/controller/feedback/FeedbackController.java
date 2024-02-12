package com.klaisapp.bookclub.controller.feedback;

import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedbackController {

    private final UserService userService;

    private static final int FEEDBACK_BONUS = 20;

    @Autowired
    public FeedbackController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/processFeedback")
    public String submitFeedback(Model model) {
        String loggedInUser = userService.getLoggedInUsername();

        User theUser = userService.findByUsername(loggedInUser);
        theUser.setActivityPoints(theUser.getActivityPoints() + FEEDBACK_BONUS);
        userService.save(theUser);

        model.addAttribute("feedbackSuccess", true);

        return "misc/feedback-result";
    }
}
