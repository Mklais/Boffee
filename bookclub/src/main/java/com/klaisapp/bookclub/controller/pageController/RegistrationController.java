package com.klaisapp.bookclub.controller.pageController;

import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.service.controller.page.RegistrationControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private RegistrationControllerService registrationControllerService;
    @Autowired
    public RegistrationController(RegistrationControllerService registrationControllerService) {
        this.registrationControllerService = registrationControllerService;
    }

    @GetMapping("/form")
    public String showRegistrationForm(Model model) {
        registrationControllerService.addAttributesToForm(model);
        return "registration-form";
    }

    @PostMapping("/save")
    public String processRegistration(@ModelAttribute("user") User user,
                                      @RequestParam("passwordVerify") String passwordVerify,
                                      Model model) {

        boolean passwordMatch = !registrationControllerService.isErrorInRegistrationProcess(user, passwordVerify, model);
        model.addAttribute("passwordMatch", passwordMatch);

        if (!passwordMatch) {
            return "registration-form";
        } else {
            registrationControllerService.saveUser(user);
            return "redirect:/login/form";
        }
    }
}
