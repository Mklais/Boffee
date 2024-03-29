package com.klaisapp.bookclub.controller.authority;

import com.klaisapp.bookclub.service.authority.AuthorityControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/authority")
public class AuthorityController {

    private final AuthorityControllerService authorityControllerService;

    @Autowired
    public AuthorityController(AuthorityControllerService authorityControllerService) {
        this.authorityControllerService = authorityControllerService;
    }

    @GetMapping("/upgrade")
    public String upgradeAuthority() {
        String username = authorityControllerService.addUpgradeToUserAuthority();
        return "redirect:/users/profile/" + username;
    }
}
