package com.klaisapp.bookclub.controller.entity.user;

import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.service.user.controller.UserControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserControllerService userControllerService;

    @Autowired
    public UserController(UserControllerService userControllerService) {
        this.userControllerService = userControllerService;
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        userControllerService.addAttributesToUsersList(model);
        return "user/user-list";
    }

    @GetMapping("/profile/{username}")
    public String showUserProfile(@PathVariable String username, Model model) {
        userControllerService.addAttributesToProfilePage(username, model);
        return "user/user-profile";
    }

    @GetMapping("/updateForm")
    public String showFormForUpdate(@RequestParam("userId") int theId, Model model) {
        userControllerService.addAttributesForUserUpdate(theId, model);
        return "user/user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("theUser") User theUser) {
        userControllerService.updateUserAndRelatedEntities(theUser);
        String username = theUser.getUsername();
        return "redirect:/users/profile/" + username;
    }

    @PostMapping("/edit/activeStatus")
    public String changeUserActiveStatus(@RequestParam("username") String username) {
        userControllerService.toggleUserActiveStatus(username);
        return "redirect:/users/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("userId") int theId) {
        userControllerService.deleteUser(theId);
        return "redirect:/users/list";
    }
}
