package com.klaisapp.bookclub.controller.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/userLogin")
    public String showLoginPage() {

        return "login-form";
    }
}
