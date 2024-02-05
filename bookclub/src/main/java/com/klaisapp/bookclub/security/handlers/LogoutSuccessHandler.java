package com.klaisapp.bookclub.security.handlers;

import com.klaisapp.bookclub.logger.LogCategory;
import com.klaisapp.bookclub.logger.LogSubsection;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("logoutSuccessHandler")
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        if (authentication != null && authentication.getDetails() != null) {
            try {
                // Simple log
                String logMessage = String.format(
                        """

                                ---|%s|---
                                ---|%s|---
                                Operation:
                                Details: %s
                                Username: '%s'""",
                        LogCategory.AUTHENTICATION,
                        LogSubsection.LOGOUT,
                        "User logged out successfully!",
                        authentication.getName()
                );

                System.out.println(logMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("/login/form");
    }
}
