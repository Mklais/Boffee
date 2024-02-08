package com.klaisapp.bookclub.common.messages;

public final class MessageConstants {
    public static final String ENTITY_NAME_DUPLICATE =
            "Entity with this name already exists!";
    public static final String ENTITY_DUPLICATE =
            "This entity already exists!";
    public static final String ENTITY_SAVED_SUCCESSFULLY =
            "Entity saved successfully.";
    public static final String USER_DUPLICATE =
            "User with this username already exists!";
    public static final String USER_TOO_FEW_REGISTERED =
            "Too few users have registered on the platform, invite them to join!";
    public static final String USER_NOT_FOUND =
            "User not found! Redirecting to login page.";
    public static final String FRIEND_REQUEST_ACCEPTING_ERROR =
            "Error accepting friend request!";
    public static final String FRIEND_REQUEST_ACCEPTED =
            "Friend request accepted successfully.";
    public static final String FRIEND_REQUEST_SENT_SUCCESSFULLY =
            "Friend request sent successfully!";
    public static final String FRIEND_REQUEST_UNAUTHORIZED =
            "User not authorized to accept this friend request";
    public static final String FRIEND_REQUEST_DECLINE_UNAUTHORIZED =
            "User not authorized to decline this friend request";
    public static final String FRIEND_REQUEST_NOT_FOUND =
            "Friend request was not found!";
    public static final String FRIEND_REQUEST_DECLINED_SUCCESSFULLY =
            "Friend request declined successfully.";
    public static final String FRIEND_REQUEST_EXISTS =
            "A friend request already exists or you are already friends.";
    public static final String PASSWORDS_DO_NOT_MATCH =
            "Passwords no not match!";
    public static final String UNEXPECTED_ERROR =
            "Oops! :/ An unexpected error occurred ";
    private MessageConstants() {
        throw new AssertionError("Cannot instantiate a constants class");
    }
}
