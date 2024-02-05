package com.klaisapp.bookclub.security.handlers;

import com.klaisapp.bookclub.logger.LogCategory;
import com.klaisapp.bookclub.logger.LogSubsection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        String logMessage = String.format(
                """

                        ---|%s|---
                        ---|%s|---
                        Operation:
                        Details: %s
                        Username: '%s'""",
                LogCategory.AUTHENTICATION,
                LogSubsection.LOGIN,
                "User logged in successfully!",
                username
        );

        System.out.println(logMessage);

        response.sendRedirect("/");
    }
}
