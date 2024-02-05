package com.klaisapp.bookclub.common;

public enum FriendshipStatus {
    PENDING("Pending", "#FFA500"),  // Request has been sent, waiting for approval (Orange)
    ACCEPTED("Accepted", "#008000"), // Both parties have agreed to be friends (Green)
    DECLINED("Declined", "#FF0000"), // The recipient has declined the friend request (Red)
    BLOCKED("Blocked", "#000000"),   // One of the users has blocked the other (Black)
    REQUEST_SENT("Request Sent", "#0000FF"), // Friend request has been sent (Blue)
    NOT_FRIENDS("Not Friends", "#808080");   // Users are not friends (Gray)

    private final String description;
    private final String colorCode;

    FriendshipStatus(String description, String colorCode) {
        this.description = description;
        this.colorCode = colorCode;
    }

    public String getDescription() {
        return description;
    }

    public String getColorCode() {
        return colorCode;
    }
}