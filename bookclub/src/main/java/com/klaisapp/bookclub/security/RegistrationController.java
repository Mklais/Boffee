package com.klaisapp.bookclub.security;

import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.service.auth.RegistrationControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationControllerService registrationControllerService;
    @Autowired
    public RegistrationController(RegistrationControllerService registrationControllerService) {
        this.registrationControllerService = registrationControllerService;
    }

    @GetMapping("/form")
    public String showRegistrationForm(Model model) {
        registrationControllerService.addAttributesToForm(model);
        return "auth/registration-form";
    }

    @PostMapping("/save")
    public String processRegistration(@ModelAttribute("user") User user,
                                      @RequestParam("passwordVerify") String passwordVerify,
                                      Model model) {

        boolean passwordMatch = !registrationControllerService.isErrorInRegistrationProcess(user, passwordVerify, model);
        model.addAttribute("passwordMatch", passwordMatch);

        if (!passwordMatch) {
            return "auth/registration-form";
        } else {
            registrationControllerService.saveUser(user);
            return "redirect:/userLogin";
        }
    }
}
