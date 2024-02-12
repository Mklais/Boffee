package com.klaisapp.bookclub.aspect;

import com.klaisapp.bookclub.logger.LogSubsection;
import com.klaisapp.bookclub.model.User;
import com.klaisapp.bookclub.logger.LogCategory;
import com.klaisapp.bookclub.logger.LogMessageFormatter;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
public class UserRegistrationLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserRegistrationLoggingAspect.class);

    @Around("execution(* com.klaisapp.bookclub.security.RegistrationController.processRegistration(..)) && args(user, passwordVerify, model)")
    public Object logUserRegistration(ProceedingJoinPoint joinPoint, User user, String passwordVerify, Model model) throws Throwable {
        attemptToRegisterLog(user);

        Object result = null;

        try {
            result = joinPoint.proceed();

            // Check if password verification was successful
            Boolean passwordMatch = (Boolean) model.asMap().get("passwordMatch");
            if (passwordMatch != null && passwordMatch) {
                successfulRegistrationLog(user);
            } else {
                passwordMisMatchErrorLog(user);
            }

        } catch (Exception e) {
            registrationFailedLog(user, e);
            throw e;
        } finally {
            logger.info("\n---User Registration End---");
        }

        return result;
    }

    private static void attemptToRegisterLog(User user) {
        String logMessage = LogMessageFormatter.format(
                LogCategory.USER,
                LogSubsection.REGISTRATION,
                "Attempting to register user",
                "Username: '" + user.getUsername() + "'"
        );
        logger.info(logMessage);
    }

    private static void passwordMisMatchErrorLog(User user) {
        String failureMessage = LogMessageFormatter.format(
                LogCategory.USER,
                LogSubsection.REGISTRATION,
                "Registration failed due to password mismatch",
                "Username: '" + user.getUsername() + "'"
        );
        logger.error(failureMessage);
    }

    private static void successfulRegistrationLog(User user) {
        String successMessage = LogMessageFormatter.format(
                LogCategory.USER,
                LogSubsection.REGISTRATION,
                "Successfully registered user",
                "User ID: '" + user.getUserId() + "' & username: '" + user.getUsername() + "'"
        );
        logger.info(successMessage);
    }

    private static void registrationFailedLog(User user, Exception e) {
        String failureMessage = LogMessageFormatter.format(
                LogCategory.USER,
                LogSubsection.REGISTRATION,
                "Registration failed",
                "Username: '" + user.getUsername() + "'"
        );
        logger.error(failureMessage, e);
    }
}