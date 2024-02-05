package com.klaisapp.bookclub.aspect;

import com.klaisapp.bookclub.logger.LogSubsection;
import com.klaisapp.bookclub.model.user.User;
import com.klaisapp.bookclub.logger.LogCategory;
import com.klaisapp.bookclub.logger.LogMessageFormatter;
import com.klaisapp.bookclub.logger.UserStatus;
import com.klaisapp.bookclub.service.user.user.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserManagementLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(UserManagementLoggingAspect.class);

    private final UserService userService;

    @Autowired
    public UserManagementLoggingAspect(UserService userService) {
        this.userService = userService;
    }

    @Around("execution(* com.klaisapp.bookclub.controller.entity.user.UserController.changeUserActiveStatus(..)) && args(username)")
    public Object logChangeUserActiveStatus(ProceedingJoinPoint joinPoint, String username) throws Throwable {
        // Fetch user & current state
        User theUser = userService.findByUsername(username);
        int currentStatusIndex = theUser.getActive();

        // Proceed with the original method
        Object result = joinPoint.proceed();

        // Fetch new state
        int newStatusIndex = theUser.getActive();

        String changedFrom = UserStatus.ACTIVE.getStatus();
        String changedTo = UserStatus.INACTIVE.getStatus();
        if (currentStatusIndex != newStatusIndex) {
            // Swap if the status was actually changed
            String temp = changedFrom;
            changedFrom = changedTo;
            changedTo = temp;
        }

        // Log the change
        String logMessage = LogMessageFormatter.format(
                LogCategory.USER,
                LogSubsection.CRUD,
                "Activity status changed!",
                "Username: '" + username + "' User ID: '" + theUser.getUserId() + "'" +
                        "\nStatus changed from: '" + changedFrom + "' to '" + changedTo + "'"
        );
        logger.info("\n" + logMessage);

        return result;
    }

    @After("execution(* com.klaisapp.bookclub.controller.entity.user.UserController.delete(..)) && args(theId)")
    public void logAfterDelete(int theId) {
        String logMessage = LogMessageFormatter.format(
                LogCategory.USER,
                LogSubsection.CRUD,
                "Successful user deletion!",
                "User ID: " + theId
        );
        logger.info("\n" + logMessage);
    }
}
