package com.klaisapp.bookclub.service.messageservice.errormessage;

public class ErrorMessageService {

    public static String getDuplicateAuthorErrorMessage() {
        return "Author with the same first and last name already exists!";
    }

    public static String getDuplicateGenreErrorMessage() {
        return "This genre exists!";
    }

    public static String getDuplicateUserErrorMessage() {
        return "User with this name exists!";
    }

    public static String getNoMatchingPasswordsErrorMessage() {
        return "Passwords do not match!";
    }

    public static String getNoUserFoundExceptionErrorMessage() {
        return "User not found! Redirecting to login page!";
    }

    public static String getTooFewRegisteredUsersErrorMessage() {
        return "Too few users have registered on Boffee, invite them to join!";
    }
}
